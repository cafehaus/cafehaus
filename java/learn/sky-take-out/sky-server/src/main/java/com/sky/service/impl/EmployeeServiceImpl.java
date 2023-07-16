package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.EmployeeMapper;
import com.sky.result.PageResult;
import com.sky.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // TODO 后期需要进行md5加密，然后再进行比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        // System.out.println(password);
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }


    /**
     * 新增员工
     * @param employeeDTO
     * @return
     */
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        // employee.setIdNumber(employeeDTO.getIdNumber());
        // employee.setName(employeeDTO.getName());
        // employee.setPhone(employeeDTO.getPhone());
        // employee.setSex(employeeDTO.getSex());
        // employee.setUsername(employeeDTO.getUsername());

        // 上面的一个一个设置属性也可以直接用下面的方法拷贝，注意要属性一致时才可以用
        // 对象属性拷贝
        BeanUtils.copyProperties(employeeDTO, employee);

        // 设置用户状态
        employee.setStatus(StatusConstant.ENABLE);

        // 设置用户密码
        String password = DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes());
        employee.setPassword(password);

        // 设置时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        // 设置创建/修改人
        // TODO 后面需要完善
        // employee.setCreateUser(10L);
        // employee.setUpdateUser(10L);

        // 完善方式，从 ThreadLocal 里取出从 token 里解析出来的员工 id
        Long empId = BaseContext.getCurrentId();
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);

        // 插入数据库
        employeeMapper.insert(employee);
    }

    /**
     * 分页查询员工列表
     * @param EmployeePageQueryDTO
     * @return
     */
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        // mybatis 的分页插件
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());
        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);

        Long total = page.getTotal();
        List<Employee> records = page.getResult();
        return new PageResult(total, records);
    }

}

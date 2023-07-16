package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增
     * @param employeeDTO
     * @return
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 分页查询员工列表
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);


    /**
     * 启用禁用员工
     * @param id
     * @param status
     * @return
     */
    void startOrStop(Long id, Integer status);
}

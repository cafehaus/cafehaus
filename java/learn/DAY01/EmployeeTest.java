import java.io.*;

public class EmployeeTest {
    public static void main(String[] args) {
        // 构造两个对象
        Employee empOne = new Employee("ZhouXiaohei");
        Employee empTwo = new Employee("ZhouDahua");

        // 设置值
        empOne.empAge(18);
        empOne.empDesignation("资深前端工程师");
        empOne.empSalary(50000);
        empOne.printEmployee();

        empTwo.empAge(27);
        empTwo.empDesignation("测试开发工程师");
        empTwo.empSalary(20000);
        empTwo.printEmployee();
    }
}

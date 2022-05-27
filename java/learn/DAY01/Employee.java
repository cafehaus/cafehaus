import java.io.*;

public class Employee {
    String name;
    int age;
    String designation;
    double salary;

    // 构造器
    public Employee(String name) {
        this.name = name;
    }

    // 设置age
    public void empAge(int empAge) {
        age = empAge;
    }

    // 设置 designation
    public void empDesignation(String empDesig) {
        designation = empDesig;
    }

    // 设置 salary
    public void empSalary(double empSalary) {
        salary = empSalary;
    }

    // 打印
    public void printEmployee() {
        System.out.println("名字：" + name);
        System.out.println("年龄：" + age);
        System.out.println("职业：" + designation);
        System.out.println("薪水：" + salary);
    }
}

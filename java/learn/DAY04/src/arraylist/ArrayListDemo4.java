package arraylist;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListDemo4 {
    public static void main(String[] args) {
        // 用集合储存学生信息
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("20220901", "周小一", 13, "六年级二班"));
        students.add(new Student("20220902", "周小二", 18, "高三二十二班"));
        students.add(new Student("20220903", "周小三", 17, "高二四班"));
        students.add(new Student("20220904", "周小四", 15, "初三五班"));

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入要查询的学学号");
            String no = sc.next();
            Student s = queryStudentById(students, no);
            if (s != null) {
                System.out.println("你查询的学生信息如下：");
                System.out.println("学号：" + s.getId() + "，姓名：" + s.getName() + "，年龄：" + s.getAge() + "，班级：" + s.getGrade());
            } else {
                System.out.println("当前学生不存在");
            }
        }
    }

    public static Student queryStudentById(ArrayList<Student> students, String id) {
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);

            // 注意：不能直接用 s.id 去访问，因为这些属性都是 private
            String sid = s.getId();
            if (sid.equals(id)) {
                // System.out.println("你查询的学生信息如下：");
                // System.out.println("学号：" + sid + "，姓名：" + s.getName() + "，年龄：" + s.getAge() + "，班级：" + s.getGrade());
                return s;
                // break; // 方法里跳出循环可以不用 break，return 就直接跳出方法结束本程序了
            }
        }

        // 未查到
        return null;
    }
}

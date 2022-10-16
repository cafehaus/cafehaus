package cn.cafe123.static_field;

public class StaticFieldDemo {
    public static void main(String[] args) {
        User student = new User();
        student.setName("周小黑");

        System.out.println(student.name);
    }
}

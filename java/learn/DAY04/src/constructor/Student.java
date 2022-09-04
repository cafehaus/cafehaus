package constructor;

public class Student {
    // 封装思想：什么样的对象，就应该提供对应的属性的行为
    // 属性加 private，并提供对应 getter 和 setter

    private String name;
    private String sex;
    private int age;

    // 快捷生成 getter 和 setter
    // 选中上面的属性，右键 - Generate

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

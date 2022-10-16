package cn.cafe123.static_field;

public class User {
    /**
     * static 静态成员变量，在内存中只存一份，可以被共享
     * 访问方式 1（推荐）：类名.属性名
     * 访问方式 2：实例对象.属性名
     */
    public static int onlineNumber = 110;

    /**
     * 私有成员变量，存在每个实例对象上，访问：对象.属性名
     */
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}

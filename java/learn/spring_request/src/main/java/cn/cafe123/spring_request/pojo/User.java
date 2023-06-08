package cn.cafe123.spring_request.pojo;

public class User {
    private String name;
    private Integer age;
    private Address address;

    // ctrl + 回车可以快速生成 toString 和 getter/setter
    // 不添加 toString 方法，System.out.println 打印的时候只能看到一个地址

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

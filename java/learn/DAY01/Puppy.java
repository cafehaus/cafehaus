public class Puppy {
    int puppyAge;
    public Puppy(String name) {
        System.out.println("小狗的名字是：" + name);
    }

    public void setAge(int age) {
        puppyAge = age;
    }

    public int getAge() {
        System.out.println("小狗的年龄为：" + puppyAge);
        return puppyAge;
    }

    public static void main(String[] args) {
        // 创建对象
        Puppy myPuppy = new Puppy("tommy");

        // 设置年龄
        myPuppy.setAge(2);

        // 获取年龄
        myPuppy.getAge();

        System.out.println("变量值：" + myPuppy.puppyAge);
    }

}

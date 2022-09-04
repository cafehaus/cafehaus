package arraylist;

import java.util.ArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {
        // 集合 ArrayList 里只能储存引用类型
        ArrayList names = new ArrayList();
        names.add("周小黑");
        names.add("mysql");

        System.out.println("====== ArrayList names ======");
        System.out.println(names);

        // 用泛型方式定义
        ArrayList<String> fruit = new ArrayList<>();
        fruit.add("apple");
        fruit.add("banner");
        System.out.println("====== ArrayList<String> fruit ======");
        System.out.println(fruit);

        // 注意这里不能用 int，集合只能存引用类型数据，而 int 是基本数据类型
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(123);
        nums.add(90);
        System.out.println("====== ArrayList<Integer> nums ======");
        System.out.println(nums);
    }
}

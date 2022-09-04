package cn.cafe123.create;

public class ArrayDemo3 {
    public static void main(String[] args) {
        // 数组注意事项
        // 1. 也可以写成：数据类型 变量名[] = {元素1, 元素2}，但是不推荐写成这样
        int[] income = {1000, 4000, 9000};
        int income2[] = {1000, 4000, 9000};

        // 2. 只能存指定数据类型的元素
        // String[] names = {"小黑", "小白", 18}; // 会报错

        // 3. 数组一旦定义出来，长度和类型就固定了
        int[] nums = {1, 2, 3};
        System.out.println(nums[4]); // 运行时会报错：Index 4 out of bounds for length 3
    }
}

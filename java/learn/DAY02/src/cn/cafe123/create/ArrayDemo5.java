package cn.cafe123.create;

public class ArrayDemo5 {
    public static void main(String[] args) {
        // 动态数组的默认值

        // 1. 整型数组的默认值都是 0，浮点型是 0.0
        short[] shorts = new short[3];
        System.out.println(shorts[2]);

        double[] doubles = new double[3];
        System.out.println(doubles[2]);

        // 2. 字符型数组的默认值也是 0
        char[] chars = new char[3];
        System.out.println(chars[2]);

        // 3. 布尔值数组的默认值是 false
        boolean[] booleans = new boolean[100];
        System.out.println(booleans[99]);

        // 4. 引用型数组（类，接口，数组，String）的默认值是 null
        String[] strings = new String[88];
        System.out.println(strings[60]);
    }
}

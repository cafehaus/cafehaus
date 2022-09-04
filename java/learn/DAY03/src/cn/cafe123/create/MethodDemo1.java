package cn.cafe123.create;

public class MethodDemo1 {
    public static void main(String[] args) {
        // 调用方法
        int res = add(100, 200);
        System.out.println(res);
    }

    public static int add(int a, int b) {
        int c = a + b;
        return c;
    }
}

package cn.cafe123.create;

public class MethodDemo3 {
    public static void main(String[] args) {
        // 判断一个数是奇数还是偶数
        String res = isOddEven(13);
        System.out.println(res);
    }

    public static String isOddEven(int n) {
        if (n % 2 == 0) {
            return "even";
        } else {
            return "odd";
        }
    }
}

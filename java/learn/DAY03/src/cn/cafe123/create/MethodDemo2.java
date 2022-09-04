package cn.cafe123.create;

public class MethodDemo2 {
    public static void main(String[] args) {
        // 求和 1 - n
        int res = oneToNSum(100);
        System.out.println(res);
    }

    public static int oneToNSum(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }
}

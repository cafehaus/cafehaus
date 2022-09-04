package string;

import java.util.Random;

public class StringDemo2 {
    public static void main(String[] args) {
        // 生成 n 个随机验证码
        String res = createCode(6);
        System.out.println(res);
    }

    public static String createCode(int n) {
        Random r = new Random();
        String code = "";

        // 先定义一个规则字符串
        String rule = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for (int i = 0; i < n; i++) {
            //注意：java 中 String 类的 length 是一个方法，要调用才行，不是像 javascript 里直接 .length 就可以的
            int idx = r.nextInt(rule.length());
            code += rule.charAt(idx);
        }

        return code;
    }
}

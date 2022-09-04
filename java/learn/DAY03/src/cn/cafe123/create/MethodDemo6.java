package cn.cafe123.create;

import java.util.Random;
import java.util.Scanner;

public class MethodDemo6 {
    public static void main(String[] args) {
        // 生成 n 位的随机验证码，包括 数字、大写字母、小写字母
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入要生成验证码的位数");
        int num = sc.nextInt();

        String code = createCode(num);
        System.out.println("生成的" + num + "位随机验证码是：" + code);
    }

    public static String createCode(int num) {
        Random r = new Random();
        String code = "";

        // 可以直接输入 num.fori 快捷生成
        for (int i = 0; i < num; i++) {
            int idx = r.nextInt(3); // 0 数字 1 大写字母 2 小写字母
            switch (idx) {
                case 0:
                    int ch = r.nextInt(10);
                    code += ch;
                    break;
                case 1:
                    char ch1 = (char)(r.nextInt(25) + 65); // A 65
                    code += ch1;
                    break;
                case 2:
                    char ch2 = (char)(r.nextInt(25) + 97); // A 97
                    code += ch2;
                    break;
            }
        }

        return code;
    }
}

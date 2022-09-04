package string;

import java.util.Random;
import java.util.Scanner;

public class StringDemo3 {
    public static void main(String[] args) {
        // 用户名密码登录，只有 3 次机会
        int num = 3; // 只能输入的次数
        String okName = "zhouxiaohei";
        String okPassword = "123456";

        Scanner sc = new Scanner(System.in);
        for (int i = 1; i <= num; i++) {
            System.out.println("请输入用户名");
            String name = sc.next();

            System.out.println("请输入密码");
            String password = sc.next();

            int chance = num - i;
            if (okName.equals(name)) {
                if (okPassword.equals(password)) {
                    System.out.println("恭喜，你在第 " + i + " 次输入就登录成功了");
                    break;
                } else {
                    System.out.println("密码错误，你还有 " + chance + " 次机会");
                }
            } else {
                System.out.println("用户名错误，你还有 " + chance + " 次机会");
            }
        }
    }
}

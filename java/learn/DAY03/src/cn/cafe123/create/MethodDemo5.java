package cn.cafe123.create;

import java.util.Scanner;

public class MethodDemo5 {
    public static void main(String[] args) {
        // 买机票：输入月份、机票原价和头等舱/经济舱
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入机票原价");
        double money = sc.nextDouble();

        int month = 0;
        while (true) {
            System.out.println("请输入月份");
            int curMonth = sc.nextInt();
            if (curMonth < 1 || curMonth > 12) {
                System.out.println("你输入的月份有误，请输入1-12之间的数字");
            } else {
                month = curMonth;
                break;
            }
        }

        System.out.println("请输入机票类型：头等舱或经济舱");
        String type = sc.next();

        double realMoney = buyTicket(money, month, type);
        System.out.println("你想要购买的机票价格是：" + realMoney);
    }

    public static double buyTicket(double money, int month, String type) {
        if (month >= 5 && month <= 10) {
            switch (type) {
                case "头等舱":
                    money *= 0.9;
                    break; // 注意：这里一定要 break，否则下面的还会执行
                case "经济舱":
                    money *= 0.85;
                    break;
                default:
                    System.out.println("你输入仓位类型有问题");
            }
        } else {
            switch (type) {
                case "头等舱":
                    money *= 0.7;
                    break;
                case "经济舱":
                    money *= 0.65;
                    break;
                default:
                    System.out.println("你输入仓位类型有问题");
            }
        }

        return money;
    }
}

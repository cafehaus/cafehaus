package string;

import java.util.Scanner;

public class StringDemo4 {
    public static void main(String[] args) {
        // 手机号脱敏
        Scanner sc = new Scanner(System.in);
        String hidePhone = "";

        while (true) {
            System.out.println("请输入你的手机号");
            String phone = sc.next();

            // 先根据长度简单判断下
            if (phone.length() == 11) {
                // String 类的 substring() 方法返回字符串的子字符串
                // substring(int beginIndex, int endIndex)
                // 参数为开始索引和结束索引(可省略)，包前不包后（区间在java中大部分都遵循这个规则）

                String before = phone.substring(0, 3);
                String after = phone.substring(7);

                hidePhone = before + "****" + after;
                break;
            } else {
                System.out.println("手机号格式错误");
            }
        }

        System.out.println("脱敏后的手机号是：" + hidePhone);
    }
}

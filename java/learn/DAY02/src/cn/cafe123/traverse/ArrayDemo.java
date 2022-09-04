package cn.cafe123.traverse;

import java.util.Random;
import java.util.Scanner;

public class ArrayDemo {
    // main 入口函数快捷键生成：main + 回车
    // 打印快捷键生成：sout + 回车

    public static void main(String[] args) {
        // 遍历数组
        int[] ages = {12, 19, 190, 490, 0};

        for (int i = 0; i < ages.length; i++) {
            System.out.println(ages[i]);
        }

        // 案例
        // 1. 数组求和
        int[] sums = {1, 2, 3, 4, 5};
        int sum = 0;
        for (int i = 0; i < sums.length; i++) {
            sum += sums[i];
        }
        System.out.println(sum);

        // 2. 找出数组中最大值
        int[] maxs = {81, 22, 309, 4, 50, 908, 21};
        int max = maxs[0];
        for (int i = 0; i < maxs.length; i++) {
            max = maxs[i] > max ? maxs[i] : max;
        }
        System.out.println(max);

        // 3. 猜数字游戏：5哥1-20之间的随机数，让用户猜
        int[] datas = new int[5];

        // 生成随机数
        Random r = new Random();
        for (int i = 0; i < datas.length; i++) {
            datas[i] = r.nextInt(20) + 1;
        }

        //  让用户猜
        Scanner sc = new Scanner(System.in);

        // break + 标签：结束循环
        // x: 跳到此位置,则外层代码不影响继续执行，内层循环结束 (如果外层无代码.则直接结束)
        myOut:
        while (true) {
            System.out.println("请输入一个1-20之间的数字：");
            int guessData = sc.nextInt();

            // 判断用户输入的数字是否在随机输数组中
            for (int i = 0; i < datas.length; i++) {
                if (guessData == datas[i]) {
                    System.out.println("恭喜你猜中了，在数组中的索引是：" + i);
                    break myOut; // 结束整个循环，跳到 myOut 标签那
                }
            }

            System.out.println("你未猜中，请继续猜");
        }

        // 猜中了打印出随机数组
        for (int i = 0; i < datas.length; i++) {
            // println 会自动换行    print 不会换行
            System.out.print(datas[i] + "\t");
        }

        // 4. 根据输入的5个员工工号，随机排序
        int[] persons = new int[5];

        // 接受输入赋值
        // Scanner sc = new Scanner(System.in); // 上一个例子已经定义了，所以这里不用定义了
        for (int i = 0; i < persons.length; i++) {
            System.out.println("请输入第" + (i + 1) + "个员工的工号：");
            persons[i] = sc.nextInt();
        }

        // 随机排序实现：每次遍历根据生成的一个 0-4 之间的随机输去交换当前数的位置
        // Random r = new Random(); // 上一个例子已经定义了
        for (int i = 0; i < persons.length; i++) {
            // 生成随机数
            int idx = r.nextInt(persons.length);

            // 将随机序号的员工工号存起来
            int temp = persons[idx];

            // 交换位置
            persons[idx] = persons[i];
            persons[i] = temp;
        }

        // 排序结束后打印一下1
        for (int i = 0; i < persons.length; i++) {
            if (i == 0) System.out.printf("随机排序后的员工工号是：");
            System.out.printf(persons[i] + "\t");
        }
    }
}

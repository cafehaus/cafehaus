package string;

public class StringDemo {
    public static void main(String[] args) {
        // java 中字符串是引用类型，和 javascript 里不一样

        // 用双引号定义的字符串变量储存在字符串常量池中
        String name = "周小黑";
        String str = "我是周小黑";
        String str2 = "我是周小黑";
        String str3 = "我是" + "周小黑"; // 会被直接编译优化成 "我是周小黑"
        String str4 = "我是" + name; // 有变量的，要到运行时才能确定，所以储存在堆中
        String str5 = new String("我是周小黑");

        // 常量池中的字符串不会重复
        System.out.println(str == str2); // true
        System.out.println(str == str3); // true，编译优化
        System.out.println(str == str4); // false
        System.out.println(str == str5); // false，new 出来的是储存在堆中

        // 所以注意，字符串比较不能像 javascript 中直接用 == 或 === 去比较
        // 要用 String 的 equals
        System.out.println("str.equals(str4)：" + str.equals(str4)); // true
        System.out.println("str.equals(str5)：" + str.equals(str5)); // true

        // 笔记：
        // String 中 == 比较引用地址是否相同，equals() 比较字符串的内容是否相同
    }

}

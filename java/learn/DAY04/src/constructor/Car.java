package constructor;

public class Car {
    String brand; // 品牌
    String color; // 颜色
    double price; // 价格

    public Car() {
        System.out.println("无参数构造器被调用");
    }

//    public Car(String b, String c, double p) {
//        brand = b;
//        color = c;
//        price = p;
//        System.out.println("有参数构造器被调用");
//    }
    // 推荐的写法
    public Car(String brand, String color, double price) {
        this.brand = brand;
        this.color = color;
        this.price = price;
        System.out.println("有参数构造器被调用");
    }
}

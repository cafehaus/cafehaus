package shopCar;

import java.util.Scanner;

public class ShopCar {
    public static void main(String[] args) {
        // 先定义固定 100 个长度的商品数组
        Goods[] shopCar = new Goods[100];

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("请输入商品操作名：add-添加，query-查询，update-修改数量，pay-支付");
            String command = sc.next();

            switch (command) {
                case "add":
                    addGoods(shopCar, sc);
                    break;
                case "query":
                    queryGoods(shopCar);
                    break;
                case "update":
                    updateGoods(shopCar, sc);
                    break;
                case "pay":
                    payGoods(shopCar);
                    break;
                default:
                    System.out.println("无效的操作命令，请重新输入");
            }
        }
    }

    // 添加
    public static void addGoods(Goods[] shopCar, Scanner sc) {
        System.out.println("请输入商品编号");
        int id = sc.nextInt();

        System.out.println("请输入商品名称");
        String name = sc.next();

        System.out.println("请输入商品价格");
        double price = sc.nextDouble();

        System.out.println("请输入商品购买数量");
        int buyNumber = sc.nextInt();

        // new 一个商品对象
        Goods g = new Goods();
        g.id = id;
        g.name = name;
        g.price = price;
        g.buyNumber = buyNumber;

        for (int i = 0; i < shopCar.length; i++) {
            // 查找还没有存商品的位置，存进去，注意是要找值为 null 的位置
            if (shopCar[i] == null) {
                shopCar[i] = g;
                break;
            }
        }
    }

    // 查询
    public static void queryGoods(Goods[] shopCar) {
        System.out.println("==========购物车中的商品信息清单==========");
        System.out.println("编号\t\t\t\t\t名称\t\t\t\t\t价格\t\t\t\t\t购买数量");
        for (int i = 0; i < shopCar.length; i++) {
            Goods g = shopCar[i];
            if (g != null) {
                System.out.println(g.id + "\t\t\t\t\t" + g.name+ "\t\t\t\t\t" + g.price + "\t\t\t\t\t" + g.buyNumber);
            } else {
                break;
            }
        }
    }
    // 通过 id 查询商品
    public static Goods queryGoodsById(Goods[] shopCar, int id) {
        for (int i = 0; i < shopCar.length; i++) {
            Goods g = shopCar[i];
            if (g != null && g.id == id) {
                return g;
            }
        }

        return null;
    }

    // 修改商品数量
    public static void updateGoods(Goods[] shopCar, Scanner sc) {
        Goods curGoods = null;

        while (true) {
            System.out.println("请输入要修改数量的商品编号");
            int id = sc.nextInt();

            Goods g = queryGoodsById(shopCar, id);
            if (g != null) {
                curGoods = g;
                break;
            } else {
                System.out.println("商品编号为 " + id + " 的商品不存在，请重新输入");
            }
        }

        System.out.println("请输入要修改数量");
        int buyNumber = sc.nextInt();

        // 引用类型，可以直接修改
        curGoods.buyNumber = buyNumber;

        // 查询一下看下结果
        queryGoods(shopCar);
    }

    // 支付
    public static void payGoods(Goods[] shopCar) {
        double money = 0.0;
        for (int i = 0; i < shopCar.length; i++) {
            Goods g = shopCar[i];
            if (g != null) {
                money += g.price * g.buyNumber;
            } else {
                break;
            }
        }

        queryGoods(shopCar);
        System.out.println("你需要支付的金额一共为：" + money);
    }
}

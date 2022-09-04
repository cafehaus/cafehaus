package constructor;

public class NiceMove {
    public static void main(String[] args) {
        // 用一个数组来存电影数据
        Movie[] movies = new Movie[2];

        // 实例化电影
        Movie mov = new Movie("隐如尘埃", 9.2, "海青");
        Movie mov2 = new Movie("人生大事", 8.7, "朱一龙");

        movies[0] = mov;
        movies[1] = mov2;

        // 打印一下
        for (int i = 0; i < movies.length; i++) {
            Movie m = movies[i];
            System.out.println("第" + (i + 1) + "部电影：" + m.getName() + ", 评分 " + m.getScore() + "分, 主演 " + m.getActor());
        }
    }
}

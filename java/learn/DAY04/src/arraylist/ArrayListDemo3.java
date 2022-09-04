package arraylist;

import java.util.ArrayList;

public class ArrayListDemo3 {
    public static void main(String[] args) {
        // 用集合存自定义对象
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("小武", 9.2, "王宏伟"));
        movies.add(new Movie("三峡好人", 9.3, "韩三明"));
        movies.add(new Movie("最后的棒棒", 8.4, "何苦"));

        for (int i = 0; i < movies.size(); i++) {
            Movie m = movies.get(i);
            System.out.println("第 " + (i + 1) + " 部电影是：" + m.getName() + "，评分 "  + m.getScore() + "，主演 " + m.getActor());
        }
    }
}

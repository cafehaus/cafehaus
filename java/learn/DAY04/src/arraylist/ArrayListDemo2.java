package arraylist;

import java.util.ArrayList;

public class ArrayListDemo2 {
    public static void main(String[] args) {
        // 删除成绩：删除分数小于 80 分的成绩
        ArrayList<Integer> list = new ArrayList<>();
        list.add(90);
        list.add(23);
        list.add(66);
        list.add(89);
        list.add(80);
        list.add(79);
        list.add(94);

        // 注意：每次删除之后序号会错位，导致有的会删不掉
        // 方案 1：每次删除后需要要减1 i--
        // 方案 2：从后往前倒着删
        int size = list.size();

        // 注意：这种方案长度那不能用前面的变量 size，要用 list.size() 动态去获取
        // for (int i = 0; i < size; i++) {
        for (int i = 0; i < list.size(); i++) {
            int score = list.get(i);
            if (score < 80) {
                list.remove(i);
                i--;
            }
        }

//        for (int i = size - 1; i >= 0; i--) {
//            int score = list.get(i);
//            if (score < 80) {
//                list.remove(i);
//            }
//        }

        System.out.println("删除小于 80 分后的成绩是");
        System.out.println(list);
    }
}

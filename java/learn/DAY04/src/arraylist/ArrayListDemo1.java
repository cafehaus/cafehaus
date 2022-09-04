package arraylist;

import java.util.ArrayList;

public class ArrayListDemo1 {
    public static void main(String[] args) {
        // 集合 ArrayList
        ArrayList<String> list = new ArrayList<>();
        list.add("javascript");
        list.add("node");
        list.add("swift");
        list.add("mysql");
        list.add("kotlin");

        // 1. 获取元素：get(索引)
        String one = list.get(1);
        System.out.println(one);

        // 2. 获取元素个数：size()
        int size = list.size();
        System.out.println(size);

        // 3. 遍历元素
        for (int i = 0; i < size; i++) {
            System.out.println(list.get(i));
        }

        // 4. 根据索引删除某个元素：remove(索引)，会返回删除的那个元素，这点很 javascript
        String delElement = list.remove(2);
        System.out.println("删除的那个元素是：" + delElement);
        System.out.println(list);

        // 5. 根据元素值删除某个元素：remove(元素值)，只会删除找到的第一个，删除成功返回 true
        Boolean delSuccess = list.remove("mysql");
        System.out.println("根据元素值 mysql 删除元素：" + delSuccess);
        System.out.println(list);

        // 6. 根据索引修改元素：set(int index, E element)
        list.set(0, "typescript");
        System.out.println("把第一个元素改成 typescript");
        System.out.println(list);
    }
}

package cn.cafe123.create;

public class MethodDemo4 {
    public static void main(String[] args) {
        // 找出整型数组中的最大值
        int[] numList = {12, 34, 89, 90, 108, 13, 80, 1, 32};
        int res = findMax(numList);
        System.out.println(res);
    }

    public static int findMax(int[] nums) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }
}

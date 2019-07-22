package com.sfpy.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author assen
 * @describe
 * @date 2019/7/22
 */
public class leetcode_1 {

    /*
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     */

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(twoSumForHashMap(new int[]{2, 1, 11, 15, 8}, 9)));
        System.out.println(Arrays.toString(twoSumForHashMap2(new int[]{2, 1, 11, 7, 8}, 9)));
    }

    /*
     * 暴力遍历法 (最优解)
     * O(n^2)
     * O(1)
     */
    public static int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /*
     * 两遍哈希表
     * O(n)
     * O(n)
     */
    public static int[] twoSumForHashMap(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (hashMap.containsKey(complement) && hashMap.get(complement) != i) {
                return new int[]{i, hashMap.get(complement)};
            }
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    /*
     * 一遍哈希表
     * O(n)
     * O(n)
     */
    public static int[] twoSumForHashMap2(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (hashMap.containsKey(complement)) {
                return new int[]{i, hashMap.get(complement)};
            }

            hashMap.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two sum solution");
    }
}

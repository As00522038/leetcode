package com.sfpy.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author assen
 * @describe
 * @date 2019/7/22
 */
public class leetcode_3 {

    /*
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */

    public static void main(String[] args) {
//        System.out.println(lengthOfLongestSubstring("abcdefgabab"));
//        System.out.println(lengthOfLongestSubstring2("abcabcdbb"));
        System.out.println(lengthOfLongestSubstring3("abcdefgabab"));
    }

    /*
     *
     * O(n^3)
     * O(min(n,m))
     * */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (allUnique(s, i, j)) {
                    ans = Math.max(ans, j - i); // 比较多个不重复字符的大小，取大的
                }
            }
        }
        return ans;
    }

    private static boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>(); // 判断字符串相应长度下是否存在重复字符
        for (int i = start; i < end; i++) {
            char c = s.charAt(i);
            if (set.contains(c))
                return false;
            set.add(c);
        }

        return true;
    }

    /*
     * O(n)
     * O(min(m,n))
     *
     * "abcabcdbb"为例子
     * 循环：
     * 1--> set={a}, ans=0, j=0, i=0
     * 2--> set={a,b}, ans=2, j=1, i=0
     * 3--> set={a,b,c}, ans=3, j=2, i=0
     * 4--> set={b,c}, ans=3, j=3, i=0
     * 5--> set={b,c,a}, ans=3, j=3, i=1
     * 6--> set={c,a,b}, ans=3, j=4, i=2
     * 7--> set={a,b,c}, ans=3, j=5, i=2
     * 8--> set={a,b,c,d}, ans=3, j=5, i=3
     * 9--> set={b,c,d}, ans=3, j=6, i=3
     *
     * ...
     * result=3
     * */
    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) { // 不包含加进set中
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /*
     * O(n)
     * O(min(m,n))
     * */
    public static int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                /*
                 * 如果包含key,获取第一次此字符出现的索引值，因为之前存取的数据是 j+1,从而实现自动向前进一位
                 * 而其hashMap键的唯一性，所以会覆盖当前的value，从而左边移除元素
                 * */
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}

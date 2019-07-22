package com.sfpy.leetcode;

import android.support.annotation.NonNull;

/**
 * @author assen
 * @describe
 * @date 2019/7/22
 */
public class leetcode_2 {

    /*
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * 拓展 ，逆向运算
     * 输入：(3 -> 4 -> 2) + (4 -> 6 -> 5)
     * 输出：8 -> 0 -> 7
     * 原因：342 + 465 = 807
     */

    public static void main(String[] args) {
//        ListNode l1 = new ListNode(2);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
//
//        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);
//
//        ListNode l3 = addTwoNumbers(l1, l2);
//
//        while (l3 != null) {
//            System.out.println(l3.val);
//            l3 = l3.next;
//        }

        // 逆向运算
        ListNode l1 = new ListNode(3);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(2);

        ListNode l2 = new ListNode(4);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(5);

        ListNode l3 = addTwoNumbers2(l1, l2);

        while (l3 != null) {
            System.out.println(l3.val);
            l3 = l3.next;
        }
    }

    /*
     * 逆向运算
     * O(max(m,n)) 两个数据源的每个节点都会计算一次
     * O(max(m,n)) 同样格外会开辟相依计算量的空间 (考虑到进位，最多执行 O(max(m,n)+1) )
     */
    public static ListNode addTwoNumbers(@NonNull ListNode l1, @NonNull ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) { // 条件语句 不为空执行
            int x = (p != null ? p.val : 0); // 取值
            int y = (q != null ? q.val : 0);
            int sum = carry + x + y; // 加法计算
            carry = sum / 10; // 计算余数
            curr.next = new ListNode(sum % 10); // 如果除不尽有余数就赋值给 下一位
            curr = curr.next;
            if (p != null) p = p.next; // 继续下次计算
            if (q != null) q = q.next;
        }
        if (carry > 0) { // 进位运算
            curr.next = new ListNode(carry);
        }

        return dummyHead.next;
    }

    /*
     * O(2max(m,n))
     * O(2max(m,n))
     */
    public static ListNode addTwoNumbers2(@NonNull ListNode l1, @NonNull ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) { // 条件语句 不为空执行
            int x = (p != null ? p.val : 0); // 取值
            int y = (q != null ? q.val : 0);
            int sum = carry + x + y; // 加法计算
            carry = sum / 10; // 计算余数
            curr.next = new ListNode(sum % 10); // 如果除不尽有余数就赋值给 下一位
            curr = curr.next;
            if (p != null) p = p.next; // 继续下次计算
            if (q != null) q = q.next;
        }
        if (carry > 0) { // 进位运算
            curr.next = new ListNode(carry);
        }

        ListNode node = dummyHead.next;
        ListNode pre = null;
        ListNode next;

        /*
         * 708为例子
         * 循环:
         * 1-->node 708, next = 08, node.next = null, pre = 7, node 08
         * 2-->node 08, next 8, node.next = 7, pre = 07, node = 8
         * 3-->node 8, next = null, node.next = 07, pre = 807, node = null
         *
         * result --> 807
         * */
        while (node != null) {
            next = node.next; // 保存当前数据
            node.next = pre; // 偷换数据，反向运算
            pre = node; // 存储数据
            node = next; // 继续计算
        }

        return pre;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

    }
}

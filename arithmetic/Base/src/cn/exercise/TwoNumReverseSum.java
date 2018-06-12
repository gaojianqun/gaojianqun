package cn.exercise;


/**
 * Created by gaojianqun on 2018/4/27.
 * 两个数反向相加
 * 问题描述
 * 给定两个链表分别代表两个非负整数，
 * 链表的每个结点分别存储整数的每位数字，且是逆序存储，
 * 即：数字最低位存储在链表表头，数字最高位存储在链表表尾。
 * 求解这两个整数的和并以相同的链表形式返回计算的结果。
 * 例如：   输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)   输出：7 -> 0 -> 8
 */
public class TwoNumReverseSum {

    public static void main(String[] args){
        int[] array = new int[]{8,4,3,5,6,3};
        int i = 0;
        int j = 3;
        //测试数据是否逆向添加
//        for (int p = 0;p < 3;p++){
//            ListNode l1 = new ListNode(array[i]);
//            System.out.print(l1.value+" ");
//            ++i;
//            l1.next = l1;
//        }
//
//        System.out.println();
//
//        for (int q = 0;q < 3;q++){
//            ListNode l2 = new ListNode(array[j]);
//            System.out.print(l2.value+" ");
//            ++j;
//            l2.next = l2;
//        }
        ListNode root = new ListNode(0);

        for (int p = 0;p < 3;p++){
            ListNode l1 = new ListNode(array[i]);
            ListNode l2 = new ListNode(array[j]);

            if(root.next!=null){
                root.value = l1.value + l2.value + 1;
            }else{
                root.value = l1.value + l2.value;
            }
            if(root.value / 10 == 0){
                System.out.println(root.value);
            }else{
//                root.next = new ListNode(root.value/10);
                root.next = new ListNode(root.value);
                System.out.println((root.value % 10));
            }

            ++i;
            ++j;

            if(l1.next == null || l2.next == null){
                System.out.println((root.next.value / 10));
            }
        }

    }

    private static class ListNode {

        //节点的数据结构
        int value;
        ListNode next;

        public ListNode(int value){
            this.value = value;
        }

    }

}


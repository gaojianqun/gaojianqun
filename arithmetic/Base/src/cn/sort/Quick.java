package cn.sort;

/**
 * Created by gaojianqun on 2018/3/27.
 * 时间复杂度为O(nlogn)
 * 6,1,2,7,9,3,4,5,10,8
 * 第一趟排序
 * 5 1 2 7 9 3 4 6 10 8
 * 5 1 2 6 9 3 4 7 10 8
 * 5 1 2 4 9 3 6 7 10 8
 * 5 1 2 4 6 3 9 7 10 8
 * 5 1 2 4 3 6 9 7 10 8
 * 5 1 2 4 3 6 9 7 10 8
 * 第二趟排序
 * 3 1 2 4 5 6 9 7 10 8
 * 3 1 2 4 5 6 9 7 10 8
 * 2 1 3 4 5 6 9 7 10 8
 * 第三趟排序
 * 2 1 3 4 5 6 9 7 10 8
 * 1 2 3 4 5 6 9 7 10 8
 * 第四趟排序
 * 1 2 3 4 5 6 9 7 10 8
 * 第五趟排序
 * 1 2 3 4 5 6 8 7 10 9
 * 1 2 3 4 5 6 8 7 9 10
 * 1 2 3 4 5 6 8 7 9 10
 * 1 2 3 4 5 6 8 7 9 10
 * 第六趟排序
 * 1 2 3 4 5 6 7 8 9 10
 * 1 2 3 4 5 6 7 8 9 10
 * 第七趟排序
 * 问题解决，排序结束
 */
public class Quick {

    public static void main(String[] args){
        int[] a = {6,1,2,7,9,3,4,5,10,8};
        int start = 0;
        int end = a.length-1;
        sort(a,start,end);
    }

    //具体排序
    public static void sort(int[] array,int s,int e){
        //设置关键数字
        int start = s;
        int end = e;
        int key = array[s];


        while(end > start){
            //哨兵开始运动右序列
            while(end>start&&array[end]>=key){
                end--;
            }
            //哨兵查找右序列中小于关键值的位置以及数值，如果小于的话交换位置
            if(array[end] <= key){
                //交换位置
                int temp = array[end];
                array[end] = array[start];
                array[start] = temp;
            }

            for(int i=0;i<array.length;i++){
                System.out.print(array[i]+" ");
            }
            System.out.println();


            //哨兵开始运动左序列
            while(end>start&&array[start]<=key){
                start++;
            }
            //哨兵查找右序列中小于关键值的位置以及数值，如果小于的话交换位置
            if(array[start] >= key){
                //交换位置
                int temp = array[start];
                array[start] = array[end];
                array[end] = temp;
            }

            for(int i=0;i<array.length;i++){
                System.out.print(array[i]+" ");
            }
            System.out.println();
        }

        //递归循环分治思想
        if(start>s){
            sort(array,s,start-1);//左边序列。因为此时的定值已经在指定的索引上
        }

        if(end<e){
            sort(array,end+1,e);//右边序列。
        }

    }

}

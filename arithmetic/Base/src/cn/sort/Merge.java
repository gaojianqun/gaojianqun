package cn.sort;

import java.util.Arrays;

/**
 * Created by gaojianqun on 2018/3/22.
 * 归并排序：分治思想
 * 时间复杂度为O(nlog₂n) 这是该算法中最好、最坏和平均的时间性能。
 * 空间复杂度为 O(n)
 * 比较操作的次数介于(nlogn) / 2和nlogn - n + 1。
 * 赋值操作的次数是(2nlogn)。归并算法的空间复杂度为：0 (n)
 * 归并排序比较占用内存，但却是一种效率高且稳定的算法。
 *
 */
public class Merge {

    public static void main(String []args){
        int[] array = new int[]{49,38,65,97,76,13,27,49,78};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    //分
    public static void sort(int []arr){
        //在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        int []temp = new int[arr.length];
        sort(arr,0,arr.length-1,temp);
    }
    private static void sort(int[] arr,int left,int right,int []temp){
        if(left<right){
            int mid = (left+right)/2;
            //左边归并排序，使得左子序列有序
            sort(arr,left,mid,temp);
            //右边归并排序，使得右子序列有序
            sort(arr,mid+1,right,temp);
            //将两个有序子数组合并操作
            merge(arr,left,mid,right,temp);
        }
    }

    //治
    private static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;//左序列指针
        int j = mid+1;//右序列指针
        int t = 0;//临时数组指针
        while (i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        while(i<=mid){//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while(j<=right){//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while(left <= right){
            arr[left++] = temp[t++];
        }
    }

}

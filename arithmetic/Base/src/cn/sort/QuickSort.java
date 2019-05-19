package cn.sort;

public class QuickSort {

    public static void main(String[] args){
        int[] a = {6,1,2,7,9,3,4,5,10,8};
        int low = 0;
        int high = a.length-1;
        sort(a,low,high);
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
    }

    public static void sort(int[] array,int low,int high){
        int start = low;
        int end = high;
        int key = array[low];

        while(end > start){

            while (end>start && array[end]>=key){
                end--;
            }

            if(array[end] <= key){
                int temp = array[end];
                array[end] = array[start];
                array[start] = temp;
            }

            while(end >start && array[start]<=key){
                start++;
            }

            if(array[start] >= key){
                int temp = array[start];
                array[start] = array[end];
                array[end] = temp;
            }

        }

        //递归
        if(start>low){
            sort(array,low,start-1);
        }

        if(end<high){
            sort(array,end+1,high);
        }

    }

}

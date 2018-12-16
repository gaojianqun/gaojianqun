package com.sort;

public class QuickSort {

    public static void main(String [] args){
        int [] array = {2,10,5,8,3,6,1,4,9,7};
        int low = 0,high = array.length - 1;
        sort(array,low,high);

        for(int i = 0;i < array.length;i++){
            System.out.print(array[i]+" ");
        }

    }

    public static void sort(int[] array,int low,int high){
       int start = low,end = high;
       int key = array[start];
       while(start < end){
           while (key <= array[end] && end > start){
               end --;
           }

           if(key >= array[end]){
               int temp = array[end];
               array[end] = array[start];
               array[start] = temp;
           }

           while(key >= array[start] && start < end){
               start ++;
           }

           if(key <= array[start]){
               int temp = array[start];
               array[start] = array[end];
               array[end] = temp;
           }

           if(start > low){
               sort(array,low,(start-1));
           }

           if(end < high){
               sort(array,(end+1),high);
           }
       }

    }

}

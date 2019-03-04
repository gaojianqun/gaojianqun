package com.sort;

public class QuickSort {

    public static void main(String [] args){
        int [] array = {6,1,2,7,9,3,4,5,10,8};
        int low = 0,high = array.length - 1;
        sort(array,low,high);

        for(int i = 0;i < array.length;i++){
            System.out.print(array[i]+" ");
        }

    }

    public static void sort(int[] array,int low,int high){
       int start = low,end = high;
       while(start < end){

           int key = array[start];

           while (key <= array[end] && end > start){
               end--;
           }

           if(array[end] <= key){
               int temp = array[end];
               array[end] = array[start];
               array[start] = temp;
           }

            while (key >= array[start] && start < end){
                start ++;
            }

            if(array[start] >= key){
                int temp = array[start];
                array[start] = array[end];
                array[end] = temp;
            }
       }


        if(start > low){
            sort(array,low,(start-1));
        }

        if(end < high){
            sort(array,(end+1),high);
        }

    }

}

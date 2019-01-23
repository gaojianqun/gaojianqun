package cn.Search.binary;

/**
 * 二分查找
 * a[10] : 1 3 4 5 6 8 8 8 11 18
 * 给定value= 8，要输出第一个8 的位置
 */
public class BinarySearch1 {

    public static void main(String [] args){
        int [] array = {1,3,4,5,8,4,8,8,11,18};
        int index = binarySearch(array,(array.length),8);
        System.out.println(index);
    }

    public static int binarySearch(int[] array ,int n,int value){
        int low = 0;
        int high = n - 1;
        while(low <= high){
            int mid = low + ((high-low)>>1);
            if(array[mid] > value){
                high = mid - 1;
            }else if(array[mid] < value){
                low = mid + 1;
            }else{
                if((mid == 0) || (array[mid-1] != value)){
                    return mid;
                }else{
                    high = mid - 1;
                }
                return mid;
            }
        }
        return -1;
    }


}

package cn.Search;


/**
 * Created by gaojianqun on 2018/12/29.
 * 二分查找
 * 随机一个数组 0 到 99 之间的数字，查询23在数组中的位置
 */
public class BinarySearch {

    public static void main(String [] args){
        int [] array = new int[]{1,2,3,4,5,6,7,8,9,10,
                                 11,12,13,14,15,16,17,18,19,20,
                                 21,22,23,24,25,26,27,28,29,30,
                                 31,32,33,34,35,36,37,38,39,40,
                                 41,42,43,44,45,46,47,48,49,50,
                                 51,52,53,54,55,56,57,58,59,60,
                                 61,62,63,64,65,66,67,68,69,70,
                                 71,72,73,74,75,76,77,78,79,80,
                                 81,82,83,84,85,86,87,88,89,90,
                                 91,92,93,94,95,96,97,98,99};
        int index = binarySearch(array,(array.length),23);
        System.out.println(index);
    }

    public static int binarySearch(int [] array,int n,int value){
        int low = 0;
        int high = n-1;

        while(low <= high){
            int mid = (low + high)/2;
            if(array[mid] == value){
                return mid;
            }else if(array[mid] < value){
                low = mid + 1;
            }else{
                high = mid -1;
            }
        }

        return -1;
    }


}

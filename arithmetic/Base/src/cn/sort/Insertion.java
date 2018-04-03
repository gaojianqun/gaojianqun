package cn.sort;

/**
 * 排序算法的时间效率取决于比较的次数
 * Created by gaojianqun on 2018/3/16.
 * 插入排序
 * 比较次数：N的二次方/4
 * 交换次数：N的二次方/4
 * 最坏情况的比较次数和交换次数：N的二次方/2
 * 时间复杂度为O(n^2)
 */
public class Insertion {
    public static void main(String [] args){
        int[] array = new int[]{21,1,3,0,2,4,5,6,10,7,8};
        sort(array);
    }

    public static void sort(int [] array){
        for(int i=0; i < array.length; i++){
            //要将j定位到是否大于0
            for(int j=i;j > 0 && array[j] < array[j-1];j--){
                int p = array[j];
                array[j] = array[j-1];
                array[j-1] = p;
            }
        }

        for(int q = 0;q < array.length; q++){
            System.out.print(array[q] + " ");
        }
    }

}

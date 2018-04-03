package cn.sort;

/**
 * 排序算法的时间效率取决于比较的次数
 * Created by gaoajianqun on 2018/3/16.
 * 选择排序
 * 比较次数：N的二次方/2
 * 交换次数：N
 * 时间复杂度为O(n^2)
 */
public class Selection {
    public static void main(String [] args){
        int[] array = new int[]{21,1,3,0,2,4,5,6,10,7,8};
        sort(array);
    }

    public static void sort(int [] array){
        for(int i = 0; i < array.length;i ++){
            int min = array[i];
            for(int j = i + 1; j < array.length && array[j] > min; j++){
                int p = array[j];
                array[j] = min;
                min = p;
            }
        }

        for(int i=0;i < array.length;i++){
            System.out.print(array[i] + " ");
        }
    }

}

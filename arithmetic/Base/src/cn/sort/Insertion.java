package cn.sort;


/**
 * 排序算法的时间效率取决于比较的次数
 * Created by gaojianqun on 2018/3/16.
 * 插入排序
 * 比较次数：N的二次方/4
 * 交换次数：N的二次方/4
 * 最坏情况的比较次数和交换次数：N的二次方/2
 * 时间复杂度为O(n^2)
 * 100个随机数 1:2毫秒 2:2毫秒
 * 500个随机数 1:18毫秒 2:12毫秒
 * 1000个随机数 1:25毫秒 2:27毫秒
 * 2000个随机数 1:45毫秒 2:45毫秒
 * 3000个随机数 1:55毫秒 2:43毫秒
 * 4000个随机数 1:58毫秒 2:60毫秒
 * 5000个随机数 1:72毫秒 2:75毫秒
 */
public class Insertion {
    public static void main(String [] args){
        int[] array = new int[]{21,1,3,0,2,4,5,6,10,7,8};
        sort2(array);
    }

    public static void sort(int [] array){
        System.out.println(System.currentTimeMillis());
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

        System.out.println();
        System.out.println(System.currentTimeMillis());
    }

    public static void sort2(int[] array) {
        System.out.println(System.currentTimeMillis());

        int i, j, key;// 要插入的数据
        for (i = 1; i < array.length; i++) {// 从数组的第二个元素开始循环将数组中的元素插入
            key = array[i];// 设置数组中的第2个元素为第一次循环要插入的数据
//            j = i-1;
//            while(j >= 0 && key < array[j]){
//                array[j + 1] = array[j];// 如果要插入的元素小于第j个元素,就将第j个元素向后移动
//                j--;
//            }
            for( j = i-1;j >= 0 && key < array[j];j--){
                array[j + 1] = array[j];// 如果要插入的元素小于第j个元素,就将第j个元素向后移动
            }
            array[j + 1] = key;// 直到要插入的元素不小于第j个元素,将key插入到数组中
        }

        for(int q = 0;q < array.length; q++){
            System.out.print(array[q] + " ");
        }

        System.out.println();

        System.out.println(System.currentTimeMillis());
    }

}

package cn.sort;

/**
 * Created by gaojianqun on 2018/3/15.
 * 希尔排序：适应于大批量数据排序
 * 1.取决于容器的长度
 * 2.增量序列：13个数据/2 = 6 ； 6/2 = 3 ；3/2 = 1
 *
 * 第一趟排序：27，38，65，34，12，13，49，49，78，97，76，64，1
 * 第二趟排序：1，12，13，27，38，64，34，49，65，49，76，78，97
 * 第三趟排序：1,12,13,27,34,38,49,49,64,65,76,78,97
 */
public class Shell {

    public static void main(String [] args){
        int[] array = new int[]{49,38,65,97,76,13,27,49,78,34,12,64,1};
        sort(array);
    }

    public static void sort(int[] array){
        int d = array.length;
        while(true){
            //定义增量序列
            d = d/2;
            //将增量序列看成一个新的数组，在这个增量序列中进行插入排序
            for(int x = 0; x< d; x++){
                //从第一个数开始组成增量序列
                for(int i = x+d;i < array.length; i = i+d){
                    //定值
                    int temp = array[i];
                    //插入排序
                    for(int j = i-d;j >= 0 && array[j] > temp;j = j-d){
                        //交换
                        int p = array[j+d];
                        array[j+d] = array[j];
                        array[j] = p;
                    }
                }
            }

            if(d==1){
                break;
            }
        }

        //输出
        for(int q = 0;q<array.length;q++){
            System.out.print(array[q]+" ");
        }
    }

}

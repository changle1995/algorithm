package com.changle.algorithm.sort;

import com.changle.algorithm.sort.impl.QuickSort;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序测试类
 *
 * @author changle
 * @date 2019/11/01
 **/
public class QuickSortTest {

    @Test
    public void testSort() {
        int times = 10000;
        int length = 10000;
        boolean equals = true;
        Sort sort = new QuickSort();
        for (int i = 1; i <= times; i++) {
//            System.out.println("---------第" + i + "次-----------");
            if (!testSortOnce(length, sort)) {
                equals = false;
                break;
            }
        }
        System.out.println("最终结果是否全部相等：" + equals);
    }

    public boolean testSortOnce(int length, Sort sort) {
        int[] array = new int[length];
        int[] array1 = new int[length];
        int[] array2 = new int[length];
        Random random = new Random();
        for (int j = 0; j < array.length; j++) {
            array[j] = random.nextInt(10000);
        }

//        System.out.println("*********************************");
//        System.out.println("原数组：" + Arrays.toString(array));
//        System.out.println("*********************************");

//        System.out.println("*********************************");
        System.arraycopy(array, 0, array1, 0, length);
        sort.sort(array1);
//        System.out.println("自定义排序：" + Arrays.toString(array1));
//        System.out.println("*********************************");

//        System.out.println("*********************************");
        System.arraycopy(array, 0, array2, 0, length);
        Arrays.sort(array2);
//        System.out.println("系统排序：" + Arrays.toString(array2));
//        System.out.println("*********************************");

        boolean equals = Arrays.equals(array1, array2);
//        System.out.println("是否相等：" + equals);
        return equals;
    }
}

package com.changle.algorithm.sort;

import com.changle.algorithm.sort.impl.QuickSort;
import com.changle.algorithm.sort.impl.QuickSort2;
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
        // 测试次数
        int times = 10000;
        // 测试数组长度
        int length = 10000;
        // 测试数组中最大值
        int max = 10000;
        boolean equals = true;
        Sort sort1 = new QuickSort();
        Sort sort2 = new QuickSort2();
        int[] array = getArray(length, max);

        long startTime1 = System.currentTimeMillis();
        for (int i = 1; i <= times; i++) {
//            System.out.println("---------第" + i + "次-----------");
            if (!testSortOnce(array, sort1)) {
                equals = false;
                break;
            }
        }
        System.out.println("最终结果是否全部相等：" + equals);
        long endTime1 = System.currentTimeMillis();
        System.out.println(endTime1 - startTime1);

        long startTime2 = System.currentTimeMillis();
        for (int i = 1; i <= times; i++) {
//            System.out.println("---------第" + i + "次-----------");
            if (!testSortOnce(array, sort2)) {
                equals = false;
                break;
            }
        }
        System.out.println("最终结果是否全部相等：" + equals);
        long endTime2 = System.currentTimeMillis();
        System.out.println(endTime2 - startTime2);
    }

    public int[] getArray(int length, int max) {
        int[] array = new int[length];
        Random random = new Random();
        for (int j = 0; j < array.length; j++) {
            array[j] = random.nextInt(max);
        }
        return array;
    }

    public boolean testSortOnce(int[] array, Sort sort) {
        int[] array1 = new int[array.length];
        int[] array2 = new int[array.length];

//        System.out.println("*********************************");
//        System.out.println("原数组：" + Arrays.toString(array));
//        System.out.println("*********************************");

//        System.out.println("*********************************");
        System.arraycopy(array, 0, array1, 0, array.length);
        sort.sort(array1);
//        System.out.println("自定义排序：" + Arrays.toString(array1));
//        System.out.println("*********************************");

//        System.out.println("*********************************");
        System.arraycopy(array, 0, array2, 0, array.length);
        Arrays.sort(array2);
//        System.out.println("系统排序：" + Arrays.toString(array2));
//        System.out.println("*********************************");

        boolean equals = Arrays.equals(array1, array2);
//        System.out.println("是否相等：" + equals);
        return equals;
    }
}

package com.changle.algorithm.sort;

import com.changle.algorithm.sort.impl.BubbleSort;
import com.changle.algorithm.sort.impl.InsertionSort;
import com.changle.algorithm.sort.impl.QuickSort;
import com.changle.algorithm.sort.impl.QuickSort2;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * 排序测试类
 *
 * @author changle
 * @date 2019/11/01
 **/
public class SortTest {

    @Test
    public void testSort() {
        // 测试次数
        int times = 100;
        // 测试数组长度
        int length = 10000;
        // 测试数组中最大值
        int max = 10000;
        // 生成待排序数组
        int[] array = getArray(length, max);

        // 各种排序算法比较
        // 换排序算法只需要修改下面这一行的实现类
        System.out.println("---冒泡---");
        testSort(times, array, new BubbleSort());
        System.out.println("---插入---");
        testSort(times, array, new InsertionSort());
        System.out.println("---快排1版---");
        testSort(times, array, new QuickSort());
        System.out.println("---快排2版---");
        testSort(times, array, new QuickSort2());
    }

    /**
     * 使用传入的排序算法对传入的数组进行指定此数的排序
     *
     * @param times 排序此数
     * @param array 待排序数组
     * @param sort  排序算法
     */
    public void testSort(int times, int[] array, Sort sort) {
        boolean equals = true;
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= times; i++) {
//            System.out.println("---------第" + i + "次-----------");
            if (!testSortOnce(array, sort)) {
                equals = false;
                break;
            }
        }
        System.out.println("是否正确排序：" + equals);
        long endTime = System.currentTimeMillis();
        System.out.println("比较" + times + "次总耗时：" + (endTime - startTime));
    }

    /**
     * 按照长度和最大值生成数组
     *
     * @param length
     * @param max
     * @return
     */
    public int[] getArray(int length, int max) {
        int[] array = new int[length];
        Random random = new Random();
        for (int j = 0; j < array.length; j++) {
            array[j] = random.nextInt(max);
        }
        return array;
    }

    /**
     * 判断排序算法是否能正确排序
     *
     * @param array
     * @param sort
     * @return
     */
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

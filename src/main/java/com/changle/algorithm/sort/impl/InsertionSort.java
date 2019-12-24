package com.changle.algorithm.sort.impl;

import com.changle.algorithm.sort.Sort;

/**
 * 插入排序.<br>
 * 将数组分为左边有序区与右边无序区，每次从无序区挑一个插入到有序区直至结束
 * 时间复杂度平均为O(n^2)，最好为O(n)，最差为O(n^2)
 * 是稳定的排序算法
 *
 * @author changle
 * @version 1.0.0
 * @date 2019/12/06
 **/
public class InsertionSort implements Sort {

    @Override
    public void sort(int[] array) {
        // 数据合法性判断
        if (array == null || array.length < 2) {
            return;
        }
        // 开始从右边无序区选择元素插入到左边有序区
        // 变量i表示从array[1]开始取数
        for (int i = 1; i < array.length; i++) {
            // 变量j表示从i开始向前比较，如果右边的比左边的小就交换
            for (int j = i; j > 0; j--) {
                if (array[j - 1] > array[j]) {
                    swap(array, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

}

package com.changle.algorithm.sort.impl;

import com.changle.algorithm.sort.Sort;

/**
 * 选择排序.<br>
 * 每次循环找出此次最小的值，放在已经排序区的最后
 * 时间复杂度平均为O(n^2)，最好为O(n^2)，最差为O(n^2)
 * 是不稳定的排序算法
 *
 * @author changle
 * @version 1.0.0
 * @date 2019/12/06
 **/
public class SelectioinSort implements Sort {

    @Override
    public void sort(int[] array) {
        // 数据合法性判断
        if (array == null || array.length < 1) {
            return;
        }
        // 变量i用来控制遍历的次数
        for (int i = 0; i < array.length - 1; i++) {
            // 用来记录最小值的下标
            int index = i;
            // 开始比较与获取此次循环中的最小值下标
            for (int j = i + 1; j < array.length; j++) {
                // 如果当前j下标表示的元素比index表示的元素小，则修改index为j的值
                if (array[j] < array[index]) {
                    index = j;
                }
            }
            // 一次循环结束之后交换当前元素与最小元素
            swap(array, i, index);
        }
    }

}

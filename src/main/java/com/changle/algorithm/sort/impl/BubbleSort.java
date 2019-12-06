package com.changle.algorithm.sort.impl;

import com.changle.algorithm.sort.Sort;

/**
 * 冒泡排序.<br>
 * 每次排序找出最大的数排在最后，因此每次排序之后需要排序的数都少一个
 *
 * @author changle
 * @version 1.0.0
 * @date 2019/12/06
 **/
public class BubbleSort implements Sort {

    @Override
    public void sort(int[] array) {
        // 数据合法性判断
        if (array == null || array.length < 2) {
            return;
        }
        // 开始比较
        // 变量i可以看做用于控制比较次数，也可以理解为控制每次待排序的数组长度
        for (int i = 1; i < array.length; i++) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成
            boolean flag = true;
            // 变量j用于控制一次比较中需要比较的元素个数，或者理解为每次排序时最后一个元素的下标
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

}

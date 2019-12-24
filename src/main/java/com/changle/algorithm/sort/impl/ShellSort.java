package com.changle.algorithm.sort.impl;

import com.changle.algorithm.sort.Sort;

/**
 * 希尔排序.<br>
 * 是插入排序的改进版
 * 为了减少数据的移动次数，在初始序列较大时取较大的步长进行比较交换，之后步长依次减少直至步长为1
 * 每次比较实用的是直接插入排序，由于最后序列已接近有序，故插入元素时数据移动的次数会相对较少，效率得到了提高
 * 时间复杂度平均为O(n^1.3)，最好为O(n)，最差为O(n^2)
 * 是不稳定的排序算法
 *
 * @author changle
 * @version 1.0.0
 * @date 2019/12/24
 **/
public class ShellSort implements Sort {

    @Override
    public void sort(int[] array) {
        // 数据合法性判断
        if (array == null || array.length < 1) {
            return;
        }
        // 每次比较中的步长
        int gap = array.length;
        while (gap >= 1) {
            // 每次步长减半
            gap = gap / 2;
            // i控制每一组的起始元素下标
            for (int i = 0; i < gap; i++) {
                // 下面的for循环其实是一个插入排序
                // 使用直接插入排序对一组元素进行排序
                // 每次比较的一组元素为i,i+gap,i+2gap...
                for (int j = i + gap; j < array.length; j += gap) {
                    // 变量k表示从j开始向前比较，如果右边的比左边的小就交换，否则此次比较结束
                    for (int k = j - gap; k >= 0; k -= gap) {
                        if (array[k] > array[k + gap]) {
                            swap(array, k, k + gap);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

}

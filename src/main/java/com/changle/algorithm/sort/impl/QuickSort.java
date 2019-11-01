package com.changle.algorithm.sort.impl;

import com.changle.algorithm.sort.Sort;

import java.util.Random;

/**
 * 快速排序算法类
 *
 * @author changle
 * @date 2019/11/01
 **/
public class QuickSort implements Sort {

    /**
     * 快速排序算法
     *
     * @param array 待排序数组
     */
    @Override
    public void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    /**
     * 快速排序算法
     *
     * @param array 待排序数组
     * @param start 排序的起始下标
     * @param end   排序的结束下标
     */
    public void sort(int[] array, int start, int end) {
        // 参数验证必须满足以下所有条件
        // 1. 数组不空
        // 2. 数组中有值
        // 2. 起始下标小于结束下标
        // 3. 起始下标非负
        // 4. 结束下标小于数组长度
        if (array != null
                && array.length > 0
                && start < end
                && start >= 0
                && end < array.length) {
            // 取一个随机下标
            int randomNumber = new Random().nextInt(end - start + 1) + start;
            // 将基准值和起始指交换一个位置，为排序做准备
            swap(array, start, randomNumber);
            // 进行一次排序
            int index = partition(array, start, end);
            // 递归排序前半部分
            sort(array, start, index - 1);
            // 递归排序后半部分
            sort(array, index + 1, end);
        }
    }

    /**
     * 单次排序并返回基准值下标
     *
     * @param array 待排序数组
     * @param start 排序的起始下标
     * @param end   排序的结束下标
     * @return 基准值下标
     */
    public int partition(int[] array, int start, int end) {
        // 取第一个元素为基准值
        int flag = array[start];
        // 开始以基准值为参考向两边移动
        while (start < end) {
            // 两个指针还未相遇并且队尾元素大于等于基准数据时，向前挪动end指针
            while (start < end && array[end] >= flag) {
                end--;
            }
            // 如果队尾元素小于基准值了，将其赋值给start
            array[start] = array[end];
            // 两个指针还未相遇并且队首元素小于等于基准值时，向后挪动start指针
            while (start < end && array[start] <= flag) {
                start++;
            }
            // 队首元素大于基准值时，将其赋值给end
            array[end] = array[start];
        }
        // 跳出循环时start和end相等，该位置就是基准值的正确位置，将基准值赋给这个位置
        array[start] = flag;
        return start;
    }

    /**
     * 交换数组中两个位置的值
     *
     * @param array 需要进行交换值操作的数组
     * @param i     待交换值的第一个位置
     * @param j     待交换值的第二个位置
     */
    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

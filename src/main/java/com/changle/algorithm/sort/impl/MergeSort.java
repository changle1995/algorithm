package com.changle.algorithm.sort.impl;

import com.changle.algorithm.sort.Sort;

/**
 * 归并排序.<br>
 * 归并排序的核心思想是分治法，运用递归最终变成只有两个元素的的比较排序
 * 时间复杂度平均为O(nlogn)，最好为O(nlogn)，最差为O(nlogn)
 * 是稳定的排序算法
 *
 * @author changle
 * @version 1.0.0
 * @date 2019/12/24
 **/
public class MergeSort implements Sort {

    @Override
    public void sort(int[] array) {
        // 数据合法性判断
        if (array == null || array.length < 1) {
            return;
        }
        // 传入数组进行排序
        int[] result = mergeSort(array, 0, array.length - 1);
        // 赋予结果
        System.arraycopy(result, 0, array, 0, array.length);
    }

    public int[] mergeSort(int[] array, int low, int high) {
        if (low == high) {
            return new int[]{array[low]};
        }
        // 取下标的中间值
        int mid = low + (high - low) / 2;
        // 递归获取左有序数组
        int[] leftArr = mergeSort(array, low, mid);
        // 递归获取右有序数组
        int[] rightArr = mergeSort(array, mid + 1, high);
        // 为新有序数组开辟空间
        int[] newArr = new int[leftArr.length + rightArr.length];
        // 将左右两个有序数组合并放入新的有序数组中
        int k = 0, leftIndex = 0, rightIndex = 0;
        // 左右两个数组还没有取完时分别比较取较小的放入新数组中
        while (leftIndex < leftArr.length && rightIndex < rightArr.length) {
            newArr[k++] = leftArr[leftIndex] < rightArr[rightIndex] ? leftArr[leftIndex++] : rightArr[rightIndex++];
        }
        // 表示右边有序数组已经取完，将剩余的左侧数组依次都填入新数组
        while (leftIndex < leftArr.length) {
            newArr[k++] = leftArr[leftIndex++];
        }
        // 表示左边有序数组已经取完，将剩余的右侧数组依次都填入新数组
        while (rightIndex < rightArr.length) {
            newArr[k++] = rightArr[rightIndex++];
        }
        return newArr;
    }

}

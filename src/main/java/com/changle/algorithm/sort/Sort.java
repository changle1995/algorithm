package com.changle.algorithm.sort;

/**
 * 排序接口<br>
 * 所有的排序算法都要实现这个接口
 *
 * @author changle
 * @version 1.0.0
 * @date 2019/11/01
 */
public interface Sort {

    /**
     * 对传入的数组进行排序
     *
     * @param array 待排序的数组
     */
    void sort(int[] array);

    /**
     * 交换数组中两个位置的值
     *
     * @param array 需要进行交换值操作的数组
     * @param i     待交换值的第一个位置
     * @param j     待交换值的第二个位置
     */
    default void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}

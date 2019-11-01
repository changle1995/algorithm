package com.changle.algorithm;

/**
 * 排序接口
 * <p>
 * 所有的排序算法都要实现这个接口
 */
public interface Sort {

    /**
     * 对传入的数组进行排序
     *
     * @param array 待排序的数组
     */
    void sort(int[] array);
}

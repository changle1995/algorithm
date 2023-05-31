package com.changle.algorithm.tree;

import lombok.Data;

/**
 * 树节点存储的元素对象
 *
 * @author changle
 * @date 2023-05-30
 **/
@Data
public class Element<K extends Comparable<K>, V> {
    /**
     * 节点元素的key值
     */
    private K key;
    /**
     * 节点元素的value值
     */
    private V value;
}

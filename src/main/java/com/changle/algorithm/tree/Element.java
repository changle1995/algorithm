package com.changle.algorithm.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 树节点存储的元素对象
 *
 * @author changle
 * @date 2023-05-30
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Element<K extends Comparable<K>, V> {
    /**
     * 节点元素的key值
     */
    private K key;
    /**
     * 节点元素的value值
     */
    private V value;

    @Override
    public String toString() {
        return "[key=" + key + ", value=" + value + "]";
    }
}

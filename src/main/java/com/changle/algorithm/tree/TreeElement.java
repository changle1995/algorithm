package com.changle.algorithm.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 树节点元素
 *
 * @author changle05@meituan.com
 * @version 1.0
 * @date 2023-05-30
 */
@Data
@AllArgsConstructor
public class TreeElement<K extends Comparable<K>, V> {
    /**
     * 节点数据对应的key值
     */
    private K key;
    /**
     * 节点数据对应的value值
     */
    private V value;

    @Override
    public String toString() {
        return "[key=" + key + ", value=" + value + "]";
    }
}

package com.changle.algorithm.tree.rbt;

import com.changle.algorithm.tree.Element;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 红黑树节点
 *
 * @author changle
 * @date 2023-05-30
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Node<K extends Comparable<K>, V> {
    /**
     * 节点元素值
     */
    private Element<K, V> element;
    /**
     * 父节点
     */
    private Node<K, V> parent;
    /**
     * 左子节点
     */
    private Node<K, V> left;
    /**
     * 右子节点
     */
    private Node<K, V> right;
    /**
     * 节点的颜色
     */
    private ColorEnum color;

    public Node(Element<K, V> element, ColorEnum color) {
        this.element = element;
        this.color = color;
    }

    public Node(Element<K, V> element, Node<K, V> parent, ColorEnum color) {
        this.element = element;
        this.parent = parent;
        this.color = color;
    }
}

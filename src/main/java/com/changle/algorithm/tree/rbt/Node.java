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

    public Node(Element<K, V> element) {
        this.element = element;
    }

    /**
     * 在当前节点上插入一个新的节点
     *
     * @param node 需要插入的节点
     */
    public void addNode(Node<K, V> node) {
        if (node == null) {
            return;
        }
        // 比较需要插入的节点与当前节点
        if (node.element.getKey().compareTo(element.getKey()) < 0) {
            // 插入节点元素小于当前节点元素
            if (left == null) {
                // 当前节点左子节点为空，直接设置为当前节点的左子结点
                left = node;
                node.parent = this;
            } else {
                // 当前节点左子节点不为空，递归调用左子结点进行插入
                left.addNode(node);
            }
        } else if (node.element.getKey().compareTo(element.getKey()) == 0) {
            // 插入节点元素等于当前节点元素
            element = node.element;
        } else {
            // 插入节点元素大于当前节点元素
            if (right == null) {
                // 当前节点右子节点为空，直接设置为当前节点的右子结点
                right = node;
                node.parent = this;
            } else {
                // 当前节点右子节点不为空，递归调用右子结点进行插入
                right.addNode(node);
            }
        }
    }

    @Override
    public String toString() {
        return "【key:" + element.getKey() +
                ",color:" + color.getRemark() +
                "】";
    }
}

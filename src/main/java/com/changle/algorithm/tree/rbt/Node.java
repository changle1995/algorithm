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

    /**
     * 通过元素值查找节点
     *
     * @param element 元素值
     * @return 元素值对应的节点
     */
    public Node<K, V> getNode(Element<K, V> element) {
        if (element.getKey().compareTo(this.element.getKey()) == 0) {
            return this;
        } else if (element.getKey().compareTo(this.element.getKey()) < 0) {
            if (left != null) {
                return left.getNode(element);
            }
        } else {
            if (right != null) {
                return right.getNode(element);
            }
        }
        return null;
    }

    /**
     * 找当前节点的前驱节点
     *
     * @return 当前节点的前驱节点
     */
    public Node<K, V> getPredecessor() {
        // 若存在左子节点，则左子节点的循环右子节点为前驱
        if (left != null) {
            Node<K, V> node = left;
            while (node.right != null) {
                node = node.right;
            }
            return node;
        }

        // 不存在左子节点，则父节点循环到不是左子节点为前驱节点
        Node<K, V> node = parent;
        while (node != null && node == node.parent.left) {
            node = node.parent;
        }
        return node;
    }

    /**
     * 找当前节点的后继节点
     *
     * @return 当前节点的后继节点
     */
    public Node<K, V> getSuccessor() {
        // 若存在右子节点，则左子节点的循环右子节点为后继
        if (right != null) {
            Node<K, V> node = right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        // 不存在左子节点，则父节点循环到不是右子节点为后继节点
        Node<K, V> node = parent;
        while (node != null && node == node.parent.right) {
            node = node.parent;
        }
        return node;
    }

    @Override
    public String toString() {
        return "【key:" + element.getKey() +
                ",color:" + color.getRemark() +
                "】";
    }
}

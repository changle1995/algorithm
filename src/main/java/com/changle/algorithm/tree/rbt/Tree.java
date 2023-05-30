package com.changle.algorithm.tree.rbt;

import com.changle.algorithm.tree.Element;
import lombok.Data;

/**
 * 红黑树结构
 *
 * @author changle
 * @date 2023-05-30
 **/
@Data
public class Tree<K extends Comparable<K>, V> {
    /**
     * 当前红黑树的根节点
     */
    private Node<K, V> root;

    /**
     * 插入一个节点元素
     *
     * @param element 待插入的节点元素
     */
    public void insert(Element<K, V> element) {
        // 向树中添加一个节点
        if (this.root == null) {
            this.root = new Node<>(element, null, ColorEnum.BLACK);
            return;
        }
        Node<K, V> node = this.root;
        // 找寻元素插入位置对应的父节点
        Node<K, V> insertParentNode = node;
        while (node != null) {
            if (element.getKey().compareTo(node.getElement().getKey()) < 0) {
                node = node.getLeft();
            } else if (element.getKey().compareTo(node.getElement().getKey()) == 0) {
                node.setElement(element);
                return;
            } else {
                node = node.getRight();
            }
            if (node != null) {
                insertParentNode = node;
            }
        }
        // 找到元素插入位置对应的父节点，创建节点，并和父节点建立关联关系
        Node<K, V> newNode = new Node<>(element, insertParentNode, ColorEnum.BLACK);
        if (element.getKey().compareTo(insertParentNode.getElement().getKey()) < 0) {
            insertParentNode.setLeft(newNode);
        } else {
            insertParentNode.setRight(newNode);
        }
        // 通过旋转和变色进行调整
    }

    /**
     * 删除一个节点元素
     *
     * @param element 待删除的节点元素
     * @return 删除后的根节点
     */
    public Node<K, V> delete(Element<K, V> element) {
        return this.root;
    }

    /**
     * 对某个树节点进行左旋操作
     * p                r
     * / \              / \
     * l   r    -->     p   rr
     * / \          / \
     * rl  rr       l  rl
     *
     * @param node 待进行左旋操作的节点
     */
    private void leftRotate(Node<K, V> node) {
        if (node == null) {
            return;
        }
        Node<K, V> right = node.getRight();
        // 1. rl不为空，则将rl设置为p的右子节点
        Node<K, V> rl = right.getLeft();
        node.setRight(rl);
        if (rl != null) {
            rl.setParent(node);
        }
        // 2. 修改p的父节点为r的父节点
        upgrade(right);
        // 3. 设置p为r的左子节点
        right.setLeft(node);
        node.setParent(right);
    }

    /**
     * 对某个树节点进行右旋操作
     * p                l
     * / \              / \
     * l   r    -->     ll   p
     * / \                   / \
     * ll  lr                lr  r
     *
     * @param node 待进行右旋操作的节点
     */
    private void rightRotate(Node<K, V> node) {
        if (node == null) {
            return;
        }
        Node<K, V> left = node.getLeft();
        // 1. lr不为空，则将lr设置为p的左子节点
        Node<K, V> lr = left.getRight();
        node.setLeft(lr);
        if (lr != null) {
            lr.setParent(node);
        }
        // 2. 修改p的父节点为l的父节点
        upgrade(left);
        // 3. 设置p为l的右子节点
        left.setRight(node);
        node.setParent(left);
    }

    /**
     * 节点升级
     * 将某个节点提升替换父节点的位置
     *
     * @param node 待升级的节点
     */
    private void upgrade(Node<K, V> node) {
        // 2. 修改p的父节点为l的父节点
        Node<K, V> parent = node.getParent();
        Node<K, V> grandParent = parent.getParent();
        node.setParent(grandParent);
        if (grandParent == null) {
            setRoot(node);
        } else if (grandParent.getLeft() == parent) {
            grandParent.setLeft(node);
        } else {
            grandParent.setRight(node);
        }
    }
}

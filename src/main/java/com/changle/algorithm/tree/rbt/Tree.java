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
        // 插入节点，默认插入节点为红色
        final Node<K, V> node = new Node<>(element);
        if (this.root == null) {
            this.root = node;
        } else {
            this.root.addNode(node);
        }
        // 旋转或变色以便符合红黑树特性
        fixAfterAdd(node);
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
     * 插入元素后对树进行调整以便符合红黑树特性
     *
     * @param node 插入的节点
     */
    private void fixAfterAdd(Node<K, V> node) {
        if (node == null) {
            // 节点为空直接返回
            return;
        }
        node.setColor(ColorEnum.RED);
        if (node == this.root) {
            // 节点是根节点直接设置为黑色返回
            this.root.setColor(ColorEnum.BLACK);
            return;
        }
        Node<K, V> parent = node.getParent();
        Node<K, V> grandParent = parent.getParent();
        // 节点的父节点为红色才需要调整
        if (!parent.getColor().isBlack()) {
            Node<K, V> uncle = parent == grandParent.getLeft() ? grandParent.getRight() : grandParent.getLeft();
            if (uncle == null || uncle.getColor().isBlack()) {
                // 3节点添加后存在LL、RR、LR、RL四种情况需要调整，分别需要旋转+变色
                if (node == parent.getLeft() && parent == grandParent.getLeft()) {
                    // LL型，对祖先节点进行右旋+变色处理
                    rightRotate(grandParent);
                } else if (node == parent.getRight() && parent == grandParent.getRight()) {
                    // RR型，对祖先节点进行左旋+变色处理
                    leftRotate(grandParent);
                } else if (node == parent.getRight() && parent == grandParent.getLeft()) {
                    // LR型，对父节点进行左旋+对祖先节点进行右旋+变色处理
                    leftRotate(parent);
                    rightRotate(grandParent);
                } else {
                    // RL型，对父节点进行右旋+对祖先节点进行左旋+变色处理
                    rightRotate(parent);
                    leftRotate(grandParent);
                }
                grandParent.setColor(ColorEnum.RED);
                parent.setColor(ColorEnum.BLACK);
            } else {
                // 4节点添加后直接变色即可
                grandParent.setColor(ColorEnum.RED);
                parent.setColor(ColorEnum.BLACK);
                uncle.setColor(ColorEnum.BLACK);
                // 4节点情况变色后可能导致更高层级的产生连续两个红色节点相邻，需要递归调整
                fixAfterAdd(grandParent);
            }
        }
        this.root.setColor(ColorEnum.BLACK);
    }

    /**
     * 对某个树节点进行左旋操作
     *     p                r
     *    / \              / \
     *   l   r    -->     p   rr
     *      / \          / \
     *     rl  rr       l  rl
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
     *     p                l
     *    / \              / \
     *   l   r    -->     ll   p
     *  / \                   / \
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

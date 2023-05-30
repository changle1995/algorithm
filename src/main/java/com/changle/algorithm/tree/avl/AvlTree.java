package com.changle.algorithm.tree.avl;

import com.changle.algorithm.tree.TreeElement;
import lombok.Data;

/**
 * 平衡二叉树
 *
 * @author changle05@meituan.com
 * @version 1.0
 * @date 2023-05-30
 */
@Data
public class AvlTree<K extends Comparable<K>, V, E extends TreeElement<K, V>> {
    /**
     * 树的节点数
     */
    private int size;
    /**
     * 根节点
     */
    private AvlTreeNode<K, V, E> root;

    /**
     * 以某个节点为根节点进行插入元素操作
     *
     * @param element 待添加的元素
     * @return 添加后的树根节点
     */
    public AvlTreeNode<K, V, E> insert(E element) {
        if (this.root == null) {
            // 根节点为空，以最新的element直接设置为根节点
            this.root = new AvlTreeNode<>(element);
        } else {
            // 根节点不为空，调用根节点的插入方法进行插入操作
            this.root = this.root.insert(element);
        }
        return this.root;
    }

    /**
     * 删除元素
     *
     * @param element 待删除的元素
     * @return 删除后的树根节点
     */
    public AvlTreeNode<K, V, E> delete(E element) {
        if (this.root != null) {
            // 根节点不为空，调用根节点的插入方法进行插入操作
            this.root = this.root.delete(element);
        }
        return this.root;
    }
}

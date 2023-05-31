package com.changle.algorithm.tree.avl;

import com.changle.algorithm.tree.Element;
import lombok.Data;

/**
 * 平衡二叉树节点
 *
 * @author changle05@meituan.com
 * @version 1.0
 * @date 2023-05-30
 */
@Data
public class Node<K extends Comparable<K>, V, E extends Element<K, V>> {
    /**
     * 节点数据
     */
    private E element;
    /**
     * 节点的高度
     */
    private int height;
    /**
     * 左子节点
     */
    private Node<K, V, E> left;
    /**
     * 右子节点
     */
    private Node<K, V, E> right;

    public Node(E element) {
        this.element = element;
        this.height = 0;
    }

    /**
     * 计算某个节点的高度
     *
     * @return 节点的高度
     */
    public int calculateHeight() {
        int leftHeight = this.getLeft() == null ? -1 : this.getLeft().calculateHeight();
        int rightHeight = this.getRight() == null ? -1 : this.getRight().calculateHeight();
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * 计算当前节点的平衡因子
     *
     * @return 平衡因子
     */
    public int calculateBalanceFactor() {
        int leftHeight = this.getLeft() == null ? -1 : this.getLeft().getHeight();
        int rightHeight = this.getRight() == null ? -1 : this.getRight().getHeight();
        return leftHeight - rightHeight;
    }

    /**
     * 对当前节点进行左旋操作
     *
     * @return 左旋结束后的根节点
     */
    public Node<K, V, E> leftRotate() {
        // 将待旋转的右子结点保存到临时变量
        Node<K, V, E> right = getRight();
        // 开始旋转
        // 1. 将右子节点的左子节点设置为当前节点的右子结点
        // 2. 将当前节点设置为右子结点的左子节点
        setRight(right.getLeft());
        right.setLeft(this);
        // 更新高度值
        setHeight(calculateHeight());
        right.setHeight(right.calculateHeight());
        // 返回右子结点位根节点
        return right;
    }

    /**
     * 对当前节点进行右旋操作
     *
     * @return 右旋结束后的根节点
     */
    public Node<K, V, E> rightRotate() {
        // 将待旋转的左子结点保存到临时变量
        Node<K, V, E> left = getLeft();
        // 开始旋转
        // 1. 将左子节点的右子节点设置为当前节点的左子结点
        // 2. 将当前节点设置为左子结点的右子节点
        setLeft(left.getRight());
        left.setRight(this);
        // 更新高度值
        setHeight(calculateHeight());
        left.setHeight(left.calculateHeight());
        // 返回左子结点位根节点
        return left;
    }

    /**
     * 以当前节点为根节点进行插入元素操作
     *
     * @param element 待添加的元素
     * @return 添加后的根节点
     */
    public Node<K, V, E> insert(E element) {
        if (element.getKey().compareTo(getElement().getKey()) == 0) {
            // 根节点key相等，以最新的element直接设置为根节点
            setElement(element);
            // 因为没有改变树的结构，所以设值完成后可以直接返回
            return this;
        } else if (element.getKey().compareTo(getElement().getKey()) < 0) {
            // 小于根节点，在左子树进行插入
            Node<K, V, E> left = getLeft();
            if (left == null) {
                left = new Node<>(element);
            } else {
                left = left.insert(element);
            }
            setLeft(left);
        } else {
            // 大于根节点，在右子树进行插入
            Node<K, V, E> right = getRight();
            if (right == null) {
                right = new Node<>(element);
            } else {
                right = right.insert(element);
            }
            setRight(right);
        }
        // 插入完成，更新节点的高度
        setHeight(calculateHeight());
        // 计算平衡因子
        int balanceFactor = calculateBalanceFactor();
        if (balanceFactor > 1 && element.getKey().compareTo(getElement().getKey()) < 0) {
            // LL型，进行一次右旋
            return rightRotate();
        } else if (balanceFactor > 1 && element.getKey().compareTo(getElement().getKey()) > 0) {
            // LR型，先对左子树进行一次左旋，再整体进行一次右旋
            setLeft(getLeft().leftRotate());
            return rightRotate();
        } else if (balanceFactor < -1 && element.getKey().compareTo(getElement().getKey()) > 0) {
            // RR型，进行一次左旋
            return leftRotate();
        } else if (balanceFactor < -1 && element.getKey().compareTo(getElement().getKey()) < 0) {
            // RL型，先对右子树进行一次右旋，再整体进行一次左旋
            setRight(getRight().rightRotate());
            return leftRotate();
        } else {
            // 插入后仍是平衡的，直接返回当前节点
            return this;
        }
    }
}

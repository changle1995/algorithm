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
     * 以当前节点为根节点删除一个节点
     *
     * @param node 待删除的节点
     */
    public void removeNode(Node<K, V> node) {
        if (node == null) {
            return;
        }
        // 比较需要删除的节点与当前节点
        if (node.element.getKey().compareTo(element.getKey()) < 0) {
            // 删除节点元素小于当前节点元素
            if (left != null) {
                // 当前节点左子节点不为空，递归调用左子结点进行删除
                left.removeNode(node);
            }
        } else if (node.element.getKey().compareTo(element.getKey()) == 0) {
            // 删除节点元素等于当前节点元素
            if (node.left != null && node.right != null) {
                // 删除节点有左右子节点
                // 找到前驱/后继节点，使用前驱/后继节点覆盖当前节点
                Node<K, V> preNode = node.getPreNode();
                node.element = preNode.element;
                // todo: 删除前驱/后继节点

            } else if (node.left != null || node.right != null) {
                // 删除节点只有一个子节点
                // 使用子节点替换当前节点
                Node<K, V> child = node.left == null ? node.right : node.left;
                node.element = child.element;
                node.left = null;
                node.right = null;
                child.parent = null;
                child = null;
            } else {
                // 删除节点本身为叶子节点
                if (node == node.parent.left) {
                    node.parent.left = null;
                } else {
                    node.parent.right = null;
                }
                node.parent = null;
                node = null;
            }
        } else {
            // 删除节点元素大于当前节点元素
            if (right != null) {
                // 当前节点右子节点不为空，递归调用右子结点进行删除
                right.removeNode(node);
            }
        }
    }

    /**
     * 找当前节点的前驱节点
     *
     * @return 当前节点的前驱节点
     */
    private Node<K, V> getPreNode() {
        if (left != null) {
            Node<K, V> node = left;
            while (node.right != null) {
                node = node.right;
            }
            return node;
        }
        Node<K, V> node = parent;
        while (node.parent != null && node.parent.left == node) {
            node = node.parent;
        }
        return node.parent;
    }

    @Override
    public String toString() {
        return "【key:" + element.getKey() +
                ",color:" + color.getRemark() +
                "】";
    }
}

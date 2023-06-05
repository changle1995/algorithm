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
        // 插入节点
        Node<K, V> node = new Node<>(element);
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
     * @return 删除掉的节点
     */
    public Node<K, V> delete(Element<K, V> element) {
        if (this.root == null) {
            return null;
        }
        // 找到待删除的节点
        Node<K, V> node = this.root.getNode(element);
        // 删除节点
        removeNode(node);
        return node;
    }

    /**
     * 从当前树删除一个节点
     *
     * @param node 待删除的节点
     */
    private void removeNode(Node<K, V> node) {
        if (node == null) {
            // 空节点无需任何操作
            return;
        }
        if (node.getLeft() != null && node.getRight() != null) {
            // 有左右两个子节点，找到前驱/后继节点，然后使用前驱/后继节点覆盖当前节点
            Node<K, V> predecessor = node.getPredecessor();
            node.setElement(predecessor.getElement());
            // 需要删除的节点变为前驱/后继节点
            node = predecessor;
        }

        Node<K, V> replacement = node.getLeft() != null ? node.getLeft() : node.getRight();
        if (replacement != null) {
            // 只有一个子节点，使用子节点替换当前节点
            Node<K, V> parent = node.getParent();
            replacement.setParent(parent);
            if (parent == null) {
                root = replacement;
            } else if (node == parent.getLeft()) {
                parent.setLeft(replacement);
            } else {
                parent.setRight(replacement);
            }
            // 删除后调整
            fixAfterRemove(replacement);
        } else if (node.getParent() == null) {
            // 删除节点为根节点
            root = null;
        } else {
            // 删除节点为非根叶子节点
            // 非根叶子节点删除需要先进行调整
            fixAfterRemove(node);
            if (node == node.getParent().getLeft()) {
                node.getParent().setLeft(null);
            } else {
                node.getParent().setRight(null);
            }
        }
    }

    /**
     * 删除非根节点后对树进行调整以便符合红黑树特性
     *
     * @param node 删除的节点
     */
    private void fixAfterRemove(Node<K, V> node) {
        while (node != root && node.getColor().isBlack()) {
            if (node == node.getParent().getLeft()) {
                // 删除节点是左子结点的情况
                // 寻找在234树中对应的真正的兄弟节点
                Node<K, V> bro = node.getParent().getRight();
                if (!bro.getColor().isBlack()) {
                    bro.setColor(ColorEnum.BLACK);
                    node.getParent().setColor(ColorEnum.RED);
                    leftRotate(node.getParent());
                    bro = node.getParent().getRight();
                }
                if ((bro.getLeft() == null || bro.getLeft().getColor().isBlack())
                        && (bro.getRight() == null || bro.getRight().getColor().isBlack())) {
                    // 兄弟节点也是2节点
                    bro.setColor(ColorEnum.RED);
                    node = node.getParent();
                } else {
                    // 兄弟节点是3、4节点
                    if (bro.getRight() == null) {
                        // 如果兄弟节点是3节点，且是左倾的情况，需要先进行变色+右旋处理，以便删除元素后再进行左旋处理
                        bro.getLeft().setColor(ColorEnum.BLACK);
                        bro.setColor(ColorEnum.RED);
                        rightRotate(bro);
                        bro = node.getParent().getRight();
                    }
                    // 3节点右旋处理后，3、4节点可以统一进行变色+左旋处理
                    bro.getRight().setColor(bro.getColor());
                    bro.setColor(node.getParent().getColor());
                    node.getParent().setColor(node.getColor());
                    leftRotate(node.getParent());
                    break;
                }
            } else {
                // 删除节点是右子结点的情况
                // 寻找在234树中对应的真正的兄弟节点
                Node<K, V> bro = node.getParent().getLeft();
                if (!bro.getColor().isBlack()) {
                    bro.setColor(ColorEnum.BLACK);
                    node.getParent().setColor(ColorEnum.RED);
                    rightRotate(node.getParent());
                    bro = node.getParent().getLeft();
                }
                if ((bro.getLeft() == null || bro.getLeft().getColor().isBlack())
                        && (bro.getRight() == null || bro.getRight().getColor().isBlack())) {
                    // 兄弟节点也是2节点
                    bro.setColor(ColorEnum.RED);
                    node = node.getParent();
                } else {
                    // 兄弟节点是3、4节点
                    if (bro.getLeft() == null) {
                        // 如果兄弟节点是3节点，且是右倾的情况，需要先进行变色+左旋处理，以便删除元素后再进行右旋处理
                        bro.getRight().setColor(ColorEnum.BLACK);
                        bro.setColor(ColorEnum.RED);
                        leftRotate(bro);
                        bro = node.getParent().getLeft();
                    }
                    // 3节点左旋处理后，3、4节点可以统一进行变色+右旋处理
                    bro.getLeft().setColor(bro.getColor());
                    bro.setColor(node.getParent().getColor());
                    node.getParent().setColor(node.getColor());
                    rightRotate(node.getParent());
                    break;
                }
            }
        }
        node.setColor(ColorEnum.BLACK);
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
        // 默认插入节点为红色
        node.setColor(ColorEnum.RED);
        Node<K, V> parent = node.getParent();
        // 节点的父节点为红色（对应于3节点升为4节点、4节点上溢两种情况）才需要调整
        if (parent != null && !parent.getColor().isBlack()) {
            Node<K, V> grandParent = parent.getParent();
            Node<K, V> uncle = parent == grandParent.getLeft() ? grandParent.getRight() : grandParent.getLeft();
            if (uncle == null || uncle.getColor().isBlack()) {
                // 3节点升为4节点，存在LL、RR、LR、RL四种情况需要调整，分别需要旋转+变色
                // 1. uncle为空，表示当前节点为新添加的节点
                // 2. uncle不为空，且颜色为黑色，表示当前节点为4节点上溢导致的递归调整情况
                if (node == parent.getLeft() && parent == grandParent.getLeft()) {
                    // LL型，对祖先节点进行右旋+变色处理
                    grandParent.setColor(ColorEnum.RED);
                    parent.setColor(ColorEnum.BLACK);
                    rightRotate(grandParent);
                } else if (node == parent.getRight() && parent == grandParent.getRight()) {
                    // RR型，对祖先节点进行左旋+变色处理
                    grandParent.setColor(ColorEnum.RED);
                    parent.setColor(ColorEnum.BLACK);
                    leftRotate(grandParent);
                } else if (node == parent.getRight() && parent == grandParent.getLeft()) {
                    // LR型，对父节点进行左旋+对祖先节点进行右旋+变色处理
                    grandParent.setColor(ColorEnum.RED);
                    parent.setColor(ColorEnum.RED);
                    node.setColor(ColorEnum.BLACK);
                    leftRotate(parent);
                    rightRotate(grandParent);
                } else {
                    // RL型，对父节点进行右旋+对祖先节点进行左旋+变色处理
                    grandParent.setColor(ColorEnum.RED);
                    parent.setColor(ColorEnum.RED);
                    node.setColor(ColorEnum.BLACK);
                    rightRotate(parent);
                    leftRotate(grandParent);
                }
            } else {
                // 4节点上溢，处理情况为直接变色即可
                grandParent.setColor(ColorEnum.RED);
                parent.setColor(ColorEnum.BLACK);
                uncle.setColor(ColorEnum.BLACK);
                // 4节点上溢变色后可能导致更高层级的产生连续两个红色节点相邻
                // 产生连续两个红色节点相邻，相当于新的红色节点为插入节点，以新的红色节点为起点递归调整
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
        // 修改p的父节点为l的父节点
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

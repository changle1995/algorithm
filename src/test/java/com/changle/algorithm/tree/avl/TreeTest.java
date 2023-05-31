package com.changle.algorithm.tree.avl;

import com.changle.algorithm.tree.TreeElement;
import org.junit.Test;

/**
 * 平衡二叉树测试类
 *
 * @author changle05@meituan.com
 * @version 1.0
 * @date 2023-05-30
 */
public class TreeTest {
    @Test
    public void testInsert() {
        Tree<Integer, Integer, TreeElement<Integer, Integer>> tree = new Tree<>();
        for (int i = 1; i < 10; i++) {
            tree.insert(new TreeElement<>(i, i));
        }
        print(tree.getRoot());
    }

    private static class Trunk {
        Trunk prev;
        String str;

        private Trunk(Trunk prev, String str) {
            this.prev = prev;
            this.str = str;
        }
    }

    private static void showTrunks(Trunk p) {
        if (p == null)
            return;

        showTrunks(p.prev);

        System.out.print(p.str);
    }

    private static void traversalPrint(Node<Integer, Integer, TreeElement<Integer, Integer>> root, Trunk prev, boolean isLeft) {
        if (root == null) {
            return;
        }

        String ROOT_PREV = "   ";
        String CHILD_PREV = "    ";

        String LEFT_CHILD_CURVED_EDGE = ".---";
        String LEFT_CHILD_STRAIGHT_EDGE = "   |";

        String RIGHT_CHILD_CURVED_EDGE = "`---";
        String RIGHT_CHILD_STRAIGHT_EDGE = "   |";


        String prev_str = CHILD_PREV;
        Trunk trunk = new Trunk(prev, prev_str);

        // 遍历左子树
        traversalPrint(root.getLeft(), trunk, true);

        if (prev == null)
            trunk.str = ROOT_PREV;
        else if (isLeft) {
            trunk.str = LEFT_CHILD_CURVED_EDGE;
            prev_str = LEFT_CHILD_STRAIGHT_EDGE;
        } else {
            trunk.str = RIGHT_CHILD_CURVED_EDGE;
            prev.str = prev_str;
        }

        showTrunks(trunk);

        // 打印当前节点
        System.out.println(root.getElement());

        if (prev != null)
            prev.str = prev_str;

        trunk.str = RIGHT_CHILD_STRAIGHT_EDGE;

        // 遍历右子树
        traversalPrint(root.getRight(), trunk, false);
    }

    public static void print(Node<Integer, Integer, TreeElement<Integer, Integer>> root) {
        traversalPrint(root, null, false);
    }
}

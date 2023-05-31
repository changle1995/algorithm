package com.changle.algorithm.tree.rbt;

import com.changle.algorithm.tree.Element;
import org.junit.Test;

/**
 * 红黑树测试类
 *
 * @author changle05@meituan.com
 * @version 1.0
 * @date 2023-05-31
 */
public class TreeTest {
    @Test
    public void testInsert() {
        Tree<Integer, Integer> tree = new Tree<>();
        for (int i = 1; i < 10; i++) {
            tree.insert(new Element<>(i, i));
        }
        TreeOperation.show(tree.getRoot());
    }
}

package com.changle.algorithm.tree.rbt;

import com.changle.algorithm.tree.Element;
import org.junit.Test;

import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入你要添加的节点:");
            int key = scanner.nextInt();
            System.out.println();
            tree.insert(new Element<>(key, key));
            TreeOperation.show(tree.getRoot());
        }
    }

    @Test
    public void testDelete() {
        Tree<Integer, Integer> tree = new Tree<>();
        for (int i = 1; i <= 10; i++) {
            tree.insert(new Element<>(i, i));
        }
        TreeOperation.show(tree.getRoot());
        //以下开始删除
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入你要删除的节点:");
            int key = scanner.nextInt();
            System.out.println();
            tree.delete(new Element<>(key, key));
            TreeOperation.show(tree.getRoot());
        }
    }
}

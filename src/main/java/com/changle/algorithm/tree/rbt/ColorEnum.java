package com.changle.algorithm.tree.rbt;

import lombok.Getter;

/**
 * 红黑树节点的颜色
 *
 * @author changle
 * @date 2023-05-30
 **/
public enum ColorEnum {
    /**
     * 红色节点
     */
    RED(false, "红色"),
    /**
     * 黑色节点
     */
    BLACK(true, "黑色");
    /**
     * 颜色值
     */
    @Getter
    private final boolean black;
    /**
     * 颜色说明
     */
    @Getter
    private final String remark;

    ColorEnum(boolean black, String remark) {
        this.black = black;
        this.remark = remark;
    }
}

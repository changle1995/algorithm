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
    RED(false, "红色节点"),
    /**
     * 黑色节点
     */
    BLACK(true, "黑色节点");
    /**
     * 颜色值
     */
    @Getter
    private final boolean value;
    /**
     * 颜色说明
     */
    @Getter
    private final String remark;

    ColorEnum(boolean value, String remark) {
        this.value = value;
        this.remark = remark;
    }
}

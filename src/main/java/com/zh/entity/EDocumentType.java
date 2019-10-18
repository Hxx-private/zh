package com.zh.entity;

/**
 * @author Hxx
 */

public enum EDocumentType {
    PDF(0);
    private final int value;

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
    EDocumentType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
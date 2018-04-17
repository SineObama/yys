package com.sine.yys;

/**
 * 输入数据非法。
 */
public class IllegalDataException extends RuntimeException {
    IllegalDataException(String message) {
        super(message);
    }
}

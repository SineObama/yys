package com.sine.yys.event;

/**
 * 由于招财猫触发时机诡异：
 * 1. 辉夜姬战备行动触发；
 * 2. 以前在（犬神）反击时触发，后来改了。
 * 所以暂时独立出一个事件，单独触发。
 */
public class ZhaoCaiMaoEvent extends BaseEvent {
}

package com.sine;

import com.sine.yys.simulation.InputUtil;
import com.sine.yys.simulation.RedBlueSimulator;

import java.io.IOException;

public class Main {
    static {
        System.setProperty("java.util.logging.config.file", "resource/logging.properties");
    }

    /**
     * @param args 参数：数据文件名 测试次数。
     */
    public static void main(String[] args) throws IOException {
        final String filename;
        final int times;
        if (args.length < 1)
            filename = "demo.txt";
        else
            filename = args[0];
        if (args.length < 2)
            times = 10;
        else
            times = Integer.valueOf(args[1]);

        RedBlueSimulator simulator = InputUtil.create(filename);
        simulator.test(times);
    }
}

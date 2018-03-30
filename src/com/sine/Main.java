package com.sine;

import com.sine.yys.InputUtil;
import com.sine.yys.RedBlueSimulator;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    static {
        if (System.getProperty("java.util.logging.config.file") == null)
            System.setProperty("java.util.logging.config.file", "logging.properties");
    }

    /**
     * @param args 参数：数据文件名 测试次数 [-i]。其中 -i 显示战斗过程信息。
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
        if (args.length > 2 && args[2].equals("-i"))
            Logger.getLogger("").setLevel(Level.INFO);

        RedBlueSimulator simulator = InputUtil.create(filename);
        simulator.test(times);
    }
}

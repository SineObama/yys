package com.sine;

import com.sine.yys.InputUtil;
import com.sine.yys.RedBlueSimulator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static String fileEncoding = null;

    static {
        if (System.getProperty("java.util.logging.config.file") == null)
            System.setProperty("java.util.logging.config.file", "logging.properties");
    }

    /**
     * @param args 参数：数据文件名 测试次数 [可选参数]
     *             可选参数：-i 显示战斗过程信息；-fe=gbk 设置读取文件的编码为gbk。
     */
    public static void main(String[] args) {
        args = process(args);
        final String filename;
        final int times;
        if (args.length < 1)
            filename = "demo.txt";
        else
            filename = args[0];
        if (args.length < 2)
            times = 100;
        else
            times = Integer.valueOf(args[1]);

        try {
            RedBlueSimulator simulator = InputUtil.create(filename, fileEncoding);
            simulator.test(times);
        } catch (UnsupportedEncodingException e) {
            System.err.println("unsupported encoding: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("read file failed: " + e.getLocalizedMessage());
        }
    }

    private static String[] process(String[] args) {
        String[] temp = new String[args.length];
        int count = 0;
        for (String arg : args) {
            if (arg.equals("-i")) {
                Logger.getLogger("").setLevel(Level.INFO);
            } else if (arg.startsWith("-fe=")) {
                fileEncoding = arg.substring(4);
            } else {
                temp[count] = arg;
                count++;
            }
        }
        String[] rtn = new String[count];
        System.arraycopy(temp, 0, rtn, 0, count);
        return rtn;
    }
}

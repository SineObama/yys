package test;

import com.sine.yys.InputUtil;
import com.sine.yys.RedBlueSimulator;
import com.sine.yys.util.RandUtil;
import com.sine.yys.util.Seed;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {
    static {
        if (System.getProperty("java.util.logging.config.file") == null)
            System.setProperty("java.util.logging.config.file", "tests/logging_testall.properties");
    }

    /**
     * 模拟./tests/下所有测试用例，规定文件名为非0自然数（无后缀），依次增加。
     * 每个测试用例会重置随机序列。
     */
    public static void main(String[] args) throws IOException {
        RedBlueSimulator simulator;
        System.out.println("random seed: " + Seed.get());
        if (System.getProperty("info") != null)
            Logger.getLogger("").setLevel(Level.INFO);
        try {
            for (int i = 1; i < 99; i++) {
                RandUtil.reset();
                simulator = InputUtil.create("tests/" + i, InputUtil.projectEnc);
                simulator.test(Integer.valueOf(args[0]));
            }
        } catch (FileNotFoundException ignored) {
        }
    }
}

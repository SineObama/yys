package test;

import com.sine.yys.InputUtil;
import com.sine.yys.RedBlueSimulator;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {
    static {
        if (System.getProperty("java.util.logging.config.file") == null) {
            if (System.getProperty("info") == null)
                System.setProperty("java.util.logging.config.file", "tests/logging_warning.properties");
            else
                System.setProperty("java.util.logging.config.file", "tests/logging_info.properties");
        }
    }

    /**
     * 模拟./tests/下所有测试用例，规定文件名为非0自然数（无后缀），依次增加。
     */
    public static void main(String[] args) throws IOException {
        RedBlueSimulator simulator;
        try {
            for (int i = 1; i < 99; i++) {
                simulator = InputUtil.create("tests/" + i);
                simulator.test(Integer.valueOf(args[0]));
            }
        } catch (FileNotFoundException ignored) {
        }
    }
}

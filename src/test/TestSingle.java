package test;

import com.sine.yys.InputUtil;
import com.sine.yys.RedBlueSimulator;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestSingle {
    static {
        if (System.getProperty("java.util.logging.config.file") == null)
            System.setProperty("java.util.logging.config.file", "tests/logging_single.properties");
    }

    /**
     * 运行./tests/下的单个测试。参数中没有文件名则从输入流读取。
     *
     * @param args 参数：{@code 测试次数 [文件名]}
     */
    public static void main(String[] args) throws IOException {
        RedBlueSimulator simulator;
        if (System.getProperty("info") != null)
            Logger.getLogger("").setLevel(Level.INFO);
        final String filename;
        if (args.length < 2) {
            Scanner scanner = new Scanner(System.in);
            filename = scanner.next();
        } else {
            filename = args[1];
        }
        simulator = InputUtil.create("tests/" + filename, InputUtil.projectEnc);
        simulator.test(Integer.valueOf(args[0]));
    }
}

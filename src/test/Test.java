package test;

import com.sine.yys.simulation.InputUtil;
import com.sine.yys.simulation.RedBlueSimulator;

import java.io.IOException;

public class Test {
    static {
        if (System.getProperty("info") == null)
            System.setProperty("java.util.logging.config.file", "resource/test/logging_severe.properties");
        else
            System.setProperty("java.util.logging.config.file", "resource/test/logging_info.properties");
    }

    public static void main(String[] args) throws IOException {
        final RedBlueSimulator simulator = InputUtil.create("tests/3.txt");
        simulator.test(Integer.valueOf(args[0]));
    }
}

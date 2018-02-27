package test;

import com.sine.yys.simulation.InputUtil;
import com.sine.yys.simulation.RedBlueSimulator;

import java.io.IOException;

public class Test {
    static {
        if (System.getProperty("info") == null)
            System.setProperty("java.util.logging.config.file", "resource/test/logging_warning.properties");
        else
            System.setProperty("java.util.logging.config.file", "resource/test/logging_info.properties");
    }

    public static void main(String[] args) {
        RedBlueSimulator simulator;
        try {
            for (int i = 1; i < 99; i++) {
                simulator = InputUtil.create("tests/" + i);
                simulator.test(Integer.valueOf(args[0]));
            }
        } catch (IOException e) {
        }
    }
}

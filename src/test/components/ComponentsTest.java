package test.components;

import com.sine.yys.skill.model.AttackInfoImpl;
import test.components.base.Test;

public class ComponentsTest {
    static {
        if (System.getProperty("java.util.logging.config.file") == null)
            System.setProperty("java.util.logging.config.file", "tests/logging.properties");
    }

    public static void main(String[] args) {
        AttackInfoImpl._float = false;
        for (TestFactory.Creator creator : TestFactory.get("")) {
            Test test = creator.create();
            try {
                test.test();
            } catch (AssertionError e) {
                System.out.println(test.getLabels() + " failed: " + e.getMessage());
            }
        }
    }
}

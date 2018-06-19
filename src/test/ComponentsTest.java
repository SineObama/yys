package test;

import com.sine.yys.skill.model.AttackInfoImpl;
import test.components.TestFactory;
import test.components.inter.Test;

import java.util.logging.Logger;

/**
 * 御魂和技能的组件测试。
 *
 * @see test.components
 */
public class ComponentsTest {
    private static Logger log;

    static {
        if (System.getProperty("java.util.logging.config.file") == null)
            System.setProperty("java.util.logging.config.file", "tests/logging_component.properties");
        log = Logger.getLogger(ComponentsTest.class.getName());
    }

    public static void main(String[] args) {
        AttackInfoImpl._float = false;
        int count = 0, fail = 0;
        long start, end;
        start = System.currentTimeMillis();
        for (TestFactory.Creator creator : TestFactory.get("")) {
            Test test = creator.create();
            try {
                test.test();
                log.fine(test.getClass().getSimpleName() + " pass");
            } catch (AssertionError e) {
                log.severe(test.getClass().getSimpleName() + " failed: " + e.getMessage());
                fail++;
            }
            count++;
        }
        end = System.currentTimeMillis();
        log.info(fail + "/" + count + " tests failed, time=" + (end - start));
    }
}

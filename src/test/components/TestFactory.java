package test.components;

import test.components.base.Test;
import util.PackageUtil;

import java.util.*;

public class TestFactory {
    private static final Map<Creator, String> map = new HashMap<>();

    static {
        // 获取包中所有测试
        for (Class<?> aClass : PackageUtil.getClass(TestFactory.class.getPackage().getName() + ".tests", true)) {
            try {
                Object o = aClass.newInstance();
                if (o instanceof Test) {
                    map.put(() -> {
                        try {
                            return (Test) aClass.newInstance();
                        } catch (InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }, ((Test) o).getLabels());
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获得含有特定标签的测试。
     */
    public static Collection<Creator> get(String label) {
        List<Creator> list = new ArrayList<>();
        for (Map.Entry<Creator, String> creatorStringEntry : map.entrySet()) {
            if (creatorStringEntry.getValue().contains(label))
                list.add(creatorStringEntry.getKey());
        }
        return list;
    }

    @FunctionalInterface
    public interface Creator {
        Test create();
    }
}

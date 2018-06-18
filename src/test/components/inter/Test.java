package test.components.inter;

public interface Test {
    String redName = "红方";
    String buleName = "蓝方";

    /**
     * @return 测试的标签，空格分离。
     */
    default String getLabels() {
        return "";
    }

    void test() throws AssertionError;
}

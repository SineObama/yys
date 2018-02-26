package com.sine.yys.simulation;

import com.sine.yys.simulation.component.PropertyImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 每行代表一个式神。
 * 前面是红方阵营，第一个空行之后是蓝方阵营。
 * <p>
 * 每行输入格式：名字      攻击    生命     防御    速度    暴击   暴伤    命中    抵抗    御魂
 * <p>
 * 其中名字和御魂为中午或全拼（不分大小写），其他作为浮点值，可用百分数形式。
 * （可以井号#开头写入一行注释，不读入数据）
 */
public class InputUtil {
    public static RedBlueSimulator create(String filename) throws IOException {
        File file = new File(filename);
        final FileReader reader = new FileReader(file);
        final List<String> lines = readFileByLines(reader);

        CampInfo red = new CampInfo();
        CampInfo blue = new CampInfo();
        CampInfo campInfo = red;
        for (String line : lines) {
            if (line.startsWith("#"))
                continue;
            if (line.equals("")) {
                campInfo = blue;
                continue;
            }
            final String[] tokens = line.split("\\s+");
            if (tokens.length != 10) {
                throw new NumberFormatException("输入格式非法，字段数不正确：" + line);
            }
            campInfo.add(tokens[0], new PropertyImpl(fromString(tokens, 1, 8)), tokens[9]);
        }
        return new RedBlueSimulator(red, blue);
    }

    /**
     * 把数组中指定位置开始的若干个字符串转为浮点，简单支持百分号类型。
     */
    private static double[] fromString(String[] s, int begin, int length) {
        double[] doubles = new double[length];
        for (int i = 0; i < length; i++) {
            String string = s[begin + i];
            if (string.endsWith("%"))
                doubles[i] = Double.valueOf(string.substring(0, string.length() - 1)) / 100.0;
            else
                doubles[i] = Double.valueOf(string);
        }
        return doubles;
    }

    private static List<String> readFileByLines(FileReader reader) throws IOException {
        List<String> lines = new ArrayList<>(11);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line.trim());
        }
        bufferedReader.close();
        return lines;
    }
}

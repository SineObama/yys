package com.sine.yys;

import com.sine.yys.impl.PropertyImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 从文件读取数据，构成模拟器。
 * <p>
 * 第一行是一个浮点值，血量加成。
 * 每行代表一个式神。
 * 先归入红方阵营，出现空行后归入蓝方阵营（或者结束输入）。
 * <p>
 * 每行输入格式：名字      攻击    生命     防御    速度    暴击   暴伤    命中    抵抗    御魂
 * <p>
 * 其中名字和御魂为中文或全拼（不分大小写），其他作为浮点值（可用百分数形式）。
 * （可用井号#开头写入注释，纯注释行将被忽略）
 */
public class InputUtil {
    public static RedBlueSimulator create(String filename) throws IOException {
        File file = new File(filename);
        final FileReader reader = new FileReader(file);
        final List<String> lines = readFileByLines(reader);
        final Iterator<String> iterator = lines.iterator();
        final double times = readNum(iterator);
        final CampInfo red = readCamp(iterator);
        final CampInfo blue = readCamp(iterator);
        return new RedBlueSimulator(red, blue, times);
    }

    /**
     * 尝试读取第一行血量加成系数。
     */
    private static double readNum(Iterator<String> iterator) {
        while (iterator.hasNext()) {
            final String line = iterator.next();
            if (line.isEmpty())
                continue;
            if (line.startsWith("#"))
                continue;
            final String[] tokens = line.replaceAll("#.*", "").split("\\s+");
            try {
                return Double.valueOf(tokens[0]);
            } catch (Exception e) {
                break;
            }
        }
        return 1.0;
    }

    /**
     * 从tokens读取至少一个式神信息，在遇到空行时结束。
     * 忽略#开头的行。其他行去掉#后的内容，再分割，读入。
     */
    private static CampInfo readCamp(Iterator<String> iterator) {
        CampInfo campInfo = new CampInfo();
        while (iterator.hasNext()) {
            final String line = iterator.next();
            if (line.isEmpty()) {
                if (campInfo.infos.size() > 0) break;
                else continue;
            }
            if (line.startsWith("#"))
                continue;
            final String[] tokens = line.replaceAll("#.*", "").split("\\s+");
            if (tokens.length != 10 && tokens.length != 9)
                throw new NumberFormatException("输入格式非法，字段数不正确：" + String.join(" ", tokens));
            EntityInfo info = new EntityInfo();
            info.shiShen = tokens[0];
            info.property = new PropertyImpl(fromString(tokens, 1, 8));
            info.mitama = tokens.length == 10 ? tokens[9] : null;
            campInfo.infos.add(info);
        }
        return campInfo;
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
        while ((line = bufferedReader.readLine()) != null)
            lines.add(line.trim());
        bufferedReader.close();
        return lines;
    }
}

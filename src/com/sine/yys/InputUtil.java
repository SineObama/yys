package com.sine.yys;

import com.sine.yys.impl.CampInfo;
import com.sine.yys.impl.EntityInfo;
import util.UnicodeReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * 从文件读取数据，构成模拟器。
 * 默认文件编码UTF-8。
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
    public static String defaultEncoding = "UTF-8";
    private final Logger log = Logger.getLogger(this.getClass().getName());

    private InputUtil() {
    }

    public static RedBlueSimulator create(String filename) throws IOException {
        return new InputUtil()._create(filename, null);
    }

    public static RedBlueSimulator create(String filename, String encoding) throws IOException, IllegalDataException {
        return new InputUtil()._create(filename, encoding);
    }

    private static List<String> readFileByLines(Reader reader) throws IOException {
        List<String> lines = new ArrayList<>(11);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null)
            lines.add(line.trim());
        bufferedReader.close();
        return lines;
    }

    private RedBlueSimulator _create(String filename, String encoding) throws IOException {
        File file = new File(filename);
        final Reader reader = new UnicodeReader(new FileInputStream(file), encoding == null ? defaultEncoding : encoding);
        final Iterator<String> iterator = readFileByLines(reader).iterator();
        final double times = readNum(iterator);

        final CampInfo red = readCamp(iterator);
        red.lifeTimes = times;
        final CampInfo blue = readCamp(iterator);
        blue.lifeTimes = times;

        log.info("读取信息如下：\n" + String.valueOf(times) + "\n" + red.infosToString() + "\n" + blue.infosToString());
        return new RedBlueSimulator(red, blue);
    }

    /**
     * 尝试读取第一行血量加成系数。
     */
    private double readNum(Iterator<String> iterator) {
        while (iterator.hasNext()) {
            final String line = iterator.next();
            if (line.isEmpty())
                continue;
            if (line.startsWith("#"))
                continue;
            final String[] tokens = line.replaceAll("#.*", "").split("\\s+");
            try {
                return Double.valueOf(tokens[0]);
            } catch (NumberFormatException e) {
                break;
            }
        }
        return 1.0;
    }

    /**
     * 从tokens读取至少一个式神信息，在遇到空行时结束。
     * 忽略#开头的行。其他行去掉#后的内容，再分割，读入。
     */
    private CampInfo readCamp(Iterator<String> iterator) {
        CampInfo campInfo = new CampInfo();
        while (iterator.hasNext()) {
            final String line = iterator.next();
            if (line.isEmpty()) {
                if (campInfo.infos.size() > 0) break;
                else continue;
            }
            if (line.startsWith("#"))
                continue;
            try {
                campInfo.infos.add(EntityInfo.fromString(line.replaceAll("#.*", "")));
            } catch (IllegalArgumentException e) {
                throw new IllegalDataException(e.getMessage());
            }
        }
        return campInfo;
    }
}

package com.sine.yys;

import com.sine.yys.impl.CampInfo;
import com.sine.yys.impl.EntityInfo;
import com.sine.yys.impl.PropertyImpl;
import com.sine.yys.mitama.MitamaFactory;
import com.sine.yys.shikigami.ShikigamiFactory;
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
    private List<String> data = new ArrayList<>();

    private InputUtil() {
    }

    public static RedBlueSimulator create(String filename) throws IOException {
        return new InputUtil()._create(filename, null);
    }

    public static RedBlueSimulator create(String filename, String encoding) throws IOException {
        return new InputUtil()._create(filename, encoding);
    }

    /**
     * 把数组中指定位置开始的若干个字符串转为浮点，简单支持百分号类型。
     */
    private static double[] fromString(String[] s, int begin, int length) {
        double[] doubles = new double[length];
        for (int i = 0; i < length; i++) {
            String string = s[begin + i];
            try {
                if (string.endsWith("%"))
                    doubles[i] = Double.valueOf(string.substring(0, string.length() - 1)) / 100.0;
                else
                    doubles[i] = Double.valueOf(string);
            } catch (NumberFormatException e) {
                throw new IllegalDataException("can't read as double: " + string);
            }
        }
        return doubles;
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
        data.add("读取信息如下：");
        data.add(String.valueOf(times));

        final CampInfo red = readCamp(iterator);
        red.lifeTimes = times;
        data.add("");
        final CampInfo blue = readCamp(iterator);
        blue.lifeTimes = times;
        data.add("");

        log.info(String.join("\n", data.toArray(new String[0])));
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
            final String[] tokens = line.replaceAll("#.*", "").split("\\s+");
            String _data = String.join(" ", tokens);
            if (tokens.length != 10 && tokens.length != 9)
                throw new IllegalDataException("wrong fields ：" + _data);
            data.add(_data);
            EntityInfo info = new EntityInfo();
            info.shiShen = tokens[0];
            info.property = new PropertyImpl(fromString(tokens, 1, 8));
            info.mitama = tokens.length == 10 ? tokens[9] : null;
            if (!ShikigamiFactory.isSupport(info.shiShen))
                throw new IllegalDataException("unsupport shikigami：" + info.shiShen);
            if (!MitamaFactory.isSupport(info.mitama))
                throw new IllegalDataException("unsupport mitama：" + info.mitama);
            campInfo.infos.add(info);
        }
        return campInfo;
    }
}

package com.sine.yys.util;

import com.sine.yys.inter.base.JSONable;

import java.util.Collection;
import java.util.Map;

/**
 * @see JSONable
 */
public class JSON {
    private StringBuilder builder = new StringBuilder();
    private String result = "";

    /**
     * 构造一个对象以持续添加键值对。
     */
    private JSON() {
        stackIn('{');
    }

    /**
     * 转化为一个JSON字符串。
     * 参数依次必须是：key, value, key, value...
     */
    public static String format(Object... objects) {
        JSON json = new JSON();
        for (int i = 0; i < objects.length; i += 2)
            json.appendKeyValue(objects[i], objects.length > i + 1 ? objects[i + 1] : null);
        return json.finish();
    }

    /**
     * 结束构造，返回JSON字符串。
     */
    private String finish() {
        if (builder != null) {
            stackOut('}');
            result = builder.toString();
            builder = null;
        }
        return result;
    }

    /**
     * 附加键值对（和逗号）。
     */
    private void appendKeyValue(Object key, Object value) {
        if (builder == null)
            return;
        appendString(key);
        builder.append(':');
        appendValue(value);
        builder.append(',');
    }

    private void appendValue(Object value) {
        if (value instanceof Collection) {
            appendCollection((Collection) value);
        } else if (value instanceof Map) {
            appendMap((Map<?, ?>) value);
        } else {
            appendSingle(value);
        }
    }

    private void appendCollection(Collection collection) {
        stackIn('[');
        for (Object value : collection) {
            appendValue(value);
            builder.append(',');
        }
        stackOut(']');
    }

    private void appendMap(Map<?, ?> map) {
        stackIn('{');
        for (Map.Entry<?, ?> entry : map.entrySet())
            appendKeyValue(entry.getKey(), entry.getValue());
        stackOut('}');
    }

    /**
     * 附加单个值（非集合Collection或映射Map）。
     */
    private void appendSingle(Object o) {
        if (o == null || o instanceof Number) {
            builder.append(String.valueOf(o));
        } else if (o instanceof JSONable) {
            String s = "";
            try {
                s = ((JSONable) o).toJSON();
            } catch (Exception e) {
                stackIn('{');
                appendKeyValue("object", o.toString());
                appendKeyValue("exception", e);
                appendKeyValue("message", e.getMessage());
                appendKeyValue("stackTrace", e.getStackTrace());
                stackOut('}');
            }
            builder.append(s);
        } else {
            appendString(o.toString());
        }
    }

    /**
     * 以字符串形式附加。
     * {@link Object#toString()}
     */
    private void appendString(Object o) {
        builder.append('\"');
        // TODO 进行字符转义
        builder.append(String.valueOf(o));
        builder.append('\"');
    }

    private void stackIn(char c) {
        builder.append(c);
    }

    private void stackOut(char c) {
        if (builder.charAt(builder.length() - 1) == ',')
            builder.deleteCharAt(builder.length() - 1);
        builder.append(c);
    }
}

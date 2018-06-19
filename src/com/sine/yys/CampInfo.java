package com.sine.yys;

import java.util.ArrayList;
import java.util.List;

/**
 * 一方阵营信息，包括若干个式神的信息。
 */
public class CampInfo {
    public final List<EntityInfo> infos = new ArrayList<>();
    public double lifeTimes = 1.0;

    public String infosToString() {
        String s = "";
        for (EntityInfo info : infos)
            s += info.toString() + "\n";
        return s;
    }
}

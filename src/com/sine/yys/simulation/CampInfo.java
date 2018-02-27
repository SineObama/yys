package com.sine.yys.simulation;

import com.sine.yys.info.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * 一方阵营信息，包括多个式神。
 */
public class CampInfo {
    final List<EntityInfo> infos = new ArrayList<>();

    public void add(String shiShen, Property property, String mitama) {
        EntityInfo info = new EntityInfo();
        info.shiShen = shiShen;
        info.property = property;
        info.mitama = mitama;
        infos.add(info);
    }
}

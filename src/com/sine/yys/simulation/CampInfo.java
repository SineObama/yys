package com.sine.yys.simulation;

import com.sine.yys.info.Property;
import com.sine.yys.mitama.Mitama;
import com.sine.yys.shikigami.Shikigami;

import java.util.ArrayList;
import java.util.List;

public class CampInfo {
    final List<EntityInfo> infos = new ArrayList<>();

    public void add(Shikigami shiShen, Property property, Mitama mitama) {
        EntityInfo info = new EntityInfo();
        info.shiShen = shiShen;
        info.property = property;
        info.mitama = mitama;
        infos.add(info);
    }
}

package com.sine.yys.simulation;

import com.sine.yys.info.IProperty;
import com.sine.yys.mitama.BaseMitama;
import com.sine.yys.shishen.BaseShiShen;

import java.util.ArrayList;
import java.util.List;

public class CampInfo {
    final List<EntityInfo> infos = new ArrayList<>();

    public void add(BaseShiShen shiShen, IProperty property, BaseMitama mitama) {
        EntityInfo info = new EntityInfo();
        info.shiShen = shiShen;
        info.property = property;
        info.mitama = mitama;
        infos.add(info);
    }
}

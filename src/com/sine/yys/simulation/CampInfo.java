package com.sine.yys.simulation;

import com.sine.yys.simulation.component.Mitama;
import com.sine.yys.simulation.component.entity.BaseShiShen;
import com.sine.yys.simulation.info.IProperty;

import java.util.ArrayList;
import java.util.List;

public class CampInfo {
    final List<EntityInfo> infos = new ArrayList<>();

    public void add(BaseShiShen shiShen, IProperty property, Mitama mitama) {
        EntityInfo info = new EntityInfo();
        info.shiShen = shiShen;
        info.property = property;
        info.mitama = mitama;
        infos.add(info);
    }
}

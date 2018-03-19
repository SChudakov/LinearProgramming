package com.sschudakov.transport_task.comparator;

import com.sschudakov.transport_task.table.MeshNode;

import java.util.Comparator;

/**
 * Created by Semen Chudakov on 16.11.2017.
 */
public class MeshNodePriceComparator implements Comparator<MeshNode> {


    @Override
    public int compare(MeshNode o1, MeshNode o2) {
        return o1.getMesh().getPrice().compareTo(o2.getMesh().getPrice());
    }
}

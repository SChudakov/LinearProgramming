package com.sschudakov.transport_task.comparator;

import com.sschudakov.transport_task.table.TTTableNode;

import java.util.Comparator;

/**
 * Created by Semen Chudakov on 16.11.2017.
 */
public class MeshNodePriceComparator implements Comparator<TTTableNode> {


    @Override
    public int compare(TTTableNode o1, TTTableNode o2) {
        return o1.getMesh().getPrice().compareTo(o2.getMesh().getPrice());
    }
}

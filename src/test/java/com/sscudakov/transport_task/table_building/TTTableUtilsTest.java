package com.sscudakov.transport_task.table_building;

import com.sschudakov.lp.transporttask.table.TTTableNode;
import com.sschudakov.lp.transporttask.table.TTBasis;
import com.sschudakov.lp.transporttask.table.TTTableMesh;
import com.sschudakov.lp.transporttask.table_building.TTUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 18.11.2017.
 */
public class TTTableUtilsTest {

    @Ignore
    @Test
    public void wouldCreateCycleTest() {

        TTBasis basis = new TTBasis(10);
        basis.add(new TTTableNode(null, 1, 1));
        basis.add(new TTTableNode(null, 1, 2));
        basis.add(new TTTableNode(null, 2, 2));
        basis.add(new TTTableNode(null, 2, 3));
        basis.add(new TTTableNode(null, 3, 3));

        System.out.println(TTUtils.wouldCreateCycle(basis, new TTTableNode(null, 3, 1)));

    }

    @Ignore
    @Test
    public void isLMeshTest() {
        TTTableMesh mesh = new TTTableMesh(null, null, 0, 146);
        Assert.assertEquals(TTUtils.isLMesh(mesh), true);
    }

    @Ignore
    @Test
    public void isRMeshTest() {
        TTTableMesh mesh = new TTTableMesh(null, 26, 26, -146);
        Assert.assertEquals(TTUtils.isRMesh(mesh), true);
    }

    @Ignore
    @Test
    public void findNextElementTest() {
        List<TTTableNode> nodes = new ArrayList<>();

        TTTableNode mesh1 = new TTTableNode(null, 1, 1);
        TTTableNode mesh2 = new TTTableNode(null, 1, 2);
        TTTableNode mesh3 = new TTTableNode(null, 3, 2);
        nodes.add(mesh1);
        nodes.add(mesh2);
        nodes.add(mesh3);

        System.out.println(TTUtils.findNextMesh(nodes, null, mesh2));


    }

}

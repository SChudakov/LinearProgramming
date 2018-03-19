package com.sscudakov.transport_task.table_building;

import com.sschudakov.transport_task.table.MeshNode;
import com.sschudakov.transport_task.table.TransportTableBasis;
import com.sschudakov.transport_task.table.TransportTaskTableMesh;
import com.sschudakov.transport_task.table_building.TransportTaskUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 18.11.2017.
 */
public class TransportTaskTableUtilsTest {

    @Test
    public void wouldCreateCycleTest() {

        TransportTableBasis basis = new TransportTableBasis(10);
        basis.add(new MeshNode(null, 1, 1));
        basis.add(new MeshNode(null, 1, 2));
        basis.add(new MeshNode(null, 2, 2));
        basis.add(new MeshNode(null, 2, 3));
        basis.add(new MeshNode(null, 3, 3));

        System.out.println(TransportTaskUtils.wouldCreateCycle(basis, new MeshNode(null, 3, 1)));

    }

    @Test
    public void isLMeshTest() {
        TransportTaskTableMesh mesh = new TransportTaskTableMesh(null, null, 0, 146);
        Assert.assertEquals(TransportTaskUtils.isLMesh(mesh), true);
    }

    @Test
    public void isRMeshTest() {
        TransportTaskTableMesh mesh = new TransportTaskTableMesh(null, 26, 26, -146);
        Assert.assertEquals(TransportTaskUtils.isRMesh(mesh), true);
    }

    @Test
    public void findNextElementTest() {
        List<MeshNode> nodes = new ArrayList<>();

        MeshNode mesh1 = new MeshNode(null, 1, 1);
        MeshNode mesh2 = new MeshNode(null, 1, 2);
        MeshNode mesh3 = new MeshNode(null, 3, 2);
        nodes.add(mesh1);
        nodes.add(mesh2);
        nodes.add(mesh3);

        System.out.println(TransportTaskUtils.findNextMesh(nodes, null, mesh2));


    }

}

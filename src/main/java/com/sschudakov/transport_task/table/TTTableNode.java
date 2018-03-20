package com.sschudakov.transport_task.table;

/**
 * Created by Semen Chudakov on 16.11.2017.
 */
public class TTTableNode {
    private TTTableMesh mesh;
    private int i;
    private int j;

    public TTTableMesh getMesh() {
        return mesh;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public TTTableNode(TTTableMesh mesh, int i, int j) {
        this.mesh = mesh;
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString() {
        return "{" + this.mesh + ";" + this.i + ";" + this.j + "}";
    }
}

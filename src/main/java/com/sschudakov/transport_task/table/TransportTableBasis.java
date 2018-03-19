package com.sschudakov.transport_task.table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 18.11.2017.
 */
public class TransportTableBasis {
    private List<MeshNode> basicElements;
    private int numOfBasicElements;


    public List<MeshNode> getBasicElements() {
        return basicElements;
    }

    public int getNumOfBasicElements() {
        return numOfBasicElements;
    }

    public TransportTableBasis(int numOfBasicElements) {
        this.numOfBasicElements = numOfBasicElements;
        this.basicElements = new ArrayList<>();
    }

    public void add(MeshNode element) {
        if (this.basicElements.size() < this.numOfBasicElements &&
                !this.basicElements.contains(element)) {
            this.basicElements.add(element);
        }
    }

    public boolean remove(MeshNode element) {
        return this.basicElements.remove(element);
    }

    public boolean isComplete() {
        return size() == this.numOfBasicElements;
    }

    public int size() {
        return this.basicElements.size();
    }

    public MeshNode getElementAt(int index) {
        return this.basicElements.get(index);
    }

    public boolean contains(TransportTaskTableMesh element) {
        for (MeshNode basicElement : this.basicElements) {
            if (basicElement.getMesh().equals(element)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(MeshNode element) {
        return this.basicElements.contains(element);
    }

    @Override
    public String toString() {
        return this.basicElements.toString();
    }
}

package com.sschudakov.transport_task.table;

import java.util.Arrays;
import java.util.Vector;

/**
 * Created by Semen Chudakov on 14.11.2017.
 */
public class TTTable {

    private int numOfConsumers;
    private int numOfProducers;

    private Vector<Integer> producersVector;
    private Vector<Integer> consumersVector;

    private Vector<Integer> uPotentials;
    private Vector<Integer> vPotentials;


    private TTTableMesh[][] mainTable;

    public int getNumOfConsumers() {
        return numOfConsumers;
    }

    public void setNumOfConsumers(int numOfConsumers) {
        this.numOfConsumers = numOfConsumers;
    }

    public int getNumOfProducers() {
        return numOfProducers;
    }

    public void setNumOfProducers(int numOfProducers) {
        this.numOfProducers = numOfProducers;
    }

    public TTTableMesh[][] getMainTable() {
        return mainTable;
    }

    public void setMainTable(TTTableMesh[][] mainTable) {
        this.mainTable = mainTable;
    }

    public Vector<Integer> getProducersVector() {
        return producersVector;
    }

    public void setProducersVector(Vector<Integer> producersVector) {
        this.producersVector = producersVector;
    }

    public Vector<Integer> getConsumersVector() {
        return consumersVector;
    }

    public void setConsumersVector(Vector<Integer> consumersVector) {
        this.consumersVector = consumersVector;
    }

    public Vector<Integer> getuPotentials() {
        return uPotentials;
    }

    public void setuPotentials(Vector<Integer> uPotentials) {
        this.uPotentials = uPotentials;
    }

    public Vector<Integer> getvPotentials() {
        return vPotentials;
    }

    public void setvPotentials(Vector<Integer> vPotentials) {
        this.vPotentials = vPotentials;
    }

    public void outputTable() {
        System.out.println("num of consumers: " + this.numOfConsumers);
        System.out.println("num of producers: " + this.numOfProducers);

        System.out.println("consumers vector: " + this.consumersVector.toString());
        System.out.println("producers vector: " + this.producersVector.toString());

        for (TTTableMesh[] TTTableMeshes : this.mainTable) {
            System.out.println(Arrays.toString(TTTableMeshes));
        }

        if (this.uPotentials != null) {
            System.out.println("u potentials: " + this.uPotentials);
        }

        if (this.vPotentials != null) {
            System.out.println("v potentials: " + this.vPotentials);
        }
    }
}

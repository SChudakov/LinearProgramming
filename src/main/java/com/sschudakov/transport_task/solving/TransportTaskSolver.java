package com.sschudakov.transport_task.solving;

import com.sschudakov.transport_task.table.MeshNode;
import com.sschudakov.transport_task.table.TransportTableBasis;
import com.sschudakov.transport_task.table.TransportTaskTable;
import com.sschudakov.transport_task.table_building.BasisBuilder;
import com.sschudakov.transport_task.table_building.TransportTaskUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 19.11.2017.
 */
public class TransportTaskSolver {

    public static void solve(TransportTaskTable table) {
        System.out.println("\nsolving\n");
        TransportTableBasis basis = BasisBuilder.buildBasis(table);
        PotentialsCalculator.calculatePotentials(table, basis);
        DeltasCalculator.calculateDeltas(table, basis);

        while (!TransportTaskUtils.isOptimal(table)) {
//        for (int i = 0; i < 3; i++) {
//            System.out.println("\nrecounted for the " + (i+1) + " time\n");
            recountTable(table, basis);
            table.outputTable();
            PotentialsCalculator.calculatePotentials(table, basis);
            DeltasCalculator.calculateDeltas(table, basis);
        }
        AnswerFormer.fromAnswer(table);
    }

    private static void recountTable(TransportTaskTable table, TransportTableBasis basis) {

        List<MeshNode> suspiciousNodes = SuspiciousMeshesFinder.findSuspiciousMeshes(table);
        System.out.println("\nsuspicious nodes: " + suspiciousNodes + "\n");

        MeshNode resolvingMesh = findMeshNodeWithGreatestDelta(suspiciousNodes);
        System.out.println("\nresolving mesh: " + resolvingMesh + "\n");

        List<MeshNode> tmpBasis = new ArrayList<>(basis.getBasicElements());
        tmpBasis.add(resolvingMesh);
        System.out.println("\ntemporary basis: " + tmpBasis + "\n");

        TransportTaskUtils.removeNotCyclicElements(tmpBasis);
        System.out.println("\ncycle: " + tmpBasis + "\n");

        moveCargo(basis, tmpBasis, resolvingMesh);
    }

    private static void moveCargo(TransportTableBasis basis, List<MeshNode> cycle, MeshNode resolvingElement) {

        List<MeshNode> minusElements = formMinusElementsList(cycle, resolvingElement);
        System.out.println("minus elements: " + minusElements);
        List<MeshNode> plusElements = formPlusElementsList(cycle, resolvingElement);
        System.out.println("plus elements: " + plusElements);


        MeshNode minElementMinusCycle = findMinElementMinusCycle(minusElements);
        System.out.println("minimum element of minus cycle: " + minElementMinusCycle);
        MeshNode minElementPlusCycle = findMinElementPlusCycle(plusElements);
        System.out.println("minimum element of plus cycle: " + minElementPlusCycle);

        int minusElementsTheta = minElementMinusCycle.getMesh().getValue();
        System.out.println("minus theta theta: " + minusElementsTheta);
        int plusElementsTheta = minElementPlusCycle.getMesh().getRestriction() - minElementPlusCycle.getMesh().getValue();
        System.out.println("plus elements theta: " + plusElementsTheta);

        int theta = min(plusElementsTheta, minusElementsTheta);
        System.out.println("theta:" + theta);

        for (MeshNode minusElement : minusElements) {
            minusElement.getMesh().setValue(
                    minusElement.getMesh().getValue() - theta
            );
        }

        for (MeshNode plusElement : plusElements) {
            plusElement.getMesh().setValue(
                    plusElement.getMesh().getValue() + theta
            );
        }

        if (minusElementsTheta <= plusElementsTheta) {
            basis.remove(minElementMinusCycle);
            basis.add(resolvingElement);
        } else {
            basis.remove(minElementPlusCycle);
            basis.add(resolvingElement);
        }

        System.out.println("new basis: " + basis);
    }

    private static List<MeshNode> formMinusElementsList(List<MeshNode> cycle, MeshNode resolvingElement) {

//        System.out.println("\nfinding minus elements\n");

        List<MeshNode> result = new ArrayList<>();
        MeshNode previousNode = null;
        MeshNode currentNode = null;
        MeshNode tmp = null;

        if (TransportTaskUtils.isLMesh(resolvingElement.getMesh())) {
            currentNode = TransportTaskUtils.findNextMesh(cycle, null, resolvingElement);
        }
        if (TransportTaskUtils.isRMesh(resolvingElement.getMesh())) {
            currentNode = resolvingElement;
        }

//        System.out.println("current node: " + currentNode);

        for (int i = 0; i < cycle.size(); i += 2) {
            result.add(currentNode);


            tmp = currentNode;
//            System.out.println("previous node: " + previousNode);
            currentNode = TransportTaskUtils.findNextMesh(cycle, previousNode, currentNode);
//            System.out.println("current node: " + currentNode);
            previousNode = tmp;


            tmp = currentNode;
//            System.out.println("previous node: " + previousNode);
            currentNode = TransportTaskUtils.findNextMesh(cycle, previousNode, currentNode);
//            System.out.println("current node: " + currentNode);
            previousNode = tmp;
        }
        return result;
    }

    private static List<MeshNode> formPlusElementsList(List<MeshNode> cycle, MeshNode resolvingElement) {

//        System.out.println("\nfinding plus elements\n");

        List<MeshNode> result = new ArrayList<>();
        MeshNode previousNode = null;
        MeshNode currentNode = null;
        MeshNode tmp = null;

        if (TransportTaskUtils.isLMesh(resolvingElement.getMesh())) {
            currentNode = resolvingElement;
        }
        if (TransportTaskUtils.isRMesh(resolvingElement.getMesh())) {
            currentNode = TransportTaskUtils.findNextMesh(cycle, null, resolvingElement);
        }

//        System.out.println("current node: " + currentNode);

        for (int i = 0; i < cycle.size(); i += 2) {
            result.add(currentNode);


            tmp = currentNode;
//            System.out.println("previous node: " + previousNode);
            currentNode = TransportTaskUtils.findNextMesh(cycle, previousNode, currentNode);
//            System.out.println("current node: " + currentNode);
            previousNode = tmp;


            tmp = currentNode;
//            System.out.println("previous node: " + previousNode);
            currentNode = TransportTaskUtils.findNextMesh(cycle, previousNode, currentNode);
//            System.out.println("current node: " + currentNode);
            previousNode = tmp;
        }
        return result;
    }

    private static MeshNode findMinElementMinusCycle(List<MeshNode> elements) {
        MeshNode result = elements.get(0);

        for (MeshNode element : elements) {
            if (element.getMesh().getValue() < result.getMesh().getValue()) {
                result = element;
            }
        }
        return result;
    }

    private static MeshNode findMinElementPlusCycle(List<MeshNode> elements) {
        MeshNode result = elements.get(0);

        for (MeshNode element : elements) {
            if (element.getMesh().getRestriction() - element.getMesh().getValue() <
                    result.getMesh().getRestriction() - result.getMesh().getValue()) {
                result = element;
            }
        }
        return result;
    }

    private static MeshNode findMeshNodeWithGreatestDelta(List<MeshNode> suspiciousNodes) {
        MeshNode result = suspiciousNodes.get(0);
        for (MeshNode suspiciousNode : suspiciousNodes) {
            if (result.getMesh().getDelta().doubleValue() < suspiciousNode.getMesh().getDelta().doubleValue()) {
                result = suspiciousNode;
            }
        }
        return result;
    }

    private static int min(int a, int b) {
        return a > b ? b : a;
    }
}

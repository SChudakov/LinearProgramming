package com.sschudakov.transporttask.solving;

import com.sschudakov.transporttask.table.TTBasis;
import com.sschudakov.transporttask.table.TTTable;
import com.sschudakov.transporttask.table.TTTableNode;
import com.sschudakov.transporttask.table_building.TTBasisBuilder;
import com.sschudakov.transporttask.table_building.TTUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 19.11.2017.
 */
public class TTSolver {

    private TTBasisBuilder ttBasisBuilder;
    private TTPotentialsCalculator ttPotentialsCalculator;
    private TTDeltasCalculator ttDeltasCalculator;

    public TTSolver() {
        this.ttBasisBuilder = new TTBasisBuilder();
        this.ttPotentialsCalculator = new TTPotentialsCalculator();
        this.ttDeltasCalculator = new TTDeltasCalculator();
    }

    public void solve(TTTable table) {
        System.out.println("\nsolving\n");
        TTBasis ttBasis = this.ttBasisBuilder.buildBasis(table);
        this.ttPotentialsCalculator.calculatePotentials(table, ttBasis);
        this.ttDeltasCalculator.calculateDeltas(table, ttBasis);

        while (!TTUtils.isOptimal(table)) {
//        for (int i = 0; i < 3; i++) {
//            System.out.println("\nrecounted for the " + (i+1) + " time\n");
            recountTable(table, ttBasis);
            table.outputTable();
            this.ttPotentialsCalculator.calculatePotentials(table, ttBasis);
            this.ttDeltasCalculator.calculateDeltas(table, ttBasis);
        }
        AnswerFormer.fromAnswer(table);
    }

    private void recountTable(TTTable table, TTBasis basis) {

        List<TTTableNode> suspiciousNodes = SuspiciousMeshesFinder.findSuspiciousMeshes(table);
        System.out.println("\nsuspicious nodes: " + suspiciousNodes + "\n");

        TTTableNode resolvingMesh = findMeshNodeWithGreatestDelta(suspiciousNodes);
        System.out.println("\nresolving mesh: " + resolvingMesh + "\n");

        List<TTTableNode> tmpBasis = new ArrayList<>(basis.getBasicElements());
        tmpBasis.add(resolvingMesh);
        System.out.println("\ntemporary basis: " + tmpBasis + "\n");

        TTUtils.removeNotCyclicElements(tmpBasis);
        System.out.println("\ncycle: " + tmpBasis + "\n");

        moveCargo(basis, tmpBasis, resolvingMesh);
    }

    private void moveCargo(TTBasis basis, List<TTTableNode> cycle, TTTableNode resolvingElement) {

        List<TTTableNode> minusElements = formMinusElementsList(cycle, resolvingElement);
        System.out.println("minus elements: " + minusElements);
        List<TTTableNode> plusElements = formPlusElementsList(cycle, resolvingElement);
        System.out.println("plus elements: " + plusElements);


        TTTableNode minElementMinusCycle = findMinElementMinusCycle(minusElements);
        System.out.println("minimum element of minus cycle: " + minElementMinusCycle);
        TTTableNode minElementPlusCycle = findMinElementPlusCycle(plusElements);
        System.out.println("minimum element of plus cycle: " + minElementPlusCycle);

        int minusElementsTheta = minElementMinusCycle.getMesh().getValue();
        System.out.println("minus theta theta: " + minusElementsTheta);
        int plusElementsTheta = minElementPlusCycle.getMesh().getRestriction() - minElementPlusCycle.getMesh().getValue();
        System.out.println("plus elements theta: " + plusElementsTheta);

        int theta = min(plusElementsTheta, minusElementsTheta);
        System.out.println("theta:" + theta);

        for (TTTableNode minusElement : minusElements) {
            minusElement.getMesh().setValue(
                    minusElement.getMesh().getValue() - theta
            );
        }

        for (TTTableNode plusElement : plusElements) {
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

    private List<TTTableNode> formMinusElementsList(List<TTTableNode> cycle, TTTableNode resolvingElement) {

//        System.out.println("\nfinding minus elements\n");

        List<TTTableNode> result = new ArrayList<>();
        TTTableNode previousNode = null;
        TTTableNode currentNode = null;
        TTTableNode tmp = null;

        if (TTUtils.isLMesh(resolvingElement.getMesh())) {
            currentNode = TTUtils.findNextMesh(cycle, null, resolvingElement);
        }
        if (TTUtils.isRMesh(resolvingElement.getMesh())) {
            currentNode = resolvingElement;
        }

//        System.out.println("current node: " + currentNode);

        for (int i = 0; i < cycle.size(); i += 2) {
            result.add(currentNode);


            tmp = currentNode;
//            System.out.println("previous node: " + previousNode);
            currentNode = TTUtils.findNextMesh(cycle, previousNode, currentNode);
//            System.out.println("current node: " + currentNode);
            previousNode = tmp;


            tmp = currentNode;
//            System.out.println("previous node: " + previousNode);
            currentNode = TTUtils.findNextMesh(cycle, previousNode, currentNode);
//            System.out.println("current node: " + currentNode);
            previousNode = tmp;
        }
        return result;
    }

    private List<TTTableNode> formPlusElementsList(List<TTTableNode> cycle, TTTableNode resolvingElement) {

//        System.out.println("\nfinding plus elements\n");

        List<TTTableNode> result = new ArrayList<>();
        TTTableNode previousNode = null;
        TTTableNode currentNode = null;
        TTTableNode tmp = null;

        if (TTUtils.isLMesh(resolvingElement.getMesh())) {
            currentNode = resolvingElement;
        }
        if (TTUtils.isRMesh(resolvingElement.getMesh())) {
            currentNode = TTUtils.findNextMesh(cycle, null, resolvingElement);
        }

//        System.out.println("current node: " + currentNode);

        for (int i = 0; i < cycle.size(); i += 2) {
            result.add(currentNode);


            tmp = currentNode;
//            System.out.println("previous node: " + previousNode);
            currentNode = TTUtils.findNextMesh(cycle, previousNode, currentNode);
//            System.out.println("current node: " + currentNode);
            previousNode = tmp;


            tmp = currentNode;
//            System.out.println("previous node: " + previousNode);
            currentNode = TTUtils.findNextMesh(cycle, previousNode, currentNode);
//            System.out.println("current node: " + currentNode);
            previousNode = tmp;
        }
        return result;
    }

    private TTTableNode findMinElementMinusCycle(List<TTTableNode> elements) {
        TTTableNode result = elements.get(0);

        for (TTTableNode element : elements) {
            if (element.getMesh().getValue() < result.getMesh().getValue()) {
                result = element;
            }
        }
        return result;
    }

    private TTTableNode findMinElementPlusCycle(List<TTTableNode> elements) {
        TTTableNode result = elements.get(0);

        for (TTTableNode element : elements) {
            if (element.getMesh().getRestriction() - element.getMesh().getValue() <
                    result.getMesh().getRestriction() - result.getMesh().getValue()) {
                result = element;
            }
        }
        return result;
    }

    private TTTableNode findMeshNodeWithGreatestDelta(List<TTTableNode> suspiciousNodes) {
        TTTableNode result = suspiciousNodes.get(0);
        for (TTTableNode suspiciousNode : suspiciousNodes) {
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

package com.sschudakov.transport_task.table_building;

import com.sschudakov.transport_task.table.MeshNode;
import com.sschudakov.transport_task.table.TransportTableBasis;
import com.sschudakov.transport_task.table.TransportTaskTable;
import com.sschudakov.transport_task.table.TransportTaskTableMesh;

import java.util.List;
import java.util.Vector;

/**
 * Created by Semen Chudakov on 16.11.2017.
 */
public class TransportTaskUtils {
    public static int tellRowSum(TransportTaskTable table, int row) {
        TransportTaskTableMesh[][] mainTable = table.getMainTable();
        int numOfProducers = table.getNumOfProducers();
        int result = 0;
        for (int i = 0; i < numOfProducers; i++) {
            if (mainTable[row][i].getValue() != null) {
                result += mainTable[row][i].getValue();
            } else {
                result += 0;
            }
        }
        return result;
    }

    public static int tellColumnSum(TransportTaskTable table, int column) {
        TransportTaskTableMesh[][] mainTable = table.getMainTable();
        int numOfConsumers = table.getNumOfConsumers();
        int result = 0;
        for (int i = 0; i < numOfConsumers; i++) {
            if (mainTable[i][column].getValue() != null) {
                result += mainTable[i][column].getValue();
            } else {
                column += 0;
            }
        }
        return result;
    }

    public static void copyTable(TransportTaskTableMesh[][] from, TransportTaskTableMesh[][] to) {

        if (from.length > to.length) {
            throw new IllegalArgumentException("from length (" + from.length + ") is greater than to length " + to.length);
        }

        for (int i = 0; i < from.length; i++) {
            if (from[i].length > to[i].length) {
                throw new IllegalArgumentException("from[" + i + "] length (" + from[i].length
                        + ") is greater than to[" + i + "] length (" + to[i].length + ")");
            }
        }

        for (int i = 0; i < from.length; i++) {
            for (int j = 0; j < from[i].length; j++) {
                to[i][j] = from[i][j];
            }
        }
    }


    public static boolean wouldCreateCycle(TransportTableBasis basis, MeshNode node) {
//        if (basis.isComplete()) {
//            throw new IllegalArgumentException("basis is already complete");
//        }
        TransportTableBasis tmpBasis = new TransportTableBasis(basis.getNumOfBasicElements());
        for (MeshNode meshNode : basis.getBasicElements()) {
            tmpBasis.add(meshNode);
        }
        tmpBasis.add(node);
        return hasCycles(tmpBasis);
    }

    private static boolean hasCycles(TransportTableBasis basis) {

        List<MeshNode> basicElements = basis.getBasicElements();

        removeNotCyclicElements(basicElements);

        return basicElements.size() != 0;
    }

    public static void removeNotCyclicElements(List<MeshNode> basicElements) {
//        System.out.println("basic elements: " + basicElements);
        int numOfBasicElements = basicElements.size();

        for (int i = 0; i < numOfBasicElements; i++) {
            for (int j = 0; j < basicElements.size(); j++) {

                MeshNode currentElement = basicElements.get(j);
//                System.out.println("current element: " + currentElement);

                if (tellNumInRow(basicElements, currentElement.getI()) == 1
                        || tellNumInColumn(basicElements, currentElement.getJ()) == 1) {
                    basicElements.remove(j);
//                    System.out.println("removed");
//                    System.out.println("basic elements: " + basicElements);
                    if (basicElements.size() == 0) {
                        break;
                    }
                }
            }
        }
    }

    private static int tellNumInRow(List<MeshNode> basicElements, int row) {
        int result = 0;
        for (MeshNode basicElement : basicElements) {
            if (basicElement.getI() == row) {
                result++;
            }
        }
//        System.out.println("num in row: " + result);
        return result;
    }

    private static int tellNumInColumn(List<MeshNode> basicElements, int column) {
        int result = 0;
        for (MeshNode basicElement : basicElements) {
            if (basicElement.getJ() == column) {
                result++;
            }
        }
//        System.out.println("num in column: " + result);
        return result;
    }

    public static Vector<Integer> formEmptyVector(int length) {
        Vector<Integer> result = new Vector<>();
        for (int i = 0; i < length; i++) {
            result.add(null);
        }
        return result;
    }

    public static boolean isSuspicious(TransportTaskTableMesh mesh) {
        return TransportTaskUtils.isRMesh(mesh) || TransportTaskUtils.isLMesh(mesh);
    }

    public static boolean isLMesh(TransportTaskTableMesh mesh) {
        if (mesh.getDelta() == null) {
            return false;
        }
        return mesh.getValue().doubleValue() == 0
                && mesh.getDelta().doubleValue() < 0;
    }

    public static boolean isRMesh(TransportTaskTableMesh mesh) {
        if (mesh.getDelta() == null) {
            return false;
        }
        return mesh.getValue().doubleValue() == mesh.getRestriction().doubleValue()
                && mesh.getDelta().doubleValue() > 0;
    }

    public static boolean isOptimal(TransportTaskTable table) {

        for (TransportTaskTableMesh[] transportTaskTableMeshes : table.getMainTable()) {
            for (TransportTaskTableMesh transportTaskTableMesh : transportTaskTableMeshes) {
                if (isLMesh(transportTaskTableMesh)
                        || isRMesh(transportTaskTableMesh)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static MeshNode findNextMesh(List<MeshNode> nodes, MeshNode previousNode, MeshNode currentNode) {
        int currentRow = currentNode.getI();
        int currentColumn = currentNode.getJ();

        for (MeshNode node : nodes) {
            if (node.getI() == currentRow || node.getJ() == currentColumn) {
                if (!node.equals(previousNode) && !node.equals(currentNode)) {
                    return node;
                }
            }
        }

        throw new IllegalArgumentException("there is no next element for " + currentColumn + " in "
                + nodes + " with previous: " + previousNode);
    }

    public static int tellConsumersValuesSum(TransportTaskTable table) {
        return vectorsValuesSum(table.getConsumersVector());
    }

    public static int tellProducersValuesSum(TransportTaskTable table) {
        return vectorsValuesSum(table.getProducersVector());
    }

    private static int vectorsValuesSum(Vector<Integer> vector) {
        int result = 0;
        for (Integer integer : vector) {
            result += integer;
        }
        return result;
    }
}

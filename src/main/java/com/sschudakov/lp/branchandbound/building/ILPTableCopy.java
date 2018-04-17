package com.sschudakov.lp.branchandbound.building;

import com.sschudakov.lp.branchandbound.table.ILPTable;
import com.sschudakov.lp.simplexmethod.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class executes {@link ILPTable} copying process.
 *
 * @see ILPTable
 */
public class ILPTableCopy {
    /**
     * Constructor.
     */
    public ILPTableCopy() {
    }

    /**
     * This method creates a copy of an {@link ILPTable}
     * by invoking all its setters.
     *
     * @param ilpTable table to be copied
     * @return an object of table
     * with the same content as (@code ilpTable)
     */
    public ILPTable copy(ILPTable ilpTable) {
        ILPTable result = new ILPTable();

        result.setNumOfVariables(ilpTable.getNumOfVariables());
        result.setNumOfEquations(ilpTable.getNumOfEquations());
        result.setNumOfInitialVariables(ilpTable.getNumOfInitialVariables());
        result.setNumOfMVariables(ilpTable.getNumOfMVariables());

        result.setIntegerVariables(copyList(ilpTable.getIntegerVariables()));


        result.setFunction(copyList(ilpTable.getFunction()));
        result.setTaskType(ilpTable.getTaskType());
        result.setMainTable(Utils.copyTable(ilpTable.getMainTable()));

        result.setRestrictionsVector(copyList(ilpTable.getRestrictionsVector()));
        result.setEquationsSigns(copyList(ilpTable.getEquationsSigns()));

        result.setBasicVariables(copyList(ilpTable.getBasicVariables()));

        result.setDeltasVector(copyList(ilpTable.getDeltasVector()));
        result.setSimplexRatios(copyList(ilpTable.getSimplexRatios()));

        result.setFunctionValue(ilpTable.getFunctionValue());

        return result;
    }

    /**
     * This method copies a given {@code list}.
     *
     * @param <T>  type of the objets in the {@code list}
     * @param list list to be copied
     * @return copy of a given list
     */
    private <T> List<T> copyList(List<T> list) {
        if (list == null) {
            return null;
        } else {
            return new ArrayList<>(list);
        }
    }
}

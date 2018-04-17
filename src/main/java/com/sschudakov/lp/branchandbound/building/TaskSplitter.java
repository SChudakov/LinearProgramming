package com.sschudakov.lp.branchandbound.building;

import com.sschudakov.lp.branchandbound.table.ILPTable;
import com.sschudakov.lp.simplexmethod.table.LPRestriction;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines a process of splitting a
 * {@link ILPTable} with non-integer variable
 * into two no tables.
 *
 * @see ILPTable
 */
public class TaskSplitter {

    /**
     * Objects that builds cutting restrictions for new tables.
     */
    private CuttingRestrictionsBuilder restrictionsBuilder;
    /**
     * Object that copies table twice while splitting.
     */
    private ILPTableCopy tableCopy;
    /**
     * Objects that adds built restrictions to copied tables.
     */
    private LPRestrictionAdd lpRestrictionAdd;

    /**
     * Initializes {@link #restrictionsBuilder},
     * {@link #tableCopy} and {@link #lpRestrictionAdd}
     * fields.
     */
    public TaskSplitter() {
        this.restrictionsBuilder = new CuttingRestrictionsBuilder();
        this.tableCopy = new ILPTableCopy();
        this.lpRestrictionAdd = new LPRestrictionAdd();
    }

    /**
     * Splits a given {@code ilpTable} into tow new tables.
     * <p>
     * When given a {@code ilpTable}, in which the {@code basicVariable}
     * has non-integer {@code basicVariableValue} splits the table into two
     * new tables, to both of which one new cutting condition is added. that
     * makes {@code basicVariable} be integer.
     *
     * @param ilpTable           table to be split
     * @param basicVariable      variable that is non-integer
     * @param basicVariableValue value of the {@code basicVariable}
     * @return list with 2 elements - two new tables.
     * @see ILPTable
     */
    public List<ILPTable> splitTask(ILPTable ilpTable, Integer basicVariable, Double basicVariableValue) {
        List<LPRestriction> restrictions = this.restrictionsBuilder.buildCuttingRestrictions(ilpTable, basicVariable, basicVariableValue);

        ILPTable firstTable = this.tableCopy.copy(ilpTable);
        ILPTable secondTable = this.tableCopy.copy(ilpTable);

        this.lpRestrictionAdd.addRestriction(firstTable, restrictions.get(0));
        this.lpRestrictionAdd.addRestriction(secondTable, restrictions.get(1));

        List<ILPTable> result = new ArrayList<>();
        result.add(firstTable);
        result.add(secondTable);

        return result;
    }


}

package com.sschudakov.branchandbound.building;

import com.sschudakov.branchandbound.table.ILPTable;

import java.util.ArrayList;
import java.util.List;

public class TaskSplitter {

    private CuttingRestrictionsBuilder restrictionsBuilder;
    private ILPTableCopy tableCopy;
    private RestrictionAdd restrictionAdd;

    public TaskSplitter() {
        this.restrictionsBuilder = new CuttingRestrictionsBuilder();
        this.tableCopy = new ILPTableCopy();
        this.restrictionAdd = new RestrictionAdd();
    }

    public List<ILPTable> splitTask(ILPTable ilpTable, Integer basicVariableIndex) {
        List<LPRestriction> restrictions = this.restrictionsBuilder
                .buildCuttingRestrictions(ilpTable, basicVariableIndex);

        ILPTable firstTable = this.tableCopy.copy(ilpTable);
        ILPTable secondTable = this.tableCopy.copy(ilpTable);

        this.restrictionAdd.addRestriction(firstTable, restrictions.get(0));
        this.restrictionAdd.addRestriction(secondTable, restrictions.get(1));

        List<ILPTable> result = new ArrayList<>();
        result.add(firstTable);
        result.add(firstTable);

        return result;
    }


}

package com.sschudakov.lp.simplexmethod.solving;

import com.sschudakov.lp.simplexmethod.table.LPSolution;
import com.sschudakov.lp.simplexmethod.table.LPTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Semen Chudakov on 10.09.2017.
 */
public class LPSolver {

    private static Logger logger = LogManager.getLogger(LPSolver.class);

    private LPTableRecount lpTableRecount;
    private LPSolutionFormer solutionFormer;

    public LPSolver() {
        this.lpTableRecount = new LPTableRecount();
        this.solutionFormer = new LPSolutionFormer();
    }

    public LPSolution solveLP(LPTable lpTable) {
        int iteration = 1;
        LPSolution result;
        try {
            while (!lpTable.isDualOptimal()) {
                this.lpTableRecount.recountSimplexTable(lpTable);
                logger.info("recounted for the " + iteration++ + " time\n");
                lpTable.outputTable();
            }
            result = this.solutionFormer.formSolution(lpTable);
        } catch (Exception e) {
            result = this.solutionFormer.formSolution(e);
        }
        return result;
    }


}
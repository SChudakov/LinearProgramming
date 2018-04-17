package com.sschudakov.lp.branchandbound.solver

import com.sschudakov.lp.branchandbound.table.ILPTable
import com.sschudakov.lp.simplexmethod.enumerable.Sign
import com.sschudakov.lp.simplexmethod.enumerable.TaskType
import com.sschudakov.lp.simplexmethod.solver.LPSolution
import spock.lang.Shared
import spock.lang.Specification

class BABILPSolverSpec extends Specification {
    /**
     * Object of the solver to be tested.
     */
    @Shared
    private BABILPSolver babilpSolver

    /**
     * Initializes {@link #babilpSolver} filed.
     * @return
     */
    def setup() {
        this.babilpSolver = new BABILPSolver()
    }

    def "ensure answer is correct"() {

        given:
        ILPTable ilpTable = new ILPTable()
        ilpTable.numOfVariables = 2
        ilpTable.numOfInitialVariables = 2
        ilpTable.numOfEquations = 2

        ilpTable.function = [-1.0D, -3.0D]

        ilpTable.taskType = TaskType.MIN

        ilpTable.mainTable = [
                [1.0D, 4.0D],
                [2.0D, 3.0D]
        ]

        ilpTable.equationsSigns = [Sign.LESS_THAN_OR_EQUAL_TO, Sign.LESS_THAN_OR_EQUAL_TO]

        ilpTable.restrictionsVector = [14.0D, 12.0D]

        ilpTable.integerVariables = [0, 1]

        when:
        LPSolution solution = this.babilpSolver.solve(ilpTable)

        then:
        notThrown(Exception)
        solution.solutionVector == [1.0D, 3.0D, 1.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D]
        solution.functionValue == -10.0D
    }
}

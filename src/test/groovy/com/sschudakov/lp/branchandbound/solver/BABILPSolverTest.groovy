package com.sschudakov.lp.branchandbound.solver

import com.sschudakov.lp.branchandbound.table.ILPTable
import com.sschudakov.lp.simplexmethod.enumerable.Sign
import com.sschudakov.lp.simplexmethod.enumerable.TaskType
import com.sschudakov.lp.simplexmethod.table.LPSolution
import com.sschudakov.lp.simplexmethod.table.LPRestriction
import spock.lang.Shared
import spock.lang.Specification

class BABILPSolverTest extends Specification {
    /**
     * Object of the solving to be tested.
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

    def "task 1 answer is correct"() {

        given:
        ILPTable ilpTable = new ILPTable()
        ilpTable.numOfVariables = 2
        ilpTable.numOfInitialVariables = 2
        ilpTable.numOfEquations = 3

        ilpTable.function = [1.0D, 1.0D]

        ilpTable.taskType = TaskType.MAX

        ilpTable.mainTable = [
                new LPRestriction([2.0D, 11.0D], Sign.LESS_THAN_OR_EQUAL_TO, 36.0D, null),
                new LPRestriction([1.0D, 1.0D], Sign.LESS_THAN_OR_EQUAL_TO, 7.0D, null),
                new LPRestriction([4.0D, -5.0D], Sign.LESS_THAN_OR_EQUAL_TO, 5.0D, null)
        ]

        ilpTable.integerVariables = [0, 1]

        when:
        LPSolution solution = this.babilpSolver.solve(ilpTable)

        then:
        notThrown(Exception)
        solution.solutionVector.get(0) == 3.0D
        solution.solutionVector.get(1) == 2.0D
        solution.functionValue == 5.0D
    }

    def "task 2 answer is correct"() {

        given:
        ILPTable ilpTable = new ILPTable()
        ilpTable.numOfVariables = 2
        ilpTable.numOfInitialVariables = 2
        ilpTable.numOfEquations = 3

        ilpTable.function = [-2.0D, -1.0D]

        ilpTable.taskType = TaskType.MIN

        ilpTable.mainTable = [
                new LPRestriction([6.0D, 4.0D], Sign.LESS_THAN_OR_EQUAL_TO, 24.0D, null),
                new LPRestriction([1.0D, -1.0D], Sign.LESS_THAN_OR_EQUAL_TO, 3.0D, null),
                new LPRestriction([-1.0D, 3.0D], Sign.LESS_THAN_OR_EQUAL_TO, 3.0D, null)
        ]
        ilpTable.integerVariables = [0, 1]

        when:
        LPSolution solution = this.babilpSolver.solve(ilpTable)

        then:
        notThrown(Exception)
        solution.solutionVector.get(0) == 3.0D
        solution.solutionVector.get(1) == 1.0D
        solution.functionValue == -7.0D
    }

    def "task 3 answer is correct"() {

        given:
        ILPTable ilpTable = new ILPTable()
        ilpTable.numOfVariables = 2
        ilpTable.numOfInitialVariables = 2
        ilpTable.numOfEquations = 3

        ilpTable.function = [6.0D, 4.0D]

        ilpTable.taskType = TaskType.MIN

        ilpTable.mainTable = [
                new LPRestriction([2.0D, 1.0D], Sign.GREATER_THAN_OR_EQUAL_TO, 3.0D, null),
                new LPRestriction([1.0D, -2.0D], Sign.LESS_THAN_OR_EQUAL_TO, 2.0D, null),
                new LPRestriction([3.0D, 2.0D], Sign.GREATER_THAN_OR_EQUAL_TO, 1.0D, null)
        ]
        ilpTable.integerVariables = [0, 1]

        when:
        LPSolution solution = this.babilpSolver.solve(ilpTable)

        then:
        notThrown(Exception)
        solution.solutionVector.get(0) == 1.0D
        solution.solutionVector.get(1) == 1.0D
        solution.functionValue == 10.0D
    }

    def "task 4 answer is correct"() {

        given:
        ILPTable ilpTable = new ILPTable()
        ilpTable.numOfVariables = 2
        ilpTable.numOfInitialVariables = 2
        ilpTable.numOfEquations = 3

        ilpTable.function = [1.0D, 1.0D]

        ilpTable.taskType = TaskType.MIN

        ilpTable.mainTable = [
                new LPRestriction([-1.0D, -1.0D], Sign.LESS_THAN_OR_EQUAL_TO, -1.0D, null),
                new LPRestriction([-2.0D, 2.0D], Sign.LESS_THAN_OR_EQUAL_TO, -2.0D, null),
                new LPRestriction([-4.0D, 2.0D], Sign.LESS_THAN_OR_EQUAL_TO, -1.0D, null)
        ]
        ilpTable.integerVariables = [0, 1]

        when:
        LPSolution solution = this.babilpSolver.solve(ilpTable)

        then:
        notThrown(Exception)
        solution.solutionVector.get(0) == 1.0D
        solution.solutionVector.get(1) == 0.0D
        solution.functionValue == 1.0D
    }

    def "task 5 answer is correct"() {

        given:
        ILPTable ilpTable = new ILPTable()
        ilpTable.numOfVariables = 2
        ilpTable.numOfInitialVariables = 2
        ilpTable.numOfEquations = 3

        ilpTable.function = [5.0D, 7.0D]

        ilpTable.taskType = TaskType.MIN

        ilpTable.mainTable = [
                new LPRestriction([-10.0D, 1.0D], Sign.LESS_THAN_OR_EQUAL_TO, -16.0D, null),
                new LPRestriction([-3.0D, -3.0D], Sign.LESS_THAN_OR_EQUAL_TO, -12.0D, null),
                new LPRestriction([-6.0D, -2.0D], Sign.LESS_THAN_OR_EQUAL_TO, -17.0D, null)
        ]
        ilpTable.integerVariables = [0, 1]

        when:
        LPSolution solution = this.babilpSolver.solve(ilpTable)

        then:
        notThrown(Exception)
        solution.solutionVector.get(0) == 4.0D
        solution.solutionVector.get(1) == 0.0D
        solution.functionValue == 20.0D
    }

    def "task 6 answer is correct"() {

        given:
        ILPTable ilpTable = new ILPTable()
        ilpTable.numOfVariables = 2
        ilpTable.numOfInitialVariables = 2
        ilpTable.numOfEquations = 3

        ilpTable.function = [2.0D, -1.0D]

        ilpTable.taskType = TaskType.MAX

        ilpTable.mainTable = [
                new LPRestriction([2.0D, 1.0D], Sign.LESS_THAN_OR_EQUAL_TO, 8.0D, null),
                new LPRestriction([1.0D, 3.0D], Sign.GREATER_THAN_OR_EQUAL_TO, 6.0D, null),
                new LPRestriction([3.0D, 2.0D], Sign.GREATER_THAN_OR_EQUAL_TO, 3.0D, null)
        ]
        ilpTable.integerVariables = [0, 1]

        when:
        LPSolution solution = this.babilpSolver.solve(ilpTable)

        then:
        notThrown(Exception)
        solution.solutionVector.get(0) == 3.0D
        solution.solutionVector.get(1) == 1.0D
        solution.functionValue == 5.0D
    }

    def "task 7 answer is correct"() {

        given:
        ILPTable ilpTable = new ILPTable()
        ilpTable.numOfVariables = 2
        ilpTable.numOfInitialVariables = 2
        ilpTable.numOfEquations = 2

        ilpTable.function = [-1.0D, -3.0D]

        ilpTable.taskType = TaskType.MIN

        ilpTable.mainTable = [
                new LPRestriction([1.0D, 4.0D], Sign.LESS_THAN_OR_EQUAL_TO, 14.0D, null),
                new LPRestriction([2.0D, 3.0D], Sign.LESS_THAN_OR_EQUAL_TO, 12.0D, null)
        ]
        ilpTable.integerVariables = [0, 1]

        when:
        LPSolution solution = this.babilpSolver.solve(ilpTable)

        then:
        notThrown(Exception)
        solution.solutionVector == [1.0D, 3.0D, 1.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D]
        solution.functionValue == -10.0D
    }
}

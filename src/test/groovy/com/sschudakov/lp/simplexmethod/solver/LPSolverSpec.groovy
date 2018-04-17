package com.sschudakov.lp.simplexmethod.solver

import com.sschudakov.lp.simplexmethod.building.LPTableBuilder
import com.sschudakov.lp.simplexmethod.enumerable.Sign
import com.sschudakov.lp.simplexmethod.enumerable.TaskType
import com.sschudakov.lp.simplexmethod.exception.NoSolutionException
import com.sschudakov.lp.simplexmethod.table.LPTable
import spock.lang.Shared
import spock.lang.Specification

class LPSolverSpec extends Specification {

    /**
     * Object to be tested.
     */
    @Shared
    LPSolver lpSolver

    def setup() {
        this.lpSolver = new LPSolver()
    }

    def "solution exists test 1"() {
        given:
        LPTable table = new LPTable()
        table.numOfVariables = 3
        table.numOfInitialVariables = 3
        table.numOfEquations = 3

        table.function = [15.0D, 7.0D, 12.0D]

        table.taskType = TaskType.MIN

        table.mainTable = [
                [-1.0D, -1.0D, -2.0D],
                [-3.0D, -1.0D, -1.0D],
                [-5.0D, -1.0D, -4.0D]
        ]

        table.equationsSigns = [Sign.LESS_THAN_OR_EQUAL_TO, Sign.LESS_THAN_OR_EQUAL_TO, Sign.LESS_THAN_OR_EQUAL_TO]

        table.restrictionsVector = [-2.0D, -3.0D, -1.0D]
        LPTableBuilder builder = new LPTableBuilder()
        builder.buildSimplexTable(table)

        when:
        def solution = this.lpSolver.solveLP(table)

        then:
        !solution.endedWithException()
        solution.getSolutionVector() == [0.5D, 1.5D, 0.0D, 0.0D, 0.0D, 3.0D]
        solution.getFunctionValue() == 18.0
    }

    def "solution exists test 2"() {
        given:
        /* "2 2" +
                " -1 -3" +
                " min" +
                " 1 4" +
                " 2 3" +
                " <= <=" +
                " 14 12"*/
        LPTable table = new LPTable()
        table.numOfVariables = 2
        table.numOfInitialVariables = 2
        table.numOfEquations = 2

        table.function = [-1.0D, -3.0D]

        table.taskType = TaskType.MIN

        table.mainTable = [
                [1.0D, 4.0D],
                [2.0D, 3.0D]
        ]

        table.equationsSigns = [Sign.LESS_THAN_OR_EQUAL_TO, Sign.LESS_THAN_OR_EQUAL_TO]

        table.restrictionsVector = [14.0D, 12.0D]
        LPTableBuilder builder = new LPTableBuilder()
        builder.buildSimplexTable(table)

        when:
        def solution = this.lpSolver.solveLP(table)

        then:
        !solution.endedWithException()
        solution.getSolutionVector() == [1.2D, 3.2D, 0.0D, 0.0D, 0.0D, 0.0D]
        solution.getFunctionValue() == -10.8D
    }

    def "no solution test"() {

        given:
        LPTable table = new LPTable()
        table.numOfVariables = 5
        table.numOfInitialVariables = 3
        table.numOfEquations = 3

        table.function = [1.0D, -1.0D, 1.0D, -3.0D, 2.0D]

        table.taskType = TaskType.MIN

        table.mainTable = [
                [1.0D, 2.0D, -1.0D, 2.0D, 1.0D],
                [-1.0D, -1.0D, 1.0D, -3.0D, -2.0D],
                [2.0D, -1.0D, 3.0D, -1.0D, 2.0D]
        ]

        table.equationsSigns = [Sign.LESS_THAN_OR_EQUAL_TO, Sign.EQUAL, Sign.GREATER_THAN_OR_EQUAL_TO]

        table.restrictionsVector = [8.0D, 10.0D, 4.0D]
        LPTableBuilder builder = new LPTableBuilder()
        builder.buildSimplexTable(table)

        when:
        def solution = this.lpSolver.solveLP(table)

        then:
        solution.endedWithException()
        solution.getSolvingException() instanceof NoSolutionException
    }
}

package com.sschudakov.lp.branchandbound.input

import com.sschudakov.lp.branchandbound.table.ILPTable
import com.sschudakov.lp.simplexmethod.enumerable.Sign
import com.sschudakov.lp.simplexmethod.enumerable.TaskType
import spock.lang.Specification

class ILPStringInputSpec extends Specification {

    /**
     * Object to be tested.
     */
    ILPStringInput ilpStringInput

    /**
     * Linear task the object will be tested on.
     */
    def linear_task = "5 3" +
            " 1 -1 1 -3 2" +
            " min" +
            " 1 2 -1 2 1" +
            " -1 -1 1 -3 -2" +
            " 2 -1 3 -1 2" +
            " <= = >=" +
            " 8 10 4"

    /**
     * String with integer variables indices.
     */
    def integerVariables = "0 1 2"

    def setup() {
        this.ilpStringInput = new ILPStringInput()
    }

    /**
     * This test ensures that all necessary setter are
     * invoked in {@link ILPTable} while it is filled with data.
     */
    def "test all setters are invoked"() {
        given:
        ILPTable ilpTable = Mock()
        when:
        this.ilpStringInput.inputILP(ilpTable, linear_task, integerVariables)

        then:
        1 * ilpTable.setNumOfVariables(_)
        1 * ilpTable.setNumOfEquations(_)
        1 * ilpTable.setFunction(_ as List<Double>)
        1 * ilpTable.setTaskType(_ as TaskType)
        1 * ilpTable.setMainTable(_ as double[][])
        1 * ilpTable.setEquationsSigns(_ as List<Sign>)
        1 * ilpTable.setRestrictionsVector(_ as List<Double>)

        1 * ilpTable.setNumOfInitialVariables(_)
        1 * ilpTable.setIntegerVariables(_ as List<Integer>)
    }

    /**
     * This test checks the content of an {@link ILPTable}
     * object after it is passed by parameter to a {@link ILPStringInput#inputILP}
     * method with (@code linear_task) and {@code integerVariables} as task parameters.
     */
    def "test input table content"() {
        given:
        def ilpTable = new ILPTable()
        when:
        this.ilpStringInput.inputILP(ilpTable, this.linear_task, this.integerVariables)
        then:
        with(ilpTable) {
            numOfVariables == 5
            numOfEquations == 3
            function == [1.0, -1.0, 1.0, -3.0, 2.0]
            mainTable == [
                    [1, 2, -1, 2, 1],
                    [-1, -1, 1, -3, -2],
                    [2, -1, 3, -1, 2]
            ]
            equationsSigns == [
                    Sign.LESS_THAN_OR_EQUAL_TO,
                    Sign.EQUAL,
                    Sign.GREATER_THAN_OR_EQUAL_TO
            ]
            restrictionsVector == [8.0, 10.0, 4.0]

            numOfInitialVariables == 5
            integerVariables == [0, 1, 2]
        }
    }
}

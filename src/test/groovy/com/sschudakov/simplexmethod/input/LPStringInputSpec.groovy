package com.sschudakov.simplexmethod.input

import com.sschudakov.simplexmethod.enumerable.Sign
import com.sschudakov.simplexmethod.enumerable.TaskType
import com.sschudakov.simplexmethod.table.LPTable
import spock.lang.Specification

/**
 * Test for the {@link LPStringInput} class
 *
 * @author Semen Chudakov
 * @since March 2018
 */
class LPStringInputSpec extends Specification {
    /**
     * Object to be tested.
     */
    LPStringInput lpStringInput
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

    def setup() {
        this.lpStringInput = new LPStringInput()
    }
    /**
     * This test ensures that all necessary setter are
     * invoked in {@link LPTable} while it is filled with data.
     */
    def "test all setters are invoked"() {
        given:
        LPTable lpTable = Mock()
        when:
        this.lpStringInput.inputLP(lpTable, linear_task)

        then:
        1 * lpTable.setNumOfVariables(_)
        1 * lpTable.setNumOfEquations(_)
        1 * lpTable.setFunction(_ as List<Double>)
        1 * lpTable.setTaskType(_ as TaskType)
        1 * lpTable.setMainTable(_ as double[][])
        1 * lpTable.setEquationsSigns(_ as List<Sign>)
        1 * lpTable.setRestrictionsVector(_ as List<Double>)
    }
    /**
     * This test checks the content of an {@link LPTable}
     * object after it is passed by parameter to a {@link LPStringInput#inputLP}
     * method with (@code linear_task) as task parameter.
     */
    def "test input table content"() {
        given:
        def lpTable = new LPTable()
        when:
        this.lpStringInput.inputLP(lpTable, this.linear_task)

        then:
        with(lpTable) {
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
        }
    }
}

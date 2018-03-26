package com.sschudakov.simplex_method.input

import com.sschudakov.simplex_method.enumerable.Sign
import com.sschudakov.simplex_method.table.LPTable
import com.sschudakov.task.LPTasks
import spock.lang.Specification

/**
 * Test for the {@link LPStringInput} class
 *
 * @author Semen Chudakov
 * @since March 2018
 */
class LPStringInputSpec extends Specification {

    LPStringInput lpStringInput
    def linear_task = LPTasks.LINEAR_TASK_1

    def setup() {
        this.lpStringInput = new LPStringInput()
    }

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

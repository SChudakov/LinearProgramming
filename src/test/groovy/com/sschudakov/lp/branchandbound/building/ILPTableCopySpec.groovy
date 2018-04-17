package com.sschudakov.lp.branchandbound.building

import com.sschudakov.lp.branchandbound.table.ILPTable
import com.sschudakov.lp.simplexmethod.enumerable.Sign
import com.sschudakov.lp.simplexmethod.enumerable.TaskType
import spock.lang.Shared
import spock.lang.Specification

class ILPTableCopySpec extends Specification {
    /**
     * Object to be tested.
     */
    @Shared
    ILPTableCopy ilpTableCopy
    /**
     * Table which content will be copied.
     */
    ILPTable ilpTable

    /**
     * Initializes {@link #ilpTableCopy}.
     */
    def setupSpec(){
        this.ilpTableCopy = new ILPTableCopy()
    }

    /**
     * Fill {@link #ilpTable} fields
     */
    def setup() {
        this.ilpTable = new ILPTable()
        this.ilpTable.numOfVariables = 2
        this.ilpTable.numOfInitialVariables = 2
        this.ilpTable.numOfEquations = 2
        this.ilpTable.integerVariables = [0, 1]


        this.ilpTable.function = [-1.0D, -3.0D]
        this.ilpTable.taskType = TaskType.MIN
        this.ilpTable.mainTable = [
                [1.0D, 4.0D],
                [2.0D, 3.0D]
        ]

        this.ilpTable.equationsSigns = [Sign.LESS_THAN_OR_EQUAL_TO, Sign.LESS_THAN_OR_EQUAL_TO]
        this.ilpTable.restrictionsVector = [14, 12]
    }

    /**
     * This tests checks the content of all fields
     * in a {@link ILPTable} copy.
     */
    def "test table fields content"() {
        when:
        def copiedTable = this.ilpTableCopy.copy(this.ilpTable)

        then:
        with(copiedTable) {
            numOfVariables == 2
            numOfInitialVariables == 2
            numOfEquations == 2
            numOfMVariables == null
            integerVariables == [0, 1]

            function == [-1.0D, -3.0D]
            taskType == TaskType.MIN
            mainTable == [
                    [1.0D, 4.0D],
                    [2.0D, 3.0D]
            ]

            equationsSigns == [Sign.LESS_THAN_OR_EQUAL_TO, Sign.LESS_THAN_OR_EQUAL_TO]
            restrictionsVector == [14.0D, 12.0D]

            basicVariables == null

            deltasVector == null
            simplexRatios == null

            functionValue == null
        }
    }
}

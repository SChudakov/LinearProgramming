package com.sschudakov.branchandbound.building

import com.sschudakov.branchandbound.table.ILPTable
import com.sschudakov.simplexmethod.enumerable.Sign
import com.sschudakov.simplexmethod.enumerable.TaskType
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
    @Shared
    ILPTable ilpTable

    /**
     * Initializes {@link #ilpTableCopy} fields
     */
    def setup() {
        this.ilpTableCopy = new ILPTableCopy()
        this.ilpTable = new ILPTable()

        this.ilpTable.numOfVariables = 2
        this.ilpTable.numOfInitialVariables = 2
        this.ilpTable.numOfEquations = 2
        this.ilpTable.integerVariables = [0, 1]


        this.ilpTable.function = [-1, -3]
        this.ilpTable.taskType = TaskType.MIN
        this.ilpTable.mainTable = [
                [1.0, 4.0],
                [2.0, 3.0]
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

            function == [-1, -3]
            taskType == TaskType.MIN
            mainTable == [
                    [1, 4],
                    [2, 3]
            ]

            equationsSigns == [Sign.LESS_THAN_OR_EQUAL_TO, Sign.LESS_THAN_OR_EQUAL_TO]
            restrictionsVector == [14, 12]

            basicVariables == null

            deltasVector == null
            simplexRatios == null

            functionValue == null
        }
    }
}

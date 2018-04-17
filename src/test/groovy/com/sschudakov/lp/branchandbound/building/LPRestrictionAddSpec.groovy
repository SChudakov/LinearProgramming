package com.sschudakov.lp.branchandbound.building

import com.sschudakov.lp.branchandbound.table.ILPTable
import com.sschudakov.lp.simplexmethod.enumerable.Sign
import com.sschudakov.lp.simplexmethod.enumerable.TaskType
import com.sschudakov.lp.simplexmethod.table.LPRestriction
import spock.lang.Shared
import spock.lang.Specification

class LPRestrictionAddSpec extends Specification {
    /**
     * Object to be tested.
     */
    @Shared
    private LPRestrictionAdd lpRestrictionAdd

    /**
     * Initializes the {@link #lpRestrictionAdd} field.
     */
    def setupSpec() {
        this.lpRestrictionAdd = new LPRestrictionAdd()
    }

    def "test addRestriction"() {
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

        List<Double> condition = [1.0D, 0.0D]
        Sign sign = Sign.LESS_THAN_OR_EQUAL_TO
        Double restrictionRightPart = 1.0
        LPRestriction lpRestriction = new LPRestriction(condition, sign, restrictionRightPart)

        when:
        this.lpRestrictionAdd.addRestriction(ilpTable, lpRestriction)

        then:
        with(ilpTable) {
            numOfVariables == 2
            ilpTable.numOfInitialVariables == 2
            ilpTable.numOfEquations == 3
            ilpTable.function == [-1.0D, -3.0D]
            ilpTable.mainTable == [
                    [1.0D, 4.0D],
                    [2.0D, 3.0D],
                    [1.0D, 0.0D]
            ]
            ilpTable.equationsSigns == [Sign.LESS_THAN_OR_EQUAL_TO, Sign.LESS_THAN_OR_EQUAL_TO, Sign.LESS_THAN_OR_EQUAL_TO]
            ilpTable.restrictionsVector == [14.0D, 12.0D, 1.0D]
        }
    }
}
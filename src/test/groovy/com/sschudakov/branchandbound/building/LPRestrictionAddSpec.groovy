package com.sschudakov.branchandbound.building

import com.sschudakov.simplexmethod.enumerable.Sign
import com.sschudakov.simplexmethod.input.LPStringInput
import com.sschudakov.simplexmethod.solver.LPSolver
import com.sschudakov.simplexmethod.table.LPTable
import com.sschudakov.simplexmethod.building.LPTableBuilder
import spock.lang.Shared
import spock.lang.Specification

class LPRestrictionAddSpec extends Specification {
    @Shared
    String task =
            "2 2" +
                    " -1 -3" +
                    " min" +
                    " 1 4" +
                    " 2 3" +
                    " <= <=" +
                    " 14 12"
    @Shared
    private LPRestrictionAdd lpRestrictionAdd

    private LPTable lpTable

    def setupSpec() {
        this.lpRestrictionAdd = new LPRestrictionAdd()
    }

    def setup() {

        lpTable = new LPTable()
        LPStringInput lpStringInput = new LPStringInput()
        LPTableBuilder builder = new LPTableBuilder()
        LPSolver lpSolver = new LPSolver()

        lpStringInput.inputLP(lpTable, task)
        builder.buildSimplexTable(lpTable)
        lpSolver.solveLP(lpTable)
    }

    def "test addRestriction"() {
        given:
        List<Double> condition = [1.0D,0.0D]
        Sign sign = Sign.LESS_THAN_OR_EQUAL_TO
        Double restrictionRightPart = 1.0

        LPRestriction lpRestriction = new LPRestriction(condition, sign, restrictionRightPart)
        when:
        this.lpRestrictionAdd.addRestriction(lpTable, lpRestriction)

        then:
        notThrown(Exception)
        lpTable.outputTable()
    }
}

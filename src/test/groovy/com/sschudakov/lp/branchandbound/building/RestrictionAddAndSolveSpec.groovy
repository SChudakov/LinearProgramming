package com.sschudakov.lp.branchandbound.building

import com.sschudakov.lp.simplexmethod.enumerable.Sign
import com.sschudakov.lp.simplexmethod.input.LPStringInput
import com.sschudakov.lp.simplexmethod.table.LPSolution
import com.sschudakov.lp.simplexmethod.solving.LPSolver
import com.sschudakov.lp.simplexmethod.table.LPRestriction
import com.sschudakov.lp.simplexmethod.table.LPTable
import com.sschudakov.lp.simplexmethod.building.LPTableBuilder
import spock.lang.Shared
import spock.lang.Specification


class RestrictionAddAndSolveSpec extends Specification {
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
    @Shared
    private LPSolver lpSolver

    private LPTable lpTable
    private LPStringInput lpStringInput
    private LPTableBuilder lpTableBuilder

    private LPRestriction lpRestriction

    def setupSpec() {
        this.lpRestrictionAdd = new LPRestrictionAdd()
    }

    def setup() {

        this.lpTable = new LPTable()
        this.lpStringInput = new LPStringInput()
        this.lpTableBuilder = new LPTableBuilder()
        this.lpSolver = new LPSolver()



        List<Double> condition = [1.0D, 0.0D]
        Sign sign = Sign.LESS_THAN_OR_EQUAL_TO
        Double restrictionRightPart = 1.0

        this.lpRestriction = new LPRestriction(condition, sign, restrictionRightPart)
    }

    def "condition add and solve test"() {
        given:
        this.lpStringInput.inputLP(lpTable, task)
        /*this.lpTableBuilder.buildSimplexTable(lpTable)
        LPSolution firstSolution = this.lpSolver.solveLP(lpTable)
        println "\n"
        println "\n"
        println "solution vector ${firstSolution.getSolutionVector()}"
        println "function value ${firstSolution.getFunctionValue()}"
        println "\n"
        println "\n"*/


        this.lpRestrictionAdd.addRestriction(this.lpTable, this.lpRestriction)

        when:
        this.lpTableBuilder.buildSimplexTable(lpTable)
        LPSolution secondSolution = this.lpSolver.solveLP(lpTable)

        then:
        !secondSolution.endedWithException()
        println "\n"
        println "\n"
        println "exception: ${secondSolution.solvingException}"
        println "solution vector ${secondSolution.solutionVector}"
        println "function value ${secondSolution.functionValue}"
        println "\n"
        println "\n"
    }

}
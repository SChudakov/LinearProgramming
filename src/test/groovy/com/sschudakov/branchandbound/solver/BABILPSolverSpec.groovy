package com.sschudakov.branchandbound.solver

import com.sschudakov.branchandbound.input.ILPStringInput
import com.sschudakov.branchandbound.table.ILPTable
import com.sschudakov.simplexmethod.solver.LPSolution
import spock.lang.Shared
import spock.lang.Specification

class BABILPSolverSpec extends Specification {
    @Shared
    private String task =
            "2 2" +
                    " -1 -3" +
                    " min" +
                    " 1 4" +
                    " 2 3" +
                    " <= <=" +
                    " 14 12"
    @Shared
    private String integerVariales = "0 1"

    @Shared
    private BABILPSolver babilpSolver

    @Shared
    private ILPTable ilpTable

    def setup() {
        this.babilpSolver = new BABILPSolver()
        this.ilpTable = new ILPTable()
        ILPStringInput ilpStringInput = new ILPStringInput()
        ilpStringInput.inputILP(this.ilpTable, task, this.integerVariales)
    }

    def "test solve"() {
        when:
        LPSolution solution = this.babilpSolver.solve(this.ilpTable)

        then:
        notThrown(Exception)
        println "functionValue vector: ${solution.solutionVector}"
        println "functionValue: ${solution.functionValue}"
    }
}

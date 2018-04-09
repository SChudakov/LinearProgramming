package com.sschudakov.simplexmethod.solver

import com.sschudakov.simplexmethod.exception.NoSolutionException
import com.sschudakov.simplexmethod.input.LPStringInput
import com.sschudakov.simplexmethod.table.LPTable
import com.sschudakov.simplexmethod.table_building.LPTableBuilder
import spock.lang.Shared
import spock.lang.Specification

class LPSolverSpec extends Specification {

    String task_1 = //18.0
            "3 3 " +
                    " 15 7 12" +
                    " min" +
                    " -1 -1 -2" +
                    " -3 -1 -1" +
                    " -5 -1 -4" +
                    " <= <= <=" +
                    " -2 -3 -1"

    String task_2 = // no solutions
            "5 3" +
                    " 1 -1 1 -3 2" +
                    " min" +
                    " 1 2 -1 2 1" +
                    " -1 -1 1 -3 -2" +
                    " 2 -1 3 -1 2" +
                    " <= = >=" +
                    " 8 10 4"
    String task_3 =
            "2 5" +
                    " -1 -3" +
                    " min" +
                    " 1 4" +
                    " 2 3" +
                    " 1 0" +
                    " 0 1" +
                    " 1 0"+
                    " <= <= >= >= <=" +
                    " 14 12 0 0 1"


    @Shared
    LPSolver lpSolver

    def setup() {
        this.lpSolver = new LPSolver()
    }

    def "solver test 1"() {
        given:
        LPTable table = new LPTable()
        LPStringInput lpStringInput = new LPStringInput()
        lpStringInput.inputLP(table, task_1)
        LPTableBuilder builder = new LPTableBuilder()
        builder.buildSimplexTable(table)

        when:
        def solution = this.lpSolver.solveLP(table)

        then:
        solution.getSolutionVector() == [0.5, 1.5, 0.0, 0.0, 0.0, 3.0]
        solution.getFunctionValue() == 18
    }

    def "solver test 2"() {
        given:
        LPTable table = new LPTable()
        LPStringInput lpStringInput = new LPStringInput()
        lpStringInput.inputLP(table, task_2)
        LPTableBuilder builder = new LPTableBuilder()
        builder.buildSimplexTable(table)

        when:
        this.lpSolver.solveLP(table)

        then:
        thrown(NoSolutionException)
    }

    def "solver test 3"() {
        given:
        LPTable table = new LPTable()
        LPStringInput lpStringInput = new LPStringInput()
        lpStringInput.inputLP(table, task_3)
        LPTableBuilder builder = new LPTableBuilder()
        builder.buildSimplexTable(table)

        when:
        def solution = this.lpSolver.solveLP(table)

        then:
        notThrown(Exception)
        println "functionValue vector: " + solution.getSolutionVector()
        println "functionValue: " + solution.getFunctionValue()
    }
}

package com.sschudakov.simplex_method.solver

import com.sschudakov.simplex_method.exception.NoSolutionException
import com.sschudakov.simplex_method.input.LPStringInput
import com.sschudakov.simplex_method.table.LPTable
import com.sschudakov.simplex_method.table_building.LPTableBuilder
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
    String task_3 = // no solutions
            "2 4" +
                    " -1 -3" +
                    " max" +
                    " 1 4" +
                    " 2 3" +
                    " 1 0" +
                    " 0 1" +
                    " <= <= >= >= " +
                    " 14 12 0 0"


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
        solution.getSolution() == 18
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
        println "solution vector: " + solution.getSolutionVector()
        println "solution: " + solution.getSolution()
    }
}

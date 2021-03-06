package com.sschudakov.lp.branchandbound.input;

import com.sschudakov.lp.branchandbound.table.ILPTable;
import com.sschudakov.lp.simplexmethod.input.LPStringInput;

import java.util.ArrayList;
import java.util.Scanner;

public class ILPStringInput {

    private LPStringInput lpStringInput;

    public ILPStringInput() {
        this.lpStringInput = new LPStringInput();
    }

    public void inputILP(ILPTable ilpTable, String lpString, String nonNegativeVariables) {
        this.lpStringInput.inputLP(ilpTable, lpString);

        Scanner scanner = new Scanner(nonNegativeVariables);
        ilpTable.setIntegerVariables(inputNonNegativeVariables(scanner));
    }

    private ArrayList<Integer> inputNonNegativeVariables(Scanner scanner) {
        ArrayList<Integer> result = new ArrayList<>();
        while (scanner.hasNext()) {
            result.add(scanner.nextInt());
        }
        return result;
    }
}

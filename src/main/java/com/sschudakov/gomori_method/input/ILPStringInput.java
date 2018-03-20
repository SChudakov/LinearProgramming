package com.sschudakov.gomori_method.input;

import com.sschudakov.simplex_method.input.LPStringInput;
import com.sschudakov.simplex_method.table.LPTable;

import java.util.ArrayList;
import java.util.Scanner;

public class ILPStringInput {

    private LPStringInput lpStringInput;

    public ILPStringInput() {
        this.lpStringInput = new LPStringInput();
    }

    public LPTable inputILP(String lpString, String nonNegativeVariables) {
        LPTable lpTable = this.lpStringInput.inputLP(lpString);
        Scanner scanner = new Scanner(nonNegativeVariables);
        lpTable.setNonNegativeVariables(inputNonNegativeVariables(scanner));
        return lpTable;
    }

    private ArrayList<Integer> inputNonNegativeVariables(Scanner scanner) {
        ArrayList<Integer> result = new ArrayList<>();
        while (scanner.hasNext()) {
            result.add(scanner.nextInt());
        }
        return result;
    }
}

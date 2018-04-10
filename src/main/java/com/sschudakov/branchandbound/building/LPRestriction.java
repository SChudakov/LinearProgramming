package com.sschudakov.branchandbound.building;

import com.sschudakov.simplexmethod.enumerable.Sign;

import java.util.List;

public class LPRestriction {
    private List<Double> condition;
    private Sign sign;
    private Double rightPartValue;

    LPRestriction(List<Double> condition, Sign sign, Double rightPartValue) {
        this.condition = condition;
        this.sign = sign;
        this.rightPartValue = rightPartValue;
    }

    public List<Double> getCondition() {
        return condition;
    }

    public Sign getSign() {
        return sign;
    }

    public Double getRightPartValue() {
        return rightPartValue;
    }
}

package com.sschudakov.lp.simplexmethod.table;

import com.sschudakov.lp.simplexmethod.enumerable.Sign;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class LPRestriction {
    private List<Double> condition;
    private Sign sign;
    private Double rightPartValue;

    public List<Double> getCondition() {
        return condition;
    }

    public void setCondition(List<Double> condition) {
        this.condition = condition;
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public Double getRightPartValue() {
        return rightPartValue;
    }

    public void setRightPartValue(Double rightPartValue) {
        this.rightPartValue = rightPartValue;
    }


    public LPRestriction() {
    }

    public LPRestriction(List<Double> condition, Sign sign, Double rightPartValue) {
        this.condition = condition;
        this.sign = sign;
        this.rightPartValue = rightPartValue;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.condition)
                .append(this.sign)
                .append(this.rightPartValue).build();
    }
}

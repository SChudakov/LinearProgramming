package com.sschudakov.lp.simplexmethod.table;

import com.sschudakov.lp.simplexmethod.enumerable.Sign;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class LPRestriction {
    private List<Double> condition;
    private Sign sign;
    private Double rightPartValue;

   public LPRestriction(List<Double> condition, Sign sign, Double rightPartValue) {
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.condition)
                .append(this.sign)
                .append(this.rightPartValue).build();
    }
}

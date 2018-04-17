package com.sschudakov.lp.transporttask.table;

/**
 * Created by Semen Chudakov on 14.11.2017.
 */
public class TTTableMesh {

    private Integer price;
    private Integer restriction;
    private Integer value;
    private Integer delta;

    //getters and setters
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRestriction() {
        return restriction;
    }

    public void setRestriction(Integer restriction) {
        this.restriction = restriction;
    }

    public Integer getDelta() {
        return delta;
    }

    public void setDelta(Integer delta) {
        this.delta = delta;
    }

    public TTTableMesh() {
    }

    public TTTableMesh(Integer price, Integer restriction) {
        this(price, restriction, null, null);
    }

    public TTTableMesh(Integer price, Integer restriction, Integer value) {
        this(price, restriction, value, null);
    }

    public TTTableMesh(Integer price, Integer restriction, Integer value, Integer delta) {
        this.price = price;
        this.restriction = restriction;
        this.value = value;
        this.delta = delta;
    }

    @Override
    public String toString() {
        return "{" + this.price + ";" + this.restriction + ";" +
                this.value + ";" + this.delta + "}";
    }
}

package ru.robq.tpo1.task3;

public enum AggregationState {
    SOLID,
    LIQUID,
    GAS;

    public String getDescr() {
        return this.toString().toLowerCase();
    }
}

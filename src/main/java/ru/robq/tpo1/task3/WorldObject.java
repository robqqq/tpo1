package ru.robq.tpo1.task3;

public class WorldObject {
    private String name;
    private int length;
    private int width;
    private AggregationState aggregationState;


    public WorldObject(String name, int length, int width, AggregationState aggregationState) {
        this.name = name;
        this.length = length;
        this.width = width;
        this.aggregationState = aggregationState;
    }

    public String getName() {
        return name;
    }

    public AggregationState getAggregationState() {
        return aggregationState;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAggregationState(AggregationState aggregationState) {
        this.aggregationState = aggregationState;
    }
}

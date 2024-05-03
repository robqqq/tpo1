package ru.robq.tpo1.task3;

public class IronWorldObject extends WorldObject{

    public IronWorldObject(String name, int length, int width, AggregationState aggregationState) {
        super(name, length, width, aggregationState);
    }

    public String melt(String new_name) {
        if (getAggregationState().equals(AggregationState.SOLID)) {
            String old_name = getName();
            setName(new_name);
            setAggregationState(AggregationState.LIQUID);
            return old_name + " melt and transform to " + new_name + ".";
        } else {
            return getName() + " can't melt because it's not solid.";
        }
        
    }

}

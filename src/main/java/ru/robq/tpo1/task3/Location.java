package ru.robq.tpo1.task3;

public enum Location {
    LEFT_SIDE("left side"),
    RIGHT_SIDE("right side"),
    FRONT_SIDE("front side"),
    BACK_SIDE("back side"),
    LEFT_FRONT_CORNER("left front corner"),
    RIGHT_FRONT_CORNER("right front corner"),
    LEFT_BACK_CORNER("left back corner"),
    RIGHT_BACK_CORNER("right back corner"),
    CENTER("center");

    private String descr;

    Location(String descr) {
        this.descr = descr;
    }

    public String getDescr() {
        return descr;
    }
}

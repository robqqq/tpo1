package ru.robq.tpo1.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Room {
    private Map<Location, Set<Person>> persons;
    private Map<Location, Set<WorldObject>> objects;
    private float noiseLevel;
    private float temperature;
    private Disaster currentDisaster;
    private final float MIN_TEMP = -273;
    private final float MIN_NOISE_LEVEL = 0;
    private final float MAX_NOISE_LEVEL = 10;

    public Room() {
        new Room(0, 0);
    }

    public Room(float noiseLevel, float temperature) {
        persons = new HashMap<>();
        objects = new HashMap<>();
        this.noiseLevel = noiseLevel;
        this.temperature = temperature;
        currentDisaster = Disaster.QUIETLY;
    }

    public String increaseTemperature(float delta) {
        if (delta < 0) {
            return "Delta must be positive";
        }
        temperature += delta;
        return "Temperature increase by " + delta + " deegres, now temperature is " + temperature;
    }

    public String decreaseTemperature(float delta) {
        if (delta < 0) {
            return "Delta must be positive";
        }
        if (temperature - delta < MIN_TEMP) {
            delta = temperature - MIN_TEMP;
            temperature = MIN_TEMP;
        } else {
            temperature -= delta;
        }
        return "Temperature decrease by " + delta + " deegres, now temperature is " + temperature;
    }
}

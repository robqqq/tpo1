package ru.robq.tpo1.task3;

import java.util.HashMap;
import java.util.HashSet;
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

    public float getNoiseLevel() {
        return noiseLevel;
    }

    public float getTemperature() {
        return temperature;
    }

    public Disaster getCurrentDisaster() {
        return currentDisaster;
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

    public String increaseNoise(float delta) {
        if (delta < 0) {
            return "Delta must be positive";
        }
        if (noiseLevel + delta > MAX_NOISE_LEVEL) {
            delta = MAX_NOISE_LEVEL - noiseLevel;
            noiseLevel = MAX_NOISE_LEVEL;
        } else {
            noiseLevel += delta;
        }
        return "Noise level increase by " + delta + ", now noise level is " + noiseLevel;
    }

    public String decreaseNoise(float delta) {
        if (delta < 0) {
            return "Delta must be positive";
        }
        if (noiseLevel - delta < MIN_NOISE_LEVEL) {
            delta = noiseLevel - MIN_NOISE_LEVEL;
            noiseLevel = MIN_NOISE_LEVEL;
        } else {
            noiseLevel -= delta;
        }
        return "Noise level decrease by " + delta + ", now noise level is " + noiseLevel;
    }

    public String setCurrentDisaster(Disaster disaster) {
        currentDisaster = disaster;
        return disaster.getDescr() + " started.";
    }

    public String clearDisaster() {
        currentDisaster = Disaster.QUIETLY;
        return "Disaster ended. Now is quietly.";
    }

    public String putPerson(Location location, Person person) {
        if (persons.containsKey(location)) {
            persons.get(location).add(person);
        } else {
            Set<Person> personsSet = new HashSet<>();
            personsSet.add(person);
            persons.put(location, personsSet);
        }
        return person.getName() + " located at " + location.getDescr() + " now.";
    }

    public String putWorldObject(Location location, WorldObject worldObject) {
        if (objects.containsKey(location)) {
            objects.get(location).add(worldObject);
        } else {
            Set<WorldObject> objectsSet = new HashSet<>();
            objectsSet.add(worldObject);
            objects.put(location, objectsSet);
        }
        return worldObject.getName() + " located at " + location.getDescr() + " now.";
    }
}

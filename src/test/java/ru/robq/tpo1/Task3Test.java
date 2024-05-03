package ru.robq.tpo1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.robq.tpo1.task3.AggregationState;
import ru.robq.tpo1.task3.Disaster;
import ru.robq.tpo1.task3.IronWorldObject;
import ru.robq.tpo1.task3.Location;
import ru.robq.tpo1.task3.Person;
import ru.robq.tpo1.task3.Room;

public class Task3Test {
    private Room room;
    private List<Person> they;
    private IronWorldObject compBank;
    private IronWorldObject gasObject ;
    private final float MIN_TEMP = -273;
    private final float MIN_NOISE_LEVEL = 0;
    private final float MAX_NOISE_LEVEL = 10;
    
    @BeforeEach
    void init() {
        they = new ArrayList<>();
        they.add(new Person("First someone", 50, 150));
        they.add(new Person("Second someone", 70, 170));
        compBank = new IronWorldObject("Computer bank", 100, 100, AggregationState.SOLID);
        gasObject = new IronWorldObject("Gas object", 0, 0, AggregationState.GAS);
        room = new Room(5, 5);
    }

    @Test
    void checkClearDisaster() {
        assertEquals("Disaster ended. Now is quietly.", room.clearDisaster());
    }

    @Test
    void checkSetCurrentDisaster() {
        assertEquals("bombardment started.", room.setCurrentDisaster(Disaster.BOMBARDMENT));
    }

    @Test
    void checkNegativeIncreaseTemperature() {
        float oldTemperature = room.getTemperature();
        assertEquals("Delta must be positive", room.increaseTemperature(-1));
        assertEquals(oldTemperature, room.getTemperature());
    }

    @Test
    void checkNegativeDecreaseTemperature() {
        float oldTemperature = room.getTemperature();
        assertEquals("Delta must be positive", room.decreaseTemperature(-1));
        assertEquals(oldTemperature, room.getTemperature());
    }

    @Test
    void checkNegativeIncreaseNoise() {
        float oldNoiseLevel = room.getNoiseLevel();
        assertEquals("Delta must be positive", room.increaseNoise(-1));
        assertEquals(oldNoiseLevel, room.getNoiseLevel());
    }

    @Test
    void checkNegativeDecreaseNoise() {
        float oldNoiseLevel = room.getNoiseLevel();
        assertEquals("Delta must be positive", room.decreaseNoise(-1));
        assertEquals(oldNoiseLevel, room.getNoiseLevel());
    }

    @Test
    void checkIncreaseTemperature() {
        float oldTemperature = room.getTemperature();
        float delta = 1;
        assertEquals("Temperature increase by " + delta + " deegres, now temperature is " + (oldTemperature + delta), room.increaseTemperature(delta));
        assertEquals(oldTemperature + delta, room.getTemperature());
    }

    @Test
    void checkDecreaseTemperature() {
        float oldTemperature = room.getTemperature();
        float delta = 1;
        assertEquals("Temperature decrease by " + delta + " deegres, now temperature is " + (oldTemperature - delta), room.decreaseTemperature(delta));
        assertEquals(oldTemperature - delta, room.getTemperature());
    }

    @Test
    void checkIncreaseNoise() {
        float oldNoiseLevel = room.getNoiseLevel();
        float delta = 1;
        assertEquals("Noise level increase by " + delta + ", now noise level is " + (oldNoiseLevel + delta), room.increaseNoise(delta));
        assertEquals(oldNoiseLevel + delta, room.getNoiseLevel());
    }

    @Test
    void checkDecreaseNoise() {
        float oldNoiseLevel = room.getNoiseLevel();
        float delta = 1;
        assertEquals("Noise level decrease by " + delta + ", now noise level is " + (oldNoiseLevel - delta), room.decreaseNoise(delta));
        assertEquals(oldNoiseLevel - delta, room.getNoiseLevel());
    }

    @Test
    void checkDecreaseTemperatureLimit() {
        float oldTemperature = room.getTemperature();
        float delta = 300;
        assertEquals("Temperature decrease by " + (oldTemperature - MIN_TEMP) + " deegres, now temperature is " + MIN_TEMP, room.decreaseTemperature(delta));
        assertEquals(MIN_TEMP, room.getTemperature());
    }

    @Test
    void checkIncreaseNoiseLimit() {
        float oldNoiseLevel = room.getNoiseLevel();
        float delta = 10;
        assertEquals("Noise level increase by " + (MAX_NOISE_LEVEL - oldNoiseLevel) + ", now noise level is " + MAX_NOISE_LEVEL, room.increaseNoise(delta));
        assertEquals(MAX_NOISE_LEVEL, room.getNoiseLevel());
    }

    @Test
    void checkDecreaseNoiseLimit() {
        float oldNoiseLevel = room.getNoiseLevel();
        float delta = 10;
        assertEquals("Noise level decrease by " + (oldNoiseLevel - MIN_NOISE_LEVEL) + ", now noise level is " + MIN_NOISE_LEVEL, room.decreaseNoise(delta));
        assertEquals(MIN_NOISE_LEVEL, room.getNoiseLevel());
    }

    @Test
    void checkPutPerson() {
        assertEquals("First someone located at right back corner now.", room.putPerson(Location.RIGHT_BACK_CORNER, they.get(0)));
        assertEquals("Second someone located at right back corner now.", room.putPerson(Location.RIGHT_BACK_CORNER, they.get(1)));
    }

    @Test
    void checkPutWorldObject() {
        assertEquals("Computer bank located at center now.", room.putWorldObject(Location.CENTER, compBank));
    }

    @Test
    void checkMelt() {
        assertEquals("Computer bank melt and transform to Melted iron.", compBank.melt("Melted iron"));
        assertEquals(AggregationState.LIQUID, compBank.getAggregationState());
        assertEquals("Melted iron", compBank.getName());
    }

    @Test
    void checkMeltNotSolid() {
        assertEquals("Gas object can't melt because it's not solid.", gasObject.melt("Melted gas object"));
        assertEquals(AggregationState.GAS, gasObject.getAggregationState());
        assertEquals("Gas object", gasObject.getName());
    }
}

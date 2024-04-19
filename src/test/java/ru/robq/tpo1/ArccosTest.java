package ru.robq.tpo1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArccosTest {
    
    @ParameterizedTest(name = "arccos({0})")
    @DisplayName("Check uncorrect values")
    @ValueSource(doubles = {-2, -1.01, 1.01, 2})
    void checkUncorrectValues(double param) {
        assertAll(
            () -> assertEquals(Math.acos(param), Arccos.arccos(param, 100), 0.001)
        );
    }

    @ParameterizedTest(name = "arccos({0})")
    @DisplayName("Check сorrect values")
    @ValueSource(doubles = {-0.9, -0.5, 0, 0.5, 0.9})
    void checkСorrectValues(double param) {
        assertAll(
            () -> assertEquals(Math.acos(param), Arccos.arccos(param, 100), 0.001)
        );
    }

    @ParameterizedTest(name = "arccos({0})")
    @DisplayName("Check сorrect values")
    @ValueSource(doubles = {-1, 1})
    void checkBoundaryValues(double param) {
        assertAll(
            () -> assertEquals(Math.acos(param), Arccos.arccos(param, 100), 0.1)
        );
    }
}

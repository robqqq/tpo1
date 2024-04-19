package ru.robq.tpo1;

import java.util.Arrays;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class IntArrayConverter implements ArgumentConverter {

    @Override
    public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
        if (!(source instanceof String)) {
            throw new IllegalArgumentException("The argument should be a string: " + source);
        }
        try {
            if (((String) source).length() == 0) {
                return new int[0];
            }
            return Arrays.stream(((String) source).split(",")).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Failed to convert", e);
        }
    }
}

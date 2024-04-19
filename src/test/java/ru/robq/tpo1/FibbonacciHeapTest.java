package ru.robq.tpo1;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;


public class FibbonacciHeapTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/check_insert_source.csv", delimiter = ';')
    void checkInsert(@ConvertWith(IntArrayConverter.class) int[] input, 
                        @ConvertWith(IntArrayConverter.class) int[] expected,
                        int insertValue) {
        FibonacciHeap heap = new FibonacciHeap(input);
        heap.insert(insertValue);
        input = heap.nodeArray();
        Arrays.sort(expected);
        Arrays.sort(input);
        assertArrayEquals(input, expected);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/check_remove_min_source.csv", delimiter = ';')
    void checkRemoveMin(@ConvertWith(IntArrayConverter.class) int[] input, 
                        @ConvertWith(IntArrayConverter.class) int[] expected) {
        FibonacciHeap heap = new FibonacciHeap(input);
        heap.removeMin();
        input = heap.nodeArray();
        Arrays.sort(expected);
        Arrays.sort(input);
        assertArrayEquals(input, expected);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/check_clear_source.csv", delimiter = ';')
    void checkClear(@ConvertWith(IntArrayConverter.class) int[] input) {
        FibonacciHeap heap = new FibonacciHeap(input);
        heap.clear();
        assertArrayEquals(heap.nodeArray(), new int[0]);
    }
}

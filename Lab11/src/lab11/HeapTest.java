package lab11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeapTest {

    private Heap<Integer> integerHeap;

    @BeforeEach
    public void setup() {
        integerHeap = new Heap<>();
    }

    @Test
    void peek() {
        integerHeap.addAll(Arrays.asList(30, 10, 20));
        assertEquals(Integer.valueOf(10), integerHeap.peek());
    }

    @Test
    void poll() {
        var values = Arrays.asList(2, 53, 213, 5, 1, 5, 4, 210, 14, 26, 44, 35, 31, 33, 19, 52, 27);
        integerHeap.addAll(values);

        Collections.sort(values);
        for (int x : values) {
            assertEquals(Integer.valueOf(x), integerHeap.poll());
        }
    }

    @Test
    void size() {
        var values = Arrays.asList(10, 14, 26, 44, 35, 31, 33, 19, 52, 27);
        integerHeap.addAll(values);
        assertEquals(values.size(), integerHeap.size());
    }
}
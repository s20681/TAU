import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BubbleSortTest {
    private BubbleSort bubbleSort;
    boolean exceptionThrown;

    @Before
    public void setUp() {
        bubbleSort = new BubbleSort();
        exceptionThrown = false;
    }

    @After
    public void tearDown() {
        bubbleSort = null;
    }

    @Test
    public void shouldReturnSameValues() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertEquals(bubbleSort.sort(list), Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

    @Test
    public void shouldSortSimple() {
        List<Integer> list = Arrays.asList(2, 1);
        assertEquals(bubbleSort.sort(list), Arrays.asList(1, 2));
    }

    @Test
    public void shouldSortSimpleNegative() {
        List<Integer> list = Arrays.asList(-1, -3, -2);
        assertEquals(bubbleSort.sort(list), Arrays.asList(-3, -2, -1));
    }

    @Test
    public void shouldSortMixed() {
        List<Integer> list = Arrays.asList(3, -1, 2, 1, -9);
        assertEquals(bubbleSort.sort(list), Arrays.asList(-9, -1, 1, 2, 3));
    }

    @Test
    public void shouldSortMixedWithZero() {
        List<Integer> list = Arrays.asList(0, -2, 1235, -897123, -1293);
        assertEquals(bubbleSort.sort(list), Arrays.asList(-897123, -1293, -2, 0, 1235));
    }

    @Test
    public void shouldSortMaxMinValues() {
        List<Integer> list = Arrays.asList(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        assertEquals(bubbleSort.sort(list), Arrays.asList(Integer.MIN_VALUE, 0, Integer.MAX_VALUE));
    }

    @Test
    public void shouldSortSimilarValues() {
        List<Integer> list = Arrays.asList(-5, -90009, -90008, -4, -90007, -90010, -3);
        assertEquals(bubbleSort.sort(list), Arrays.asList(-90010, -90009, -90008, -90007, -5, -4, -3));
    }

    @Test
    public void shouldSortDuplicateValues() {
        List<Integer> list = Arrays.asList(0, 5, 0, 5, 0, 5, 0);
        assertEquals(bubbleSort.sort(list), Arrays.asList(0, 0, 0, 0, 5, 5, 5));
    }

    @Test
    public void shouldSortLargeCollection() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }

        List<Integer> listCopy = new ArrayList<>(list);
        Collections.shuffle(list);

        assertNotEquals(list, listCopy);
        bubbleSort.sort(list);
        assertEquals(list, listCopy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfEmpty() {
        List<Integer> list = Collections.emptyList();
        assertThrows(IllegalArgumentException.class, (Executable) bubbleSort.sort(list));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowIfNullElement() {
        List<Integer> list = Arrays.asList(0, null, 1, 2);
        assertThrows(IllegalArgumentException.class, (Executable) bubbleSort.sort(list));
    }

    @Test
    public void shouldNotThrowIfSingleElement() {
        List<Integer> list = Arrays.asList(0);
        try {
            bubbleSort.sort(list);
        } catch (IllegalArgumentException exception) {
            exceptionThrown = true;
        }

        assertEquals(exceptionThrown, false);
    }

    @Test
    public void shouldNotThrowIfAllElementsSame() {
        List<Integer> list = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        try {
            bubbleSort.sort(list);
        } catch (IllegalArgumentException exception) {
            exceptionThrown = true;
        }

        assertEquals(exceptionThrown, false);
    }
}

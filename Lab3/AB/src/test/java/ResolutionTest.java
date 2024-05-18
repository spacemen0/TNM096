import main.java.Clause;
import main.java.Resolution;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ResolutionTest {

    @Test
    public void testResolution1() {
        Set<String> p1 = new HashSet<>(Arrays.asList("a", "b"));
        Set<String> n1 = new HashSet<>(java.util.List.of("c"));
        Clause A = new Clause(p1, n1);

        Set<String> p2 = new HashSet<>(Arrays.asList("c", "b"));
        Clause B = new Clause(p2, new HashSet<>());

        Clause result = Resolution.resolve(A, B);

        Set<String> expectedResultP = new HashSet<>(Arrays.asList("a", "b"));
        Set<String> expectedResultN = new HashSet<>();
        Clause expectedResult = new Clause(expectedResultP, expectedResultN);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testResolution2() {
        Set<String> p1 = new HashSet<>(Arrays.asList("a", "b"));
        Set<String> n1 = new HashSet<>(java.util.List.of("c"));
        Clause A = new Clause(p1, n1);

        Set<String> p2 = new HashSet<>(Arrays.asList("d", "b"));
        Set<String> n2 = new HashSet<>(java.util.List.of("g"));
        Clause B = new Clause(p2, n2);

        Clause result = Resolution.resolve(A, B);

        assertNull(result);
    }

    @Test
    public void testResolution3() {
        Set<String> p1 = new HashSet<>(Arrays.asList("c", "t"));
        Set<String> n1 = new HashSet<>(java.util.List.of("b"));
        Clause A = new Clause(p1, n1);

        Set<String> p2 = new HashSet<>(Arrays.asList("z", "b"));
        Set<String> n2 = new HashSet<>(java.util.List.of("c"));
        Clause B = new Clause(p2, n2);

        Clause result = Resolution.resolve(A, B);

        assertNull(result);
    }
}

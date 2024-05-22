import main.java.Clause;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubsumptionTest {

    @Test
    public void testSubsumption1() {
        Set<String> p1 = new HashSet<>(Arrays.asList("c", "a"));
        Clause clause1 = new Clause(p1, new HashSet<>());

        Set<String> p2 = new HashSet<>(Arrays.asList("a", "b", "c"));
        Clause clause2 = new Clause(p2, new HashSet<>());

        assertTrue(clause1.subsumes(clause2));
    }

    @Test
    public void testSubsumption2() {
        Set<String> p1 = new HashSet<>(List.of("b"));
        Set<String> n1 = new HashSet<>(List.of("c"));
        Clause clause1 = new Clause(p1, n1);

        Set<String> p2 = new HashSet<>(Arrays.asList("a", "b"));
        Set<String> n2 = new HashSet<>(List.of("c"));
        Clause clause2 = new Clause(p2, n2);

        assertTrue(clause1.subsumes(clause2));
    }

    @Test
    public void testSubsumption3() {
        Set<String> p1 = new HashSet<>(List.of("b"));
        Set<String> n1 = new HashSet<>(Arrays.asList("f", "c"));
        Clause clause1 = new Clause(p1, n1);

        Set<String> p2 = new HashSet<>(Arrays.asList("a", "b"));
        Set<String> n2 = new HashSet<>(List.of("c"));
        Clause clause2 = new Clause(p2, n2);

        assertFalse(clause1.subsumes(clause2));
    }

    @Test
    public void testSubsumption4() {
        Set<String> p1 = new HashSet<>(List.of("b"));
        Set<String> n1 = new HashSet<>();
        Clause clause1 = new Clause(p1, n1);

        Set<String> p2 = new HashSet<>(Arrays.asList("a", "b"));
        Set<String> n2 = new HashSet<>(List.of("c"));
        Clause clause2 = new Clause(p2, n2);

        assertTrue(clause1.subsumes(clause2));
    }

    @Test
    public void testSubsumption5() {
        Set<String> p1 = new HashSet<>(Arrays.asList("b", "a"));
        Set<String> n1 = new HashSet<>(List.of("c"));
        Clause clause1 = new Clause(p1, n1);

        Set<String> p2 = new HashSet<>(Arrays.asList("a", "b"));
        Set<String> n2 = new HashSet<>(List.of("c"));
        Clause clause2 = new Clause(p2, n2);

        assertTrue(clause1.subsumes(clause2));
    }
}

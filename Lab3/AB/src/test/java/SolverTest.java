import main.java.Clause;
import main.java.Solver;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolverTest {

    @Test
    public void testSolver1() {
        Set<Clause> KB = new HashSet<>();
        Set<String> p1 = new HashSet<>(Arrays.asList("a", "b"));
        Set<String> n1 = new HashSet<>(List.of("c"));
        Clause clause1 = new Clause(p1, n1);
        KB.add(clause1);

        Set<Clause> result = Solver.solve(KB);

        assertTrue(result.contains(clause1));
    }

    @Test
    public void testSolver2() {
        Set<Clause> KB = new HashSet<>();
        Set<String> p1 = new HashSet<>(List.of("a"));
        Set<String> n1 = new HashSet<>(List.of("b"));
        Clause clause1 = new Clause(p1, n1);
        KB.add(clause1);

        Set<Clause> result = Solver.solve(KB);

        assertTrue(result.contains(clause1));
    }

    @Test
    public void testSolver3() {
        Set<Clause> KB = new HashSet<>();
        Set<String> p1 = new HashSet<>(List.of("a"));
        Set<String> n1 = new HashSet<>(List.of("a"));
        Clause clause1 = new Clause(p1, n1);
        KB.add(clause1);

        Set<Clause> result = Solver.solve(KB);

        assertTrue(result.contains(clause1));
    }
}

import main.java.Clause;
import main.java.Solver;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class SolverTest {

    @Test
    public void testSolver() {
        String ice = "a";
        String sun = "b";
        String money = "c";
        String movie = "d";
        String cry = "e";
        Clause A = new Clause(Set.of(ice), Set.of(sun,money));
        Clause B = new Clause(Set.of(ice,movie), Set.of(money));
        Clause C = new Clause(Set.of(money), Set.of(movie));
        Clause D = new Clause(Set.of(), Set.of(movie,ice));
        Clause E = new Clause(Set.of(sun,money,cry), Set.of());
        Clause F = new Clause(Set.of(movie), Set.of());
        Set<Clause> KB = new HashSet<>();
        KB.add(A);
        KB.add(B);
        KB.add(C);
        KB.add(D);
        KB.add(E);
        KB.add(F);
        Set<Clause> result = Solver.solve(KB);
        for (Clause clause : result) {
            System.out.println(clause);
        }
    }
}

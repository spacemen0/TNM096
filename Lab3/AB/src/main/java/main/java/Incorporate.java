package main.java;
import java.util.Set;

public class Incorporate {

    public static Set<Clause> incorporate(Set<Clause> S, Set<Clause> KB) {
        for (Clause clause : S) {
            incorporateClause(clause, KB);
        }
        return KB;
    }

    public static void incorporateClause(Clause A, Set<Clause> KB) {
        for (Clause B : KB) {
            if (B.subsumes(A)) {
                return;
            }
        }
        KB.removeIf(A::subsumes);
        KB.add(A);
    }
}


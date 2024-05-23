package main.java;

import java.util.HashSet;
import java.util.Set;

public class Incorporate {

    public static Set<Clause> incorporate(Set<Clause> S, Set<Clause> KB) {
        for (Clause clause : S) {
            incorporateClause(clause, KB);
        }
        return KB;
    }

    public static void incorporateClause(Clause A, Set<Clause> KB) {
        Set<Clause> toBeRemoved = new HashSet<>();
        for (Clause B : KB) {
            if (B.subsumes(A)) {
                return;
            }
        }
        for (Clause B : KB) {
            if (A.subsumes(B)) {
                toBeRemoved.add(B);
            }
        }
        KB.removeAll(toBeRemoved);
        KB.add(A);
    }
}


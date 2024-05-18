package main.java;

import java.util.HashSet;
import java.util.Set;

public class Incorporate {

    public static Set<Clause> incorporate(Set<Clause> S, Set<Clause> KB) {
        for (Clause clause : S) {
            KB = incorporateClause(clause, KB);
        }
        return KB;
    }

    public static Set<Clause> incorporateClause(Clause A, Set<Clause> KB) {
        Set<Clause> clausesToRemove = new HashSet<>();

        for (Clause B : KB) {
            if (A.subsumes(B)) {
                clausesToRemove.add(B);
            } else if (B.subsumes(A)) {
                return KB;
            }
        }

        KB.removeAll(clausesToRemove);
        KB.add(A);
        return KB;
    }
}


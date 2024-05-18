package main.java;

import java.util.HashSet;
import java.util.Set;

public class Solver {

    public static Set<Clause> solve(Set<Clause> KB) {
        do {
            Set<Clause> S = new HashSet<>();
            Set<Clause> KBPrim = new HashSet<>(KB);

            for (Clause A : KB) {
                for (Clause B : KB) {
                    if (!A.equals(B)) {
                        Clause C = Resolution.resolve(A, B);
                        if (C != null) {
                            S.add(C);
                        }
                    }
                }
            }

            if (S.isEmpty()) {
                return KB;
            }
            KB = Incorporate.incorporate(S, KB);
            if (KBPrim.equals(KB)) {
                break;
            }

        } while (true);

        return KB;
    }
}



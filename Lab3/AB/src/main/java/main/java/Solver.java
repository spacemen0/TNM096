package main.java;

import java.util.HashSet;
import java.util.Set;

public class Solver {

    public static Set<Clause> solve(Set<Clause> KB) {
        KB = Incorporate.incorporate(KB, new HashSet<>());
        do {
            Set<Clause> s = new HashSet<>();
            Set<Clause> KBPrim = new HashSet<>(KB);

            for (Clause A : KB) {
                for (Clause B : KB) {
                    if (!A.equals(B)) {
                        Clause C = Resolution.resolve(A, B);
                        if (C != null) {
                            s.add(C);
                        }
                    }
                }
            }

            if (s.isEmpty()) {
                return KB;
            }
            Incorporate.incorporate(s, KB);
            if (KBPrim.equals(KB)) {
                break;
            }

        } while (true);

        return KB;
    }
}



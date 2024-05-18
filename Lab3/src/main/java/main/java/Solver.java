package main.java;

import java.util.HashSet;
import java.util.Set;

public class Solver {

    public static Set<Clause> solve(Set<Clause> KB) {
        KB = Incorporate.incorporate(KB, new HashSet<>());
        do {
            Set<Clause> KBPrime = new HashSet<>(KB);
            Set<Clause> S = new HashSet<>();

            for (Clause A : KBPrime) {
                for (Clause B : KBPrime) {
                    if (!A.equals(B)) {
                        Clause C = Resolution.resolve(A, B);
                        if (C != null) {
                            S.add(C);
                        }
                    }
                }
            }
            if (S.isEmpty()) {
                return KBPrime;
            }
            KB = Incorporate.incorporate(S, KB);
        } while (true);
    }
}


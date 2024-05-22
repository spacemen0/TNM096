package main.java;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Clause> KB = new HashSet<>();
        Clause A = new Clause(Set.of("a", "b","c"), Set.of());
        Clause B = new Clause(Set.of("a"), Set.of("c"));
        Clause C = new Clause(Set.of("a","c"), Set.of("b"));


        KB.add(A);
        KB.add(B);
        KB.add(C);

        Set<Clause> result = Solver.solve(KB);
        for (Clause clause : result) {
            System.out.println(clause);
        }
    }
}
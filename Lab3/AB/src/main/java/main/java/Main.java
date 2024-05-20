package main.java;

import java.util.HashSet;
import java.util.Set;
public class Main {
    public static void main(String[] args) {
        Set<Clause> KB = new HashSet<>();
        //sun:a money:b ice:c movie:d cry:e
        Clause A = new Clause(Set.of("c"), Set.of("a","b"));
        Clause B = new Clause(Set.of("c","d"), Set.of("b"));
        Clause C = new Clause(Set.of("b"), Set.of("d"));
        Clause D = new Clause(Set.of(), Set.of("d", "c"));
        Clause E = new Clause(Set.of("a","b","e"), Set.of());


        KB.add(A);
        KB.add(B);
        KB.add(C);
        KB.add(D);
        KB.add(E);

        Set<Clause> result = Solver.solve(KB);
        for (Clause clause : result) {
            System.out.println(clause);
        }
    }
}

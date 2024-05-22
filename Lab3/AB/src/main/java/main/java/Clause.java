package main.java;

import java.util.HashSet;
import java.util.Set;

public class Clause {
    Set<String> p;
    Set<String> n;

    public Clause(Set<String> positiveLiterals, Set<String> negativeLiterals) {
        this.p = new HashSet<>(positiveLiterals);
        this.n = new HashSet<>(negativeLiterals);
    }

    public boolean isTautology() {
        for (String literal : p) {
            if (n.contains(literal)) {
                return true;
            }
        }
        return false;
    }

    public boolean subsumes(Clause other) {
        return other.p.containsAll(p) &&
                other.n.containsAll(n);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Clause clause = (Clause) obj;
        return p.equals(clause.p) &&
                n.equals(clause.n);
    }

    @Override
    public int hashCode() {
        return p.hashCode() + n.hashCode();
    }

    @Override
    public String toString() {
        return "Clause { " +
                "positiveLiterals=" + p +
                ", negativeLiterals=" + n +
                " }";
    }
}

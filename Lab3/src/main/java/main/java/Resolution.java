package main.java;

import java.util.HashSet;
import java.util.Set;

public class Resolution {

    public static Clause resolve(Clause A, Clause B) {
        Clause localA = new Clause(A.p, A.n);
        Clause localB = new Clause(B.p, B.n);
        Set<String> pn = new HashSet<>(localA.p);
        pn.retainAll(localB.n);

        Set<String> np = new HashSet<>(localA.n);
        np.retainAll(localB.p);

        if (pn.isEmpty() && np.isEmpty()) {
            return null;
        }
        if (!pn.isEmpty()) {
            String literal = pn.iterator().next();
            localA.p.remove(literal);
            localB.n.remove(literal);
        } else {
            String literal = np.iterator().next();
            localA.n.remove(literal);
            localB.p.remove(literal);
        }
        Set<String> Cp = new HashSet<>(localA.p);
        Cp.addAll(localB.p);
        Set<String> Cn = new HashSet<>(localA.n);
        Cn.addAll(localB.n);
        Clause resolvent = new Clause(Cp, Cn);
        if (resolvent.isTautology()) {
            return null;
        }
        return resolvent;
    }
}

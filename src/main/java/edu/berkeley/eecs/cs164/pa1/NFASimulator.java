package edu.berkeley.eecs.cs164.pa1;

import java.util.*;

/**
 * This class simulates a non-deterministic finite automaton over ASCII strings.
 */
public class NFASimulator {
    private final Automaton nfa;

    /**
     * Create a new simulator from a given NFA structure
     *
     * @param nfa the nfa to simulate
     */
    public NFASimulator(Automaton nfa) {
        this.nfa = nfa;
    }

    /**
     * Determines whether or not the given text is accepted by the NFA
     *
     * @param text the text to try matching
     * @return true if the text is accepted by the NFA, else false
     */
    public boolean matches(String text) {
        Set<AutomatonState> in = new HashSet<AutomatonState>();
        in.add(nfa.getStart());
        Set<AutomatonState> d = closure(in);

        for (char ch : text.toCharArray()) {
            d = dfaEdge(d, ch);
        }

        return d.contains(nfa.getOut());
    }

    /**
     * Find all states reachable from the input by epsilon moves. Basically a DFS.
     * @param states the set of states to start from
     * @return set of all states reachable from state by epsilon moves
     */
    private Set<AutomatonState> closure(Set<AutomatonState> states) {
        Deque<AutomatonState> t_prime = new ArrayDeque<AutomatonState>();
        Set<AutomatonState> t = new HashSet<AutomatonState>();

        for (AutomatonState s : states) {
            t_prime.addFirst(s);
        }

        while (!t_prime.isEmpty()) {
            AutomatonState s = t_prime.removeFirst();
            t.add(s);

            for (AutomatonState ep : s.getEpsilonTransitions()) {
                t_prime.addFirst(ep);
            }
        }

        return t;
    }

    /**
     * Find all states NFA could be in after taking all possible epsilon moves and a character move.
     * @param d current states
     * @param c character to transition with
     * @return all states possible by taking transition with label c and epsilons
     */
    private Set<AutomatonState> dfaEdge(Set<AutomatonState> d, char c) {
        Set<AutomatonState> input = new HashSet<AutomatonState>();
        for (AutomatonState s : d) {
            input.addAll(s.getTransitions(c));
        }
        return closure(input);
    }

}

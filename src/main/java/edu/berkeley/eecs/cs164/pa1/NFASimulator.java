package edu.berkeley.eecs.cs164.pa1;

import java.util.*;

/**
 * This class simulates a non-deterministic finite automaton over ASCII strings.
 */
public class NFASimulator {
    private final Automaton nfa;

    class State {

        public AutomatonState state;
        public int depth;
        public int index;

        public State(AutomatonState state, int depth, int index) {
            this.state = state;
            this.depth = depth;
            this.index = index;
        }
    }

    class StateComparator implements Comparator<State> {

        @Override
        public int compare(State a, State b) {
            if (a.index == b.index) {
                return a.depth - b.depth;
            } else {
                return a.index - b.index;
            }
        }
    }

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
     * Find all states reachable from the input by epsilon moves.
     * @param states the set of states to start from
     * @return set of all states reachable from state by epsilon moves
     */
    private Set<AutomatonState> closure(Set<AutomatonState> states) {
        Set<AutomatonState> t = new HashSet<AutomatonState>();
        t.addAll(states);
        for (AutomatonState s : t) {
            t.addAll(closure(s.getEpsilonTransitions()));
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

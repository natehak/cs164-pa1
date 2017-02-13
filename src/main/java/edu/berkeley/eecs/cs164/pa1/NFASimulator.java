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
        PriorityQueue<State> fringe = new PriorityQueue<State>(100, new StateComparator());
        fringe.add(new State(nfa.getStart(), 0, 0));

        while (!fringe.isEmpty()) {
            State state = fringe.poll();

            if (state.state.equals(nfa.getOut()) && state.index == text.length()) {
                return true;
            }

            Set<AutomatonState> epsilonStates = state.state.getEpsilonTransitions();
            for (AutomatonState s : epsilonStates) {
                fringe.add(new State(s, state.depth + 1, state.index));
            }

            if (state.index < text.length()) {
                Set<AutomatonState> states = state.state.getTransitions(text.charAt(state.index));
                for (AutomatonState s : states) {
                    fringe.add(new State(s, state.depth + 1, state.index + 1));
                }
            }
        }
        return false;
    }

}

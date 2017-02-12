package edu.berkeley.eecs.cs164.pa1;

/**
 * This class parses a simple regular expression syntax into an NFA
 */
public class RegexParser {

    /**
     * This is the main function of this object. It kicks off
     * whatever "compilation" process you write for converting
     * regex strings to NFAs.
     *
     * @param pattern the pattern to compile
     * @return an NFA accepting the pattern
     * @throws RegexParseException upon encountering a parse error
     */
    public static Automaton parse(String pattern) {
        // Why no tokenize step? Because each character is itself a token! (Disregarding escapes)
        return null;
    }

    /**
     * Creates an NFA that reflects the | operator in regex.
     * @param a Some automaton
     * @param b Some other automaton
     * @return An automaton that recognizes a OR b
     */
    private Automaton alt(Automaton a, Automaton b) {
        AutomatonState altIn = new AutomatonState();
        altIn.addEpsilonTransition(a.getStart());
        altIn.addEpsilonTransition(b.getStart());

        AutomatonState altOut = new AutomatonState();
        a.getOut().addEpsilonTransition(altOut);
        b.getOut().addEpsilonTransition(altOut);

        return new Automaton(altIn, altOut);
    }

    /**
     * Creates an NFA that recognizes auto a and THEN auto b.
     * @param a Some automaton
     * @param b Some other automaton
     * @return An automaton that recognizes a THEN b
     */
    private Automaton concat(Automaton a, Automaton b) {
        a.getOut().addEpsilonTransition(b.getStart());
        return new Automaton(a.getStart(), b.getOut());
    }

    /**
     * Creates an NFA that recognizes some character.
     * @param ch The character to recognize
     * @return An automaton that recognizes ch
     */
    private Automaton recognize(char ch) {
        AutomatonState in = new AutomatonState();
        AutomatonState out = new AutomatonState();
        in.addTransition(ch, out);
        return new Automaton(in, out);
    }

    /**
     * Creates an NFA that reflects the kleene star operator in regex.
     * @param a The NFA to recognize.
     * @return An automaton that recognizes a at least once.
     */
    private Automaton star(Automaton a) {
        AutomatonState in = new AutomatonState();
        AutomatonState out = new AutomatonState();

        in.addEpsilonTransition(a.getStart());
        in.addEpsilonTransition(out);
        a.getOut().addEpsilonTransition(in);

        return new Automaton(in, out);
    }

    /**
     * Creates an NFA that reflects the plus operator in regex.
     * @param a The NFA to transform.
     * @return An automaton that recognizes a at least once
     */
    private Automaton plus(Automaton a) {
        AutomatonState in = new AutomatonState();
        AutomatonState out = new AutomatonState();

        in.addEpsilonTransition(a.getStart());
        a.getOut().addEpsilonTransition(in);
        a.getOut().addEpsilonTransition(out);

        return new Automaton(in, out);
    }

    /**
     * Creates an NFA that reflects the ? operator in regex.
     * @param a The NFA to transform.
     * @return An automaton that recgonizes a zero or one times.
     */
    private Automaton option(Automaton a) {
        AutomatonState in = new AutomatonState();
        AutomatonState out = new AutomatonState();

        in.addEpsilonTransition(a.getStart());
        in.addEpsilonTransition(out);
        a.getOut().addEpsilonTransition(out);

        return new Automaton(in, out);
    }

}

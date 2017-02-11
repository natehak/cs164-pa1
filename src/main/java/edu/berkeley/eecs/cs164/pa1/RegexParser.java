package edu.berkeley.eecs.cs164.pa1;

import java.util.LinkedList;

/**
 * Simple tree structure from parser example.
 */
class Node {
    LinkedList children;
    String type;

    public Node(String type) {
        this.type = type;
        children = new LinkedList<>();
    }

    public void addChild(Object child) {
        children.addLast(child);
    }

    @Override
    public String toString() {
        return type +
                "(" + children +
                ')';
    }
}

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
        Node parseTree = createParseTree(pattern);
        return null;
    }

    private static Node createParseTree(String input) {
        return null;
    }
}

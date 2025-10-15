package org.ian;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the solution
 */
public class Solution {
    private static final Logger log = LoggerFactory.getLogger(Solution.class);
    private String input;

    public Solution(String input) {
        this.input = input;
    }

    /**
     * Runs the solution
     */
    public void run() {
        log.info("ran solution");

        List<String> tokens = new ArrayList<>();

        int cursor = 0;
        while (cursor < this.input.length()) {
            char current = this.input.charAt(cursor);
            log.info("cursor >" + cursor + ":" + current);
            // whitespace
            // letter
            // number (value)
            // quote (string value)

            // skip whitespace
            if (Character.isWhitespace(current)) {
                log.info("... white skip");
                cursor++;
            } else if (Character.isLetter(current)) {
                // extract a string
                String field = extractField(cursor);
                tokens.add(field);

                cursor = cursor + field.length(); // move cursor to the end
                log.info("extracted field: " + field);
            } else if (current == '"') {
                // its a string literal
                String literal = extractString(cursor + 1);
                log.info(literal);
                tokens.add(literal);

                cursor = cursor + literal.length() + 2; // 2 addition positions for the quotes
                log.info("extracted literal: [" + literal + "]");
            } else {
                String operator = extractOperator(cursor);
                tokens.add(operator);
                log.info("... other skip");
                cursor=cursor+operator.length();
            }
        }

        for (String token : tokens) {
            log.info("# Results > " + token);
        }
    }

    private String extractField(int start) {
        // move the end until we get an character which is not a field
        int end = start;
        while (end < this.input.length()
                && Character.isLetterOrDigit(this.input.charAt(end))
                || this.input.charAt(end) == '_') {
            end++;
        }

        return this.input.substring(start, end);
    }

    private String extractString(int start) {
        // move the end until we get an character which is not a field
        int end = start;
        while (end < this.input.length() && this.input.charAt(end) != '"') {
            end++;
        }

        return this.input.substring(start, end);
    }

    Set<Character> OPERATORS = Set.of('!','=');
    private String extractOperator(int start) {
        int end = start;
        while (end < this.input.length() && OPERATORS.contains(this.input.charAt(end))) {
            end++;
        }

        return this.input.substring(start, end);
    }
}

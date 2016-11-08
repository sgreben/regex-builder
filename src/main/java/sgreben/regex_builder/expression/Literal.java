package sgreben.regex_builder.expression;

import sgreben.regex_builder.tokens.LITERAL;
import sgreben.regex_builder.tokens.TOKEN;

public class Literal extends Nullary {
    private final String literal;

    public Literal(String literal) {
        this.literal = literal;
    }

    public String getLiteral() {
        return literal;
    }

    @Override
    public void compile(sgreben.regex_builder.CaptureGroupIndex index, java.util.List<TOKEN> output) {
        output.add(new LITERAL(literal));
    }
}
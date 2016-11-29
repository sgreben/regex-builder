package com.github.sgreben.regex_builder.expression;

import com.github.sgreben.regex_builder.CaptureGroupIndex;
import com.github.sgreben.regex_builder.tokens.TOKEN;
import com.github.sgreben.regex_builder.tokens.LITERAL;

public class Literal extends Nullary {
    private final String literal;

    public Literal(String literal) {
        this.literal = literal;
    }

    public String getLiteral() {
        return literal;
    }

    @Override
    public void compile(CaptureGroupIndex index, java.util.List<TOKEN> output) {
        output.add(new LITERAL(literal));
    }
}
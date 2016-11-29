package com.github.sgreben.regex_builder.expression;

import com.github.sgreben.regex_builder.CaptureGroupIndex;
import com.github.sgreben.regex_builder.Expression;
import com.github.sgreben.regex_builder.tokens.END_GROUP;
import com.github.sgreben.regex_builder.tokens.PLUS;
import com.github.sgreben.regex_builder.tokens.START_GROUP_NON_CAPTURING;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public class Repeat1 extends Unary {
    public Repeat1(Expression child) {
        super(child);
    }

    @Override
    public void compile(CaptureGroupIndex index, java.util.List<TOKEN> output) {
        output.add(new START_GROUP_NON_CAPTURING());
        for (Expression child : children()) {
            child.compile(index, output);
        }
        output.add(new END_GROUP());
        output.add(new PLUS());
    }

    @Override
    public Expression possessive() {
        return new Repeat1Possessive(child());
    }

    @Override
    public Expression reluctant() {
        return new Repeat1Reluctant(child());
    }
}
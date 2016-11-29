package com.github.sgreben.regex_builder.expression;

import com.github.sgreben.regex_builder.CaptureGroupIndex;
import com.github.sgreben.regex_builder.Expression;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public class Sequence extends Nary {
    public Sequence(Expression... children) {
        super(children);
    }

    @Override
    public void compile(CaptureGroupIndex index, java.util.List<TOKEN> output) {
        for (Expression child : children()) {
            child.compile(index, output);
        }
    }
}
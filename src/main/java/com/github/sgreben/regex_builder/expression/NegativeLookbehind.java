package com.github.sgreben.regex_builder.expression;

import com.github.sgreben.regex_builder.CaptureGroupIndex;
import com.github.sgreben.regex_builder.Expression;
import com.github.sgreben.regex_builder.tokens.END_GROUP;
import com.github.sgreben.regex_builder.tokens.TOKEN;
import com.github.sgreben.regex_builder.tokens.START_NEGATIVE_LOOKBEHIND;

public class NegativeLookbehind extends Unary {
    public NegativeLookbehind(Expression child) {
        super(child);
    }

    @Override
    public void compile(CaptureGroupIndex index, java.util.List<TOKEN> output) {
        output.add(new START_NEGATIVE_LOOKBEHIND());
        child().compile(index, output);
        output.add(new END_GROUP());
    }
}
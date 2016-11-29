package com.github.sgreben.regex_builder.expression;

import com.github.sgreben.regex_builder.CaptureGroupIndex;
import com.github.sgreben.regex_builder.Expression;
import com.github.sgreben.regex_builder.tokens.PLUS;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public class RepeatAtLeastPossessive extends RepeatAtLeast {

    public RepeatAtLeastPossessive(Expression child, Integer lowerBound) {
        super(child, lowerBound);
    }

    @Override
    public void compile(CaptureGroupIndex index, java.util.List<TOKEN> output) {
        super.compile(index, output);
        output.add(new PLUS());
    }
}

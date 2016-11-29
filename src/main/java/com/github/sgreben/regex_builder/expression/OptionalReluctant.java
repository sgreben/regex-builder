package com.github.sgreben.regex_builder.expression;

import com.github.sgreben.regex_builder.CaptureGroupIndex;
import com.github.sgreben.regex_builder.Expression;
import com.github.sgreben.regex_builder.tokens.PLUS;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public class OptionalReluctant extends Optional {
    public OptionalReluctant(Expression child) {
        super(child);
    }

    @Override
    public void compile(CaptureGroupIndex index, java.util.List<TOKEN> output) {
        super.compile(index, output);
        output.add(new PLUS());
    }
}
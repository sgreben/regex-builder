package com.github.sgreben.regex_builder.expression;

import com.github.sgreben.regex_builder.CaptureGroupIndex;
import com.github.sgreben.regex_builder.Expression;
import com.github.sgreben.regex_builder.tokens.QUESTION;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public class RepeatReluctant extends Repeat {
    public RepeatReluctant(Expression child, Integer lowerBound, Integer upperBound) {
        super(child, lowerBound, upperBound);
    }

    public RepeatReluctant(Expression child, Integer bound) {
        this(child, bound, bound);
    }

    public RepeatReluctant(Expression child) {
        super(child);
    }

    @Override
    public void compile(CaptureGroupIndex index, java.util.List<TOKEN> output) {
        super.compile(index, output);
        output.add(new QUESTION());
    }
}
package com.github.sgreben.regex_builder.expression;

import com.github.sgreben.regex_builder.CaptureGroupIndex;
import com.github.sgreben.regex_builder.tokens.RAW;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public class EndLine extends Nullary {
    public EndLine() {
    }

    @Override
    public void compile(CaptureGroupIndex index, java.util.List<TOKEN> output) {
        output.add(new RAW("$"));
    }
}
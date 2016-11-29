package com.github.sgreben.regex_builder.expression;

import com.github.sgreben.regex_builder.CaptureGroup;
import com.github.sgreben.regex_builder.CaptureGroupIndex;
import com.github.sgreben.regex_builder.tokens.TOKEN;
import com.github.sgreben.regex_builder.tokens.BACK_REFERENCE;

public class BackReference extends Nullary {
    private final CaptureGroup group;

    public BackReference(CaptureGroup group) {
        this.group = group;
    }

    @Override
    public void compile(CaptureGroupIndex index, java.util.List<TOKEN> output) {
        output.add(new BACK_REFERENCE(index.get(group)));
    }
}
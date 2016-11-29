package com.github.sgreben.regex_builder.expression;

import com.github.sgreben.regex_builder.CaptureGroupIndex;
import com.github.sgreben.regex_builder.CharClass;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public class CharClassExpression extends Nullary {
    private final CharClass charClass;

    public CharClassExpression(CharClass charClass) {
        this.charClass = charClass;
    }

    @Override
    public void compile(CaptureGroupIndex index, java.util.List<TOKEN> output) {
        charClass.compile(output);
    }
}
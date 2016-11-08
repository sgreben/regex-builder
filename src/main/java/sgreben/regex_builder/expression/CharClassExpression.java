package sgreben.regex_builder.expression;

import sgreben.regex_builder.CharClass;
import sgreben.regex_builder.tokens.TOKEN;

public class CharClassExpression extends Nullary {
    private final CharClass charClass;

    public CharClassExpression(CharClass charClass) {
        this.charClass = charClass;
    }

    @Override
    public void compile(sgreben.regex_builder.CaptureGroupIndex index, java.util.List<TOKEN> output) {
        charClass.compile(output);
    }
}
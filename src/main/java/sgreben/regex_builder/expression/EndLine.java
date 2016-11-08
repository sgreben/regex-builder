package sgreben.regex_builder.expression;

import sgreben.regex_builder.tokens.RAW;
import sgreben.regex_builder.tokens.TOKEN;

public class EndLine extends Nullary {
    public EndLine() {
    }

    @Override
    public void compile(sgreben.regex_builder.CaptureGroupIndex index, java.util.List<TOKEN> output) {
        output.add(new RAW("$"));
    }
}
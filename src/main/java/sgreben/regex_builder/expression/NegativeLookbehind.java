package sgreben.regex_builder.expression;

import sgreben.regex_builder.Expression;
import sgreben.regex_builder.tokens.END_GROUP;
import sgreben.regex_builder.tokens.START_NEGATIVE_LOOKBEHIND;
import sgreben.regex_builder.tokens.TOKEN;

public class NegativeLookbehind extends Unary {
    public NegativeLookbehind(Expression child) {
        super(child);
    }

    @Override
    public void compile(sgreben.regex_builder.CaptureGroupIndex index, java.util.List<TOKEN> output) {
        output.add(new START_NEGATIVE_LOOKBEHIND());
        child().compile(index, output);
        output.add(new END_GROUP());
    }
}
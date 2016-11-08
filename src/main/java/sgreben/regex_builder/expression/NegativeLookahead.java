package sgreben.regex_builder.expression;

import sgreben.regex_builder.Expression;
import sgreben.regex_builder.tokens.END_GROUP;
import sgreben.regex_builder.tokens.START_NEGATIVE_LOOKAHEAD;
import sgreben.regex_builder.tokens.TOKEN;

public class NegativeLookahead extends Unary {
    public NegativeLookahead(Expression child) {
        super(child);
    }

    @Override
    public void compile(sgreben.regex_builder.CaptureGroupIndex index, java.util.List<TOKEN> output) {
        output.add(new START_NEGATIVE_LOOKAHEAD());
        child().compile(index, output);
        output.add(new END_GROUP());
    }
}
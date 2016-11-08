package sgreben.regex_builder.expression;

import sgreben.regex_builder.Expression;
import sgreben.regex_builder.tokens.END_GROUP;
import sgreben.regex_builder.tokens.QUESTION;
import sgreben.regex_builder.tokens.START_GROUP_NON_CAPTURING;
import sgreben.regex_builder.tokens.TOKEN;

public class Optional extends Unary {
    public Optional(Expression child) {
        super(child);
    }

    @Override
    public void compile(sgreben.regex_builder.CaptureGroupIndex index, java.util.List<TOKEN> output) {
        output.add(new START_GROUP_NON_CAPTURING());
        for (Expression child : children()) {
            child.compile(index, output);
        }
        output.add(new END_GROUP());
        output.add(new QUESTION());
    }

    @Override
    public Expression possessive() {
        return new OptionalPossessive(child());
    }

    @Override
    public Expression reluctant() {
        return new OptionalReluctant(child());
    }
}
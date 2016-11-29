package com.github.sgreben.regex_builder.expression;

import com.github.sgreben.regex_builder.CaptureGroupIndex;
import com.github.sgreben.regex_builder.Expression;
import com.github.sgreben.regex_builder.tokens.BRACES;
import com.github.sgreben.regex_builder.tokens.END_GROUP;
import com.github.sgreben.regex_builder.tokens.START_GROUP_NON_CAPTURING;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public class RepeatAtLeast extends Unary {
    private final Integer lowerBound;

    public RepeatAtLeast(Expression child, Integer lowerBound) {
        super(child);
        this.lowerBound = lowerBound;
    }

    @Override
    public void compile(CaptureGroupIndex index, java.util.List<TOKEN> output) {
        output.add(new START_GROUP_NON_CAPTURING());
        for (Expression child : children()) {
            child.compile(index, output);
        }
        output.add(new END_GROUP());
        output.add(new BRACES(lowerBound, null));
    }

    @Override
    public Expression possessive() {
        return new RepeatAtLeastPossessive(child(), lowerBound);
    }

    @Override
    public Expression reluctant() {
        return new RepeatAtLeastReluctant(child(), lowerBound);
    }
}

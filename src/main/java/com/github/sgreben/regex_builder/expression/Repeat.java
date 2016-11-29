package com.github.sgreben.regex_builder.expression;

import com.github.sgreben.regex_builder.CaptureGroupIndex;
import com.github.sgreben.regex_builder.Expression;
import com.github.sgreben.regex_builder.tokens.*;

public class Repeat extends Unary {
    private final Integer lowerBound;
    private final Integer upperBound;

    public Repeat(Expression child) {
        super(child);
        this.lowerBound = null;
        this.upperBound = null;
    }

    public Repeat(Expression child, Integer lowerBound, Integer upperBound) {
        super(child);
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public Repeat(Expression child, Integer bound) {
        this(child, bound, bound);
    }

    @Override
    public void compile(CaptureGroupIndex index, java.util.List<TOKEN> output) {
        output.add(new START_GROUP_NON_CAPTURING());
        for (Expression child : children()) {
            child.compile(index, output);
        }
        output.add(new END_GROUP());
        if (lowerBound != null && upperBound != null && !lowerBound.equals(upperBound)) {
            output.add(new BRACES(lowerBound, upperBound));
        } else if (lowerBound != null) {
            output.add(new BRACES(lowerBound));
        } else if (upperBound != null) {
            output.add(new BRACES(upperBound));
        } else {
            output.add(new STAR());
        }
    }

    @Override
    public Expression possessive() {
        return new RepeatPossessive(child(), lowerBound, upperBound);
    }

    @Override
    public Expression reluctant() {
        return new RepeatReluctant(child(), lowerBound, upperBound);
    }
}
package com.github.sgreben.regex_builder;

import com.github.sgreben.regex_builder.expression.Unary;
import com.github.sgreben.regex_builder.tokens.END_GROUP;
import com.github.sgreben.regex_builder.tokens.START_GROUP;
import com.github.sgreben.regex_builder.tokens.START_GROUP_NAMED;
import com.github.sgreben.regex_builder.tokens.TOKEN;

/**
 * A regex capture group "(...)"
 */
public class CaptureGroup extends Unary {
    private final String name;

    public CaptureGroup(Expression expression) {
        super(expression);
        name = null;
    }

    public CaptureGroup(Expression expression, String name) {
        super(expression);
        this.name = name;
    }

    @Override
    public void compile(CaptureGroupIndex index, java.util.List<TOKEN> output) {
        if (name != null) {
            output.add(new START_GROUP_NAMED(name));
        } else {
            output.add(new START_GROUP());
        }
        for (Expression child : children()) {
            child.compile(index, output);
        }
        output.add(new END_GROUP());
    }
}

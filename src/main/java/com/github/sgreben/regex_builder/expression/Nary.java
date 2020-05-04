package com.github.sgreben.regex_builder.expression;

import com.github.sgreben.regex_builder.Expression;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

abstract class Nary extends ExpressionBase {
    private final List<Expression> children;

    public Nary(final Expression... childrenArray) {
        this.children = Collections.unmodifiableList(
                Arrays.asList(childrenArray)
        );
    }

    @Override
    public Iterable<Expression> children() {
        return children;
    }
}

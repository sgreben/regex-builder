package com.github.sgreben.regex_builder.expression;

import com.github.sgreben.regex_builder.Expression;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

abstract class Nullary extends ExpressionBase {
    private static final List<Expression> empty =
            Collections.unmodifiableList(new LinkedList<Expression>());

    public Nullary() {
    }

    @Override
    public Iterable<Expression> children() {
        return empty;
    }
}

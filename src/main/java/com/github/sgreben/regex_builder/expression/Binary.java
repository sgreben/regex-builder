package com.github.sgreben.regex_builder.expression;

import com.github.sgreben.regex_builder.Expression;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

abstract class Binary extends ExpressionBase implements Expression {
    private final List<Expression> children;

    public Binary(Expression leftChild, Expression rightChild) {
        List<Expression> children = new LinkedList<Expression>();
        children.add(leftChild);
        children.add(rightChild);
        this.children = Collections.unmodifiableList(children);
    }

    @Override
    public Iterable<Expression> children() {
        return children;
    }
}
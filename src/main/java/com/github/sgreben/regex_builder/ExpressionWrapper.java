package com.github.sgreben.regex_builder;

import com.github.sgreben.regex_builder.expression.ExpressionVisitor;
import com.github.sgreben.regex_builder.tokens.TOKEN;

import java.util.Collections;
import java.util.List;

public class ExpressionWrapper implements Expression {
    private Expression expression;

    public ExpressionWrapper() {
        expression = null;
    }

    public Iterable<Expression> children() {
        return Collections.singleton(expression);
    }

    public void accept(ExpressionVisitor visitor) {
        expression.accept(visitor);
    }

    public void compile(CaptureGroupIndex index, List<TOKEN> output) {
        expression.compile(index, output);
    }

    public Expression possessive() {
        return expression.possessive();
    }

    public Expression reluctant() {
        return expression.reluctant();
    }

    void setExpression(Expression expression) {
        this.expression = expression;
    }
}
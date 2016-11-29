package com.github.sgreben.regex_builder;

import com.github.sgreben.regex_builder.expression.ExpressionVisitor;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public interface Expression {
    java.lang.Iterable<Expression> children();

    void accept(ExpressionVisitor visitor);

    void compile(CaptureGroupIndex index, java.util.List<TOKEN> output);

    Expression possessive();

    Expression reluctant();
}
package com.github.sgreben.regex_builder.expression;

import com.github.sgreben.regex_builder.Expression;

abstract class ExpressionBase implements Expression {
    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitPre(this);
        for (Expression child : children()) {
            child.accept(visitor);
        }
        visitor.visitPost(this);
    }

    @Override
    public Expression possessive() {
        return this;
    }

    @Override
    public Expression reluctant() {
        return this;
    }
}
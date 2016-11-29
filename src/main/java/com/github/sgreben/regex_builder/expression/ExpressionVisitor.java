package com.github.sgreben.regex_builder.expression;

import com.github.sgreben.regex_builder.Expression;

public interface ExpressionVisitor {
    void visitPre(Expression node);
    void visitPost(Expression node);
}
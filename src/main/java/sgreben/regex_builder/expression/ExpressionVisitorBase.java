package sgreben.regex_builder.expression;

import sgreben.regex_builder.Expression;

public class ExpressionVisitorBase implements ExpressionVisitor {
    @Override
    public void visitPre(Expression node) {}

    @Override
    public void visitPost(Expression node) {}
}
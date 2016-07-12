package sgreben.regex_builder.expression;
import sgreben.regex_builder.Expression;

public class ExpressionVisitorBase implements ExpressionVisitor {
	public void visitPre(Expression node) {}
	public void visitPost(Expression node) {}
}
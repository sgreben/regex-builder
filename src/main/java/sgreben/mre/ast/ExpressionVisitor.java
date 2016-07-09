package sgreben.mre.expression;

public interface ExpressionVisitor {
	void visitPre(Expression node);
	void visitPost(Expression node);
}
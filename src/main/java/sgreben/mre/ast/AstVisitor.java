package sgreben.mre.ast;

public interface AstVisitor {
	void visitPre(Ast node);
	void visitPost(Ast node);
}
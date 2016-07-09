package sgreben.mre.ast;

public interface Ast {
	java.lang.Iterable<Ast> children();
	void accept(AstVisitor visitor);
	void compile(StringBuilder sb);
}
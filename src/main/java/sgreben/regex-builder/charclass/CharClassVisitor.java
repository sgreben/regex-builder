package sgreben.regex_builder.charclass;
import sgreben.regex_builder.CharClass;

public interface CharClassVisitor {
	void visitPre(CharClass node);
	void visitPost(CharClass node);
}
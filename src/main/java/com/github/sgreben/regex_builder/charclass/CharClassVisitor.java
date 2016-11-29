package com.github.sgreben.regex_builder.charclass;
import com.github.sgreben.regex_builder.CharClass;

public interface CharClassVisitor {
	void visitPre(CharClass node);
	void visitPost(CharClass node);
}
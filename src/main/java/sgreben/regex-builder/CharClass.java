package sgreben.regex_builder;

import sgreben.regex_builder.charclass.CharClassVisitor;
import sgreben.regex_builder.tokens.TOKEN;

public interface CharClass {
	java.lang.Iterable<CharClass> children();
	void accept(CharClassVisitor visitor);
	void compile(java.util.List<TOKEN> output);
}
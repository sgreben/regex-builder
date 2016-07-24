package sgreben.regex_builder.expression;

import sgreben.regex_builder.tokens.*;
import sgreben.regex_builder.Expression;

public class Sequence extends Nary {
	public Sequence(Expression... children) { super(children); }
	
	public void compile(sgreben.regex_builder.CaptureGroupIndex index, java.util.List<TOKEN> output) {
		for(Expression child : children()) {
			child.compile(index, output);
		}
	}
}
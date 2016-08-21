package sgreben.regex_builder.expression;

import sgreben.regex_builder.tokens.*;
import sgreben.regex_builder.Expression;

public class Atomic extends Unary {
	public Atomic(Expression child) { super(child); }
	
	public void compile(sgreben.regex_builder.CaptureGroupIndex index, java.util.List<TOKEN> output) {
		output.add(new START_GROUP_ATOMIC_NON_CAPTURING());
		for(Expression child : children()) {
			child.compile(index, output);
		}
		output.add(new END_GROUP());
	}
}
package sgreben.regex_builder.expression;

import sgreben.regex_builder.tokens.*;
import sgreben.regex_builder.Expression;

public class OptionalPossessive extends Optional {
	public OptionalPossessive(Expression child) { super(child); }
	
	public void compile(sgreben.regex_builder.CaptureGroupIndex index, java.util.List<TOKEN> output) {
		super.compile(index, output);
		output.add(new PLUS());
	}
}
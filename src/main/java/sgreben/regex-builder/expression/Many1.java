package sgreben.regex_builder.expression;

import sgreben.regex_builder.tokens.*;

public class Many1 extends Unary {
	public Many1(Expression child) { super(child); }
	
	public void compile(java.util.List<TOKEN> output) {
		output.add(new START_GROUP_NON_CAPTURING());
		for(Expression child : children()) {
			child.compile(output);
		}
		output.add(new END_GROUP());
		output.add(new PLUS());
	}
}
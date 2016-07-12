package sgreben.regex_builder.expression;

import sgreben.regex_builder.tokens.*;

public class Repeat extends Unary {
	private final Integer lowerBound;
	private final Integer upperBound;
	
	public Repeat(Expression child, Integer lowerBound, Integer upperBound) { 
		super(child); 
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}
	
	public Repeat(Expression child, Integer bound) { 
		this(child, bound, null); 
	}
	
	public void compile(java.util.List<TOKEN> output) {
		output.add(new START_GROUP_NON_CAPTURING());
		for(Expression child : children()) {
			child.compile(output);
		}
		output.add(new END_GROUP());
		if(lowerBound != null && upperBound != null) {
			output.add(new BRACES(lowerBound, upperBound));
		} else if (lowerBound != null) {
			output.add(new BRACES(lowerBound));
		} else if (upperBound != null) {
			output.add(new BRACES(upperBound));
		}
	}
}
package sgreben.regex_builder.expression;

import sgreben.regex_builder.tokens.*;

/* Raw regex string (for regex syntax not yet modeled in the builder).
 *
 */
public class Raw extends Nullary {
	private final String rawClass; 
	
	public Raw(String rawClass) {
		this.rawClass = rawClass;
	}
	
	public void compile(java.util.List<TOKEN> output) {
		output.add(new RAW(rawClass));
	}
}

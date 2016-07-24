package sgreben.regex_builder.expression;

import sgreben.regex_builder.CaptureGroup;
import sgreben.regex_builder.tokens.TOKEN;
import sgreben.regex_builder.tokens.BACK_REFERENCE;

public class BackReference extends Nullary {
	private final CaptureGroup group; 
	public BackReference(CaptureGroup group) {
		this.group = group;
	}
	public void compile(sgreben.regex_builder.CaptureGroupIndex index, java.util.List<TOKEN> output) {
		output.add(new BACK_REFERENCE(index.get(group)));
	}
}
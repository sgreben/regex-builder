package sgreben.regex_builder.expression;

import sgreben.regex_builder.tokens.TOKEN;
import sgreben.regex_builder.tokens.RAW;

public class BeginLine extends Nullary {
	public BeginLine() {}
	public void compile(sgreben.regex_builder.CaptureGroupIndex index, java.util.List<TOKEN> output) {
		output.add(new RAW("^"));
	}
}
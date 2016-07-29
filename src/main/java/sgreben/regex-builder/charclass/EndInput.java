package sgreben.regex_builder.charclass;

import sgreben.regex_builder.tokens.*;

public class EndInput extends Nullary {
	public EndInput() {}
	public void compile(java.util.List<TOKEN> output) {
		output.add(new RAW("\\z"));
	}
}
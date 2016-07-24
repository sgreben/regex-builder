package sgreben.regex_builder.charclass;

import sgreben.regex_builder.tokens.*;

public class HorizontalWhitespace extends Nullary {
	public HorizontalWhitespace() {}
	public void compile(java.util.List<TOKEN> output) {
		output.add(new RAW("\\h"));
	}
}
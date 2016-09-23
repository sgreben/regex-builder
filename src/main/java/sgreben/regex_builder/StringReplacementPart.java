package sgreben.regex_builder;

class StringReplacementPart implements ReplacementPart {
	private final String string;
	
	public StringReplacementPart(String string) {
		this.string = string;
	}
	
	public String toReplacementString(CaptureGroupIndex index) {
		return string;
	}
}
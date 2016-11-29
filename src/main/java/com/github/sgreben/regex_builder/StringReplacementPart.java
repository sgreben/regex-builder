package com.github.sgreben.regex_builder;

class StringReplacementPart implements ReplacementPart {
	private final String string;
	
	public StringReplacementPart(String string) {
		this.string = string;
	}

	@Override
	public String toReplacementString(CaptureGroupIndex index) {
		return string;
	}
}
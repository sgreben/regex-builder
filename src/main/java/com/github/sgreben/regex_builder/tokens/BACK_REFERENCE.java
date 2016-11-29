package com.github.sgreben.regex_builder.tokens;

public class BACK_REFERENCE implements TOKEN {
	private final int groupIndex;
	
	public BACK_REFERENCE(int groupIndex) {
		this.groupIndex = groupIndex; 
	}
	
	public String regexString() {
		return "\\"+groupIndex;
	}
}
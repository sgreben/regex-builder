package sgreben.regex_builder.tokens;

import java.util.regex.Pattern;
import sgreben.regex_builder.CaptureGroup;
import sgreben.regex_builder.CaptureGroupIndex;

public class BACK_REFERENCE implements TOKEN {
	private final int groupIndex;
	
	public BACK_REFERENCE(int groupIndex) {
		this.groupIndex = groupIndex; 
	}
	
	public String regexString() {
		return "\\"+groupIndex;
	}
}
package sgreben.regex_builder.tokens;

import java.util.regex.Pattern;
import sgreben.regex_builder.CaptureGroup;
import sgreben.regex_builder.CaptureGroupIndex;

public class BACK_REFERENCE implements TOKEN {
	private final CaptureGroup group;
	
	public BACK_REFERENCE(CaptureGroup group) {
		this.group = group; 
	}
	
	public String regexString(sgreben.regex_builder.CaptureGroupIndex index) {
		return "\\"+index.getIndex(group);
	}
}
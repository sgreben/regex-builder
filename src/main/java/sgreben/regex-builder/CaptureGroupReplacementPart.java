package sgreben.regex_builder;

class CaptureGroupReplacementPart implements ReplacementPart {
	private final CaptureGroup group;
	
	public CaptureGroupReplacementPart(CaptureGroup group) {
		this.group = group;
	}
	
	public String toReplacementString(CaptureGroupIndex index) {
		return "$"+index.getIndex(group);		
	}
}
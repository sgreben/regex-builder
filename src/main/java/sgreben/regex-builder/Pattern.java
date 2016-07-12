package sgreben.mre;

public class Pattern {
	private final java.util.regex.Pattern rawPattern;
	private final CaptureGroup entireMatch;
	private final CaptureGroupIndex groupIndex;
	
	public Pattern(java.util.regex.Pattern rawPattern, 
				   CaptureGroup entireMatch,
				   CaptureGroupIndex groupIndex) {
		this.rawPattern = rawPattern;
		this.entireMatch = entireMatch;
		this.groupIndex = groupIndex;
	}
	
	public Matcher matcher(String text) {
		java.util.regex.Matcher matcher = rawPattern.matcher(text);
		return new Matcher(matcher, entireMatch, groupIndex);
	}
}
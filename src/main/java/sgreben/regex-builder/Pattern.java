package sgreben.regex_builder;

public class Pattern {
	private final java.util.regex.Pattern rawPattern;
	private final CaptureGroupIndex groupIndex;
	
	public Pattern(java.util.regex.Pattern rawPattern, 
				   CaptureGroupIndex groupIndex) {
		this.rawPattern = rawPattern;
		this.groupIndex = groupIndex;
	}
	
	public Matcher matcher(String text) {
		java.util.regex.Matcher matcher = rawPattern.matcher(text);
		return new Matcher(matcher, groupIndex);
	}
}
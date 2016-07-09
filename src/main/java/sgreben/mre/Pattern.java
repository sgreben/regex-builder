package sgreben.mre;

public class Pattern {
	private final java.util.regex.Pattern rawPattern;
	
	public Pattern(java.util.regex.Pattern rawPattern) {
		this.rawPattern = rawPattern;
	}
	
	public Matcher matcher(String text) {
		java.util.regex.Matcher matcher = rawPattern.matcher(text);
		return new Matcher(matcher);
	}
}
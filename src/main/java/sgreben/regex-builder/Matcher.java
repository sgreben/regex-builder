package sgreben.regex_builder;

public class Matcher {
	private final java.util.regex.Matcher matcher;
	private final CaptureGroupIndex groupIndex;
	
	public Matcher(java.util.regex.Matcher matcher, CaptureGroupIndex groupIndex) {
		this.matcher = matcher;
		this.groupIndex = groupIndex;
	}
	
	public boolean matches() {
		return matcher.matches();
	}
	
	public boolean find() {
		return matcher.find();
	}
	
	public boolean find(int offset) {
		return matcher.find(offset);
	}
	
	public int start(CaptureGroup group) {
		return matcher.start(groupIndex.getIndex(group));
	}
	
	public int end(CaptureGroup group) {
		return matcher.end(groupIndex.getIndex(group));
	}
	
	public String group(CaptureGroup group) {
		return matcher.group(groupIndex.getIndex(group));
	}
	
	public String replaceAll(Replacement replacement) {
		String replacementString = replacement.toReplacementString(groupIndex);
		return matcher.replaceAll(replacementString);
	}
}
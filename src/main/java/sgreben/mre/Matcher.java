package sgreben.mre;

public class Matcher {
	private final java.util.regex.Matcher matcher;
	private final CaptureGroup entireMatch;
	private final CaptureGroupIndex groupIndex;
	
	public Matcher(java.util.regex.Matcher matcher, CaptureGroup entireMatch, CaptureGroupIndex groupIndex) {
		this.matcher = matcher;
		this.entireMatch = entireMatch;
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
}
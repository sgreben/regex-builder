package sgreben.mre.charclass;

public class CharClass {
	private final String regexString;
	public CharClass(char lower, char upper) {
		regexString = lower+"-"+upper;
	}
	public CharClass(String regexString) {
		this.regexString = regexString;
	}
	public String getRegexString() {
		return regexString;
	}
	public CharClass complement() {
		return new CharClass("^"+regexString);
	}
    public CharClass intersection(CharClass other) {
	 	return new CharClass(regexString+"&&&["+other+"]");
	}
	public CharClass union(CharClass other) {
		return new CharClass(regexString+other.getRegexString());
	}
}
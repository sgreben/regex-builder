package sgreben.regex_builder.tokens;

public class BRACES implements TOKEN {
	private final String bracesString;
	
	public BRACES(Integer... numbers) {
		if(numbers.length == 0) {
			bracesString = "";
		} else if (numbers.length == 1) {
			bracesString = "{"+numbers[0]+"}";
		} else if (numbers.length == 2 && numbers[1] == null) {
			bracesString = "{"+numbers[0]+",}";
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			sb.append(numbers[0]);
			for(int i = 1; i < numbers.length; ++i) {
				sb.append(", ");
				sb.append(numbers[i]);
			}
			sb.append("}");
			bracesString = sb.toString();
		}
	}
	
	public String regexString() {
		return bracesString;
	}
}
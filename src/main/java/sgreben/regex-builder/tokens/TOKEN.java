package sgreben.regex_builder.tokens;

// Right now, the "tokens" is just regex tokens. 
// If we switch to a custom regex engine, this will contain the regex VM instructions.

public interface TOKEN {
	String regexString();
}
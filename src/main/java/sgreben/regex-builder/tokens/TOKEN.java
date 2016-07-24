package sgreben.regex_builder.tokens;

public interface TOKEN {
	String regexString(sgreben.regex_builder.CaptureGroupIndex index);
}
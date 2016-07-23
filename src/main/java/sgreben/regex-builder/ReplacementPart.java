package sgreben.regex_builder;

interface ReplacementPart {
	String toReplacementString(CaptureGroupIndex index);
}
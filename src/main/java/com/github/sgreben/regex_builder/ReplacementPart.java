package com.github.sgreben.regex_builder;

@FunctionalInterface
interface ReplacementPart {
	String toReplacementString(CaptureGroupIndex index);
}
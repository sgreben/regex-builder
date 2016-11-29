package com.github.sgreben.regex_builder;

import java.util.regex.MatchResult;

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

    public int start() {
        return matcher.start();
    }

    public int start(CaptureGroup group) {
        return matcher.start(groupIndex.get(group));
    }

    public int end() {
        return matcher.end();
    }

    public int end(CaptureGroup group) {
        return matcher.end(groupIndex.get(group));
    }

    public MatchResult toMatchResult() {
        return matcher.toMatchResult();
    }

    public String group(CaptureGroup group) {
        return matcher.group(groupIndex.get(group));
    }

    public String replaceAll(Replacement replacement) {
        String replacementString = replacement.toReplacementString(groupIndex);
        return matcher.replaceAll(replacementString);
    }

    public String replaceFirst(Replacement replacement) {
        String replacementString = replacement.toReplacementString(groupIndex);
        return matcher.replaceFirst(replacementString);
    }

    public Matcher appendReplacement(StringBuffer sb, Replacement replacement) {
        String replacementString = replacement.toReplacementString(groupIndex);
        return new Matcher(matcher.appendReplacement(sb, replacementString), groupIndex);
    }

    public StringBuffer appendTail(StringBuffer sb) {
        return matcher.appendTail(sb);
    }

    public String group() {
        return matcher.group();
    }

    public int groupCount() {
        return matcher.groupCount();
    }
}
package com.github.sgreben.regex_builder;

import java.util.LinkedList;

public class Replacement {
    private final LinkedList<ReplacementPart> parts;
    private String replacementString = null;

    public Replacement() {
        parts = new LinkedList<>();
    }

    public void addPart(ReplacementPart part) {
        parts.add(part);
    }

    public String toReplacementString(CaptureGroupIndex index) {
        if (replacementString != null) {
            return replacementString;
        }
        StringBuilder sb = new StringBuilder();
        for (ReplacementPart part : parts) {
            sb.append(part.toReplacementString(index));
        }
        replacementString = sb.toString();
        return replacementString;
    }
}
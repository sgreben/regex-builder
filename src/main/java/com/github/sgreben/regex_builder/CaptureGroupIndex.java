package com.github.sgreben.regex_builder;

import java.util.HashMap;
import java.util.Map;

/**
 * A mapping between CaptureGroup objects and the underlying regex group indices.
 */
public class CaptureGroupIndex {
    private final Map<CaptureGroup, Integer> groupIndex;

    public CaptureGroupIndex() {
        this.groupIndex = new HashMap<CaptureGroup, Integer>();
    }

    public Integer get(CaptureGroup group) {
        return groupIndex.get(group);
    }

    public void put(CaptureGroup group, Integer index) {
        groupIndex.put(group, index);
    }
}
package sgreben.regex_builder;

class CaptureGroupReplacementPart implements ReplacementPart {
    private final CaptureGroup group;

    public CaptureGroupReplacementPart(CaptureGroup group) {
        this.group = group;
    }

    @Override
    public String toReplacementString(CaptureGroupIndex index) {
        return "$" + index.get(group);
    }
}
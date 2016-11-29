package com.github.sgreben.regex_builder.tokens;

public class CHAR_CLASS_NAMED implements TOKEN {
    private final String name;
    private final String regexString;

    public CHAR_CLASS_NAMED(String name) {
        this.name = name;
        this.regexString = "\\p{"+name+"}";
    }

    @Override
    public String regexString() {
        return regexString;
    }
}

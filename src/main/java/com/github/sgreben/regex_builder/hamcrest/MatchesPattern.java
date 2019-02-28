package com.github.sgreben.regex_builder.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import com.github.sgreben.regex_builder.Pattern;



public class MatchesPattern extends TypeSafeMatcher<String> {
    private final Pattern pattern;

    public MatchesPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    protected boolean matchesSafely(String item) {
        return pattern.matcher(item).matches();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a string matching the pattern '" + pattern + "'");
    }

    /**
     * Creates a matcher of {@link java.lang.String} that matches when the examined string
     * exactly matches the given {@link com.github.sgreben.regex_builder.Pattern}.
     */
    public static Matcher<String> matchesPattern(Pattern pattern) {
        return new MatchesPattern(pattern);
    }
}

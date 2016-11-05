package sgreben.regex_builder;

import sgreben.regex_builder.expression.*;

/**
 * Regular expression builder
 */
public class Re {
    /**
     * Match a string literal.
     */
    public static Expression string(String s) {
        return new Literal(s);
    }

    /**
     * Match a character literal.
     */
    public static Expression character(char c) {
        return string("" + c);
    }

    /**
     * Match the beginning of a line.
     */
    public static Expression beginLine() {
        return new BeginLine();
    }

    /**
     * Match the end of a line.
     */
    public static Expression endLine() {
        return new EndLine();
    }

    /**
     * Match a character from the given char class.
     */
    public static Expression charClass(CharClass charClass) {
        return new CharClassExpression(charClass);
    }

    /**
     * Match any character.
     */
    public static Expression anyCharacter() {
        return charClass(CharClass.anyChar());
    }

    /**
     * Repeat the expression zero or more times.
     */
    public static Repeat repeat(Expression e) {
        return new Repeat(e);
    }

    /**
     * Repeat the expression zero or more times.
     */
    public static Repeat repeat(CharClass c) {
        return new Repeat(charClass(c));
    }

    /**
     * Repeat the expression zero or more times.
     */
    public static Repeat repeat(String e) {
        return repeat(string(e));
    }

    /**
     * Repeat the expression zero or more times.
     */
    public static Repeat repeat(char e) {
        return repeat(character(e));
    }

    /**
     * Repeat the expression zero or more times (possessive)
     */
    public static RepeatPossessive repeatPossessive(Expression e) {
        return new RepeatPossessive(e);
    }

    /**
     * Repeat the expression zero or more times (possessive)
     */
    public static RepeatPossessive repeatPossessive(CharClass c) {
        return new RepeatPossessive(charClass(c));
    }

    /**
     * Repeat the expression zero or more times (possessive)
     */
    public static RepeatPossessive repeatPossessive(String e) {
        return repeatPossessive(string(e));
    }

    /**
     * Repeat the expression zero or more times (possessive)
     */
    public static RepeatPossessive repeatPossessive(char e) {
        return repeatPossessive(character(e));
    }

    /**
     * Repeat the expression zero or more times (reluctant)
     */
    public static RepeatReluctant repeatReluctant(Expression e) {
        return new RepeatReluctant(e);
    }

    /**
     * Repeat the expression zero or more times (reluctant)
     */
    public static RepeatReluctant repeatReluctant(CharClass c) {
        return new RepeatReluctant(charClass(c));
    }

    /**
     * Repeat the expression zero or more times (reluctant)
     */
    public static RepeatReluctant repeatReluctant(String e) {
        return repeatReluctant(string(e));
    }

    /**
     * Repeat the expression zero or more times (reluctant)
     */
    public static RepeatReluctant repeatReluctant(char e) {
        return repeatReluctant(character(e));
    }

    /**
     * Repeat the expression the given number of times
     */
    public static Repeat repeat(Expression e, int times) {
        return new Repeat(e, times);
    }

    /**
     * Repeat the expression the given number of times
     */
    public static Repeat repeat(CharClass c, int times) {
        return repeat(charClass(c), times);
    }

    /**
     * Repeat the expression the given number of times
     */
    public static Repeat repeat(String e, int times) {
        return repeat(string(e), times);
    }

    /**
     * Repeat the expression the given number of times
     */
    public static Repeat repeat(char e, int times) {
        return repeat(character(e), times);
    }

    /**
     * Repeat the expression a number of times within the given range.
     */
    public static Repeat repeat(Expression e, int timesMin, int timesMax) {
        return new Repeat(e, timesMin, timesMax);
    }

    /**
     * Repeat the expression a number of times within the given range.
     */
    public static Repeat repeat(String e, int timesMin, int timesMax) {
        return repeat(string(e), timesMin, timesMax);
    }

    /**
     * Repeat the expression a number of times within the given range.
     */
    public static Repeat repeat(CharClass c, int timesMin, int timesMax) {
        return repeat(charClass(c), timesMin, timesMax);
    }

    /**
     * Repeat the expression a number of times within the given range.
     */
    public static Repeat repeat(char e, int timesMin, int timesMax) {
        return repeat(character(e), timesMin, timesMax);
    }

    /**
     * Repeat the expression a number of times (possessive).
     */
    public static Expression repeatPossessive(Expression e, int times) {
        return new RepeatPossessive(e, times);
    }

    /**
     * Repeat the expression a number of times (possessive).
     */
    public static Expression repeatPossessive(CharClass c, int times) {
        return repeatPossessive(charClass(c), times);
    }

    /**
     * Repeat the expression a number of times (possessive).
     */
    public static Expression repeatPossessive(String e, int times) {
        return repeatPossessive(string(e), times);
    }

    /**
     * Repeat the expression a number of times (possessive).
     */
    public static Expression repeatPossessive(char e, int times) {
        return repeatPossessive(character(e), times);
    }

    /**
     * Repeat the expression a number of times within the given range (possessive).
     */
    public static Expression repeatPossessive(Expression e, int timesMin, int timesMax) {
        return new RepeatPossessive(e, timesMin, timesMax);
    }

    /**
     * Repeat the expression a number of times within the given range (possessive).
     */
    public static Expression repeatPossessive(CharClass c, int timesMin, int timesMax) {
        return repeatPossessive(charClass(c), timesMin, timesMax);
    }

    /**
     * Repeat the expression a number of times within the given range (possessive).
     */
    public static Expression repeatPossessive(String e, int timesMin, int timesMax) {
        return repeatPossessive(string(e), timesMin, timesMax);
    }

    /**
     * Repeat the expression a number of times within the given range (possessive).
     */
    public static Expression repeatPossessive(char e, int timesMin, int timesMax) {
        return repeatPossessive(character(e), timesMin, timesMax);
    }

    /**
     * Repeat the expression the given number of times (reluctant).
     */
    public static Expression repeatReluctant(Expression e, int times) {
        return new RepeatReluctant(e, times);
    }

    /**
     * Repeat the expression the given number of times (reluctant).
     */
    public static Expression repeatReluctant(String e, int times) {
        return repeatReluctant(string(e), times);
    }

    /**
     * Repeat the expression the given number of times (reluctant).
     */
    public static Expression repeatReluctant(CharClass c, int times) {
        return repeatReluctant(charClass(c), times);
    }

    /**
     * Repeat the expression the given number of times (reluctant).
     */
    public static Expression repeatReluctant(char e, int times) {
        return repeatReluctant(character(e), times);
    }

    /**
     * Repeat the expression a number of times within the given range (reluctant).
     */
    public static Expression repeatReluctant(Expression e, int timesMin, int timesMax) {
        return new RepeatReluctant(e, timesMin, timesMax);
    }

    /**
     * Repeat the expression a number of times within the given range (reluctant).
     */
    public static Expression repeatReluctant(CharClass c, int timesMin, int timesMax) {
        return repeatReluctant(charClass(c), timesMin, timesMax);
    }

    /**
     * Repeat the expression a number of times within the given range (reluctant).
     */
    public static Expression repeatReluctant(String e, int timesMin, int timesMax) {
        return repeatReluctant(string(e), timesMin, timesMax);
    }

    /**
     * Repeat the expression a number of times within the given range (reluctant).
     */
    public static Expression repeatReluctant(char e, int timesMin, int timesMax) {
        return repeatReluctant(character(e), timesMin, timesMax);
    }

    /**
     * Repeat the expression one or more times.
     */
    public static Repeat1 repeat1(Expression e) {
        return new Repeat1(e);
    }

    /**
     * Repeat the expression one or more times.
     */
    public static Repeat1 repeat1(CharClass c) {
        return repeat1(charClass(c));
    }

    /**
     * Repeat the expression one or more times.
     */
    public static Repeat1 repeat1(String e) {
        return repeat1(string(e));
    }

    /**
     * Repeat the expression one or more times.
     */
    public static Repeat1 repeat1(char e) {
        return repeat1(character(e));
    }

    /**
     * Repeat the expression one or more times (possessive).
     */
    public static Expression repeat1Possessive(Expression e) {
        return new Repeat1Possessive(e);
    }

    /**
     * Repeat the expression one or more times (possessive).
     */
    public static Expression repeat1Possessive(String e) {
        return repeat1Possessive(string(e));
    }

    /**
     * Repeat the expression one or more times (possessive).
     */
    public static Expression repeat1Possessive(char e) {
        return repeat1Possessive(character(e));
    }

    /**
     * Repeat the expression one or more times (possessive).
     */
    public static Expression repeat1Possessive(CharClass c) {
        return repeat1Possessive(charClass(c));
    }

    /**
     * Repeat the expression one or more times (reluctant).
     */
    public static Expression repeat1Reluctant(Expression e) {
        return new Repeat1Reluctant(e);
    }

    /**
     * Repeat the expression one or more times (reluctant).
     */
    public static Expression repeat1Reluctant(CharClass c) {
        return repeat1Reluctant(charClass(c));
    }

    /**
     * Repeat the expression one or more times (reluctant).
     */
    public static Expression repeat1Reluctant(String e) {
        return repeat1Reluctant(string(e));
    }

    /**
     * Repeat the expression one or more times (reluctant).
     */
    public static Expression repeat1Reluctant(char e) {
        return repeat1Reluctant(character(e));
    }

    /**
     * Match a sequence of expessions.
     */
    public static Expression sequence(Expression... es) {
        return new Sequence(es);
    }

    /**
     * Match a sequence of expessions.
     */
    public static Expression sequence(Object... os) {
        return sequence(convertStrings(os));
    }

    /**
     * Match one of the given expessions.
     */
    public static Expression choice(Expression... es) {
        return new Choice(es);
    }

    /**
     * Match one of the given expessions.
     */
    public static Expression choice(Object... es) {
        return sequence(convertStrings(es));
    }

    /**
     * Match the given expession zero or one times.
     */
    public static Optional optional(Expression e) {
        return new Optional(e);
    }

    /**
     * Match the given expession zero or one times.
     */
    public static Optional optional(CharClass c) {
        return optional(charClass(c));
    }

    /**
     * Match the given expession zero or one times.
     */
    public static Optional optional(String e) {
        return optional(string(e));
    }

    /**
     * Match the given expession zero or one times.
     */
    public static Optional optional(char e) {
        return optional(character(e));
    }

    /**
     * Match the given expession zero or one times (possessive).
     */
    public static Expression optionalPossessive(Expression e) {
        return new OptionalPossessive(e);
    }

    /**
     * Match the given expession zero or one times (possessive).
     */
    public static Expression optionalPossessive(CharClass c) {
        return optionalPossessive(charClass(c));
    }

    /**
     * Match the given expession zero or one times (possessive).
     */
    public static Expression optionalPossessive(String e) {
        return optionalPossessive(string(e));
    }

    /**
     * Match the given expession zero or one times (possessive).
     */
    public static Expression optionalPossessive(char e) {
        return optionalPossessive(character(e));
    }

    /**
     * Match the given expession zero or one times (reluctant).
     */
    public static Expression optionalReluctant(Expression e) {
        return new OptionalReluctant(e);
    }

    /**
     * Match the given expession zero or one times (reluctant).
     */
    public static Expression optionalReluctant(String e) {
        return optionalReluctant(string(e));
    }

    /**
     * Match the given expession zero or one times (reluctant).
     */
    public static Expression optionalReluctant(CharClass c) {
        return optionalReluctant(charClass(c));
    }

    /**
     * Match the given expession zero or one times (reluctant).
     */
    public static Expression optionalReluctant(char e) {
        return optionalReluctant(character(e));
    }

    /**
     * Match a (possibly empty) sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy(Expression separator, Expression e) {
        return optional(separatedBy1(separator, e));
    }

    /**
     * Match a (possibly empty) sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy(char separator, Expression e) {
        return optional(separatedBy1(character(separator), e));
    }

    /**
     * Match a (possibly empty) sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy(String separator, Expression e) {
        return separatedBy(string(separator), e);
    }

    /**
     * Match a (possibly empty) sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy(Expression separator, String e) {
        return separatedBy(separator, string(e));
    }

    /**
     * Match a (possibly empty) sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy(Expression separator, char e) {
        return separatedBy(separator, character(e));
    }

    /**
     * Match a (possibly empty) sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy(String separator, String e) {
        return separatedBy(string(separator), string(e));
    }

    /**
     * Match a (possibly empty) sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy(char separator, String e) {
        return separatedBy(character(separator), string(e));
    }

    /**
     * Match a (possibly empty) sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy(String separator, char e) {
        return separatedBy(string(separator), character(e));
    }

    /**
     * Match a (possibly empty) sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy(char separator, char e) {
        return separatedBy(character(separator), character(e));
    }

    /**
     * Match a (possibly empty) sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy(CharClass separator, Expression e) {
        return separatedBy(charClass(separator), e);
    }

    /**
     * Match a (possibly empty) sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy(CharClass separator, String e) {
        return separatedBy(charClass(separator), string(e));
    }

    /**
     * Match a (possibly empty) sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy(CharClass separator, char e) {
        return separatedBy(charClass(separator), character(e));
    }

    /**
     * Match a (possibly empty) sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy(CharClass separator, CharClass c) {
        return separatedBy(charClass(separator), charClass(c));
    }

    /**
     * Match a (possibly empty) sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy(Expression separator, CharClass c) {
        return separatedBy(separator, charClass(c));
    }

    /**
     * Match a (possibly empty) sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy(String separator, CharClass c) {
        return separatedBy(separator, charClass(c));
    }

    /**
     * Match a (possibly empty) sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy(char separator, CharClass c) {
        return separatedBy(separator, charClass(c));
    }

    /**
     * Match a (possibly empty) sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy1(Expression separator, Expression e) {
        return sequence(e, repeat(sequence(separator, e)));
    }

    /**
     * Match a nonempty sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy1(String separator, Expression e) {
        return separatedBy1(string(separator), e);
    }

    /**
     * Match a nonempty sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy1(Expression separator, String e) {
        return separatedBy1(separator, string(e));
    }

    /**
     * Match a nonempty sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy1(Expression separator, char e) {
        return separatedBy1(separator, character(e));
    }

    /**
     * Match a nonempty sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy1(String separator, String e) {
        return separatedBy1(string(separator), string(e));
    }

    /**
     * Match a nonempty sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy1(char separator, String e) {
        return separatedBy1(character(separator), string(e));
    }

    /**
     * Match a nonempty sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy1(String separator, char e) {
        return separatedBy1(string(separator), character(e));
    }

    /**
     * Match a nonempty sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy1(char separator, char e) {
        return separatedBy1(character(separator), character(e));
    }

    /**
     * Match a nonempty sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy1(CharClass separator, Expression e) {
        return separatedBy1(charClass(separator), e);
    }

    /**
     * Match a nonempty sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy1(CharClass separator, String e) {
        return separatedBy1(charClass(separator), string(e));
    }

    /**
     * Match a nonempty sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy1(CharClass separator, char e) {
        return separatedBy1(charClass(separator), e);
    }

    /**
     * Match a nonempty sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy1(CharClass separator, CharClass e) {
        return separatedBy1(charClass(separator), charClass(e));
    }

    /**
     * Match a nonempty sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy1(char separator, CharClass e) {
        return separatedBy1(character(separator), charClass(e));
    }

    /**
     * Match a nonempty sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy1(String separator, CharClass e) {
        return separatedBy1(string(separator), charClass(e));
    }

    /**
     * Match a nonempty sequence of matches of [e], separated by matches of [separator].
     */
    public static Expression separatedBy1(Expression separator, CharClass e) {
        return separatedBy1(separator, charClass(e));
    }

    public static Expression separatedByPossessive(Expression separator, Expression e) {
        return optionalPossessive(separatedBy1Possessive(separator, e));
    }

    public static Expression separatedByPossessive(char separator, Expression e) {
        return optionalPossessive(separatedBy1Possessive(character(separator), e));
    }

    public static Expression separatedByPossessive(String separator, Expression e) {
        return separatedBy(string(separator), e);
    }

    public static Expression separatedByPossessive(Expression separator, String e) {
        return separatedBy(separator, string(e));
    }

    public static Expression separatedByPossessive(Expression separator, char e) {
        return separatedBy(separator, character(e));
    }

    public static Expression separatedByPossessive(String separator, String e) {
        return separatedBy(string(separator), string(e));
    }

    public static Expression separatedByPossessive(char separator, String e) {
        return separatedBy(character(separator), string(e));
    }

    public static Expression separatedByPossessive(String separator, char e) {
        return separatedBy(string(separator), character(e));
    }

    public static Expression separatedByPossessive(char separator, char e) {
        return separatedBy(character(separator), character(e));
    }

    public static Expression separatedBy1Possessive(Expression separator, Expression e) {
        return sequence(e, repeatPossessive(sequence(separator, e)));
    }

    public static Expression separatedBy1Possessive(String separator, Expression e) {
        return separatedBy1Possessive(string(separator), e);
    }

    public static Expression separatedBy1Possessive(Expression separator, String e) {
        return separatedBy1Possessive(separator, string(e));
    }

    public static Expression separatedBy1Possessive(Expression separator, char e) {
        return separatedBy1Possessive(separator, character(e));
    }

    public static Expression separatedBy1Possessive(String separator, String e) {
        return separatedBy1Possessive(string(separator), string(e));
    }

    public static Expression separatedBy1Possessive(char separator, String e) {
        return separatedBy1Possessive(character(separator), string(e));
    }

    public static Expression separatedBy1Possessive(String separator, char e) {
        return separatedBy1Possessive(string(separator), character(e));
    }

    public static Expression separatedBy1Possessive(char separator, char e) {
        return separatedBy1Possessive(character(separator), character(e));
    }

    public static Expression word() {
        return repeat1(CharClass.wordChar());
    }

    public static Expression number() {
        return repeat1(CharClass.digit());
    }

    public static Expression whitespace() {
        return repeat(CharClass.whitespaceChar());
    }

    public static Expression whitespace1() {
        return repeat1(CharClass.whitespaceChar());
    }

    public static Expression atomic(Expression expression) {
        return new Atomic(expression);
    }

    public static Expression atomic(Object... os) {
        return atomic(sequence(os));
    }

    public static Expression atomic(String e) {
        return atomic(string(e));
    }

    public static Expression atomic(char e) {
        return atomic(character(e));
    }

    public static CaptureGroup capture(Expression expression) {
        return new CaptureGroup(expression);
    }

    public static CaptureGroup capture(Object... os) {
        return capture(sequence(os));
    }

    public static CaptureGroup capture(String e) {
        return capture(string(e));
    }

    public static CaptureGroup capture(char e) {
        return capture(character(e));
    }

    public static Expression positiveLookbehind(Expression expression) {
        return new PositiveLookbehind(expression);
    }

    public static Expression positiveLookbehind(Object... os) {
        return positiveLookbehind(sequence(os));
    }

    public static Expression positiveLookbehind(String e) {
        return positiveLookbehind(string(e));
    }

    public static Expression positiveLookbehind(char e) {
        return positiveLookbehind(character(e));
    }

    public static Expression negativeLookbehind(Expression expression) {
        return new NegativeLookbehind(expression);
    }

    public static Expression negativeLookbehind(Object... os) {
        return negativeLookbehind(sequence(os));
    }

    public static Expression negativeLookbehind(String e) {
        return negativeLookbehind(string(e));
    }

    public static Expression negativeLookbehind(char e) {
        return negativeLookbehind(character(e));
    }

    public static Expression positiveLookahead(Expression expression) {
        return new PositiveLookahead(expression);
    }

    public static Expression positiveLookahead(Object... os) {
        return positiveLookahead(sequence(os));
    }

    public static Expression positiveLookahead(String e) {
        return positiveLookahead(string(e));
    }

    public static Expression positiveLookahead(char e) {
        return positiveLookahead(character(e));
    }

    public static Expression negativeLookahead(Expression expression) {
        return new NegativeLookahead(expression);
    }

    public static Expression negativeLookahead(Object... os) {
        return negativeLookahead(sequence(os));
    }

    public static Expression negativeLookahead(String e) {
        return negativeLookahead(string(e));
    }

    public static Expression negativeLookahead(char e) {
        return negativeLookahead(character(e));
    }

    public static Expression backReference(CaptureGroup group) {
        return new BackReference(group);
    }

    /**
     * A replacement expression consisting of a sequence of strings and capture groups.
     */
    public static Replacement replacement(Object... os) {
        Replacement replacement = new Replacement();
        for (Object o : os) {
            if (o instanceof String) {
                replacement.addPart(
                        new StringReplacementPart((String) o)
                );
            } else if (o instanceof Character) {
                replacement.addPart(
                        new StringReplacementPart("" + (Character) o)
                );
            } else if (o instanceof CaptureGroup) {
                replacement.addPart(
                        new CaptureGroupReplacementPart((CaptureGroup) o)
                );
            }
        }
        return replacement;
    }

    /**
     * Converts vararg actuals consisting of expressions, strings,
     * characters and character classes into an array of expressions.
     */
    private static Expression[] convertStrings(Object[] os) {
        Expression[] es = new Expression[os.length];
        for (int i = 0; i < os.length; ++i) {
            if (os[i] instanceof Expression) {
                es[i] = (Expression) os[i];
            } else if (os[i] instanceof String) {
                es[i] = string((String) os[i]);
            } else if (os[i] instanceof CharClass) {
                es[i] = charClass((CharClass) os[i]);
            } else if (os[i] instanceof Character) {
                es[i] = character((Character) os[i]);
            }
        }
        return es;
    }
}
package sgreben.regex_builder;

import sgreben.regex_builder.expression.CharClassExpression;

/**
 * Fluent regex builder API
 */
public class FluentRe {
    private Expression expression;

    private FluentRe(Expression expression) {
        this.expression = expression;
    }

    public static FluentRe match(Expression e) {
        return new FluentRe(e);
    }

    public static FluentRe match(String s) {
        return new FluentRe(Re.string(s));
    }

    public static FluentRe match(CharClass c) {
        return new FluentRe(new CharClassExpression(c));
    }

    public static FluentRe match(char c) {
        return match("" + c);
    }

    public FluentRe or(FluentRe e) {
        return new FluentRe(Re.choice(expression, e.expression));
    }

    public FluentRe repeat() {
        return new FluentRe(Re.repeat(expression));
    }

    public FluentRe repeat1() {
        return new FluentRe(Re.repeat1(expression));
    }

    public FluentRe repeat(int n) {
        return new FluentRe(Re.repeat(expression, n));
    }

    public FluentRe repeatAtLeast(int n) {
        return new FluentRe(Re.repeatAtLeast(expression, n));
    }

    public FluentRe repeat(int min, int max) {
        return new FluentRe(Re.repeat(expression, min, max));
    }

    /**
     * Adds a "reluctant" modifier (if applicable)
     */
    public FluentRe reluctant() {
        return new FluentRe(expression.reluctant());
    }

    /**
     * Adds a "possessive" modifier (if applicable)
     */
    public FluentRe possessive() {
        return new FluentRe(expression.possessive());
    }

    public FluentRe or(char e) {
        return new FluentRe(Re.choice(expression, e));
    }

    public FluentRe or(CharClass e) {
        return new FluentRe(Re.choice(expression, e));
    }

    public FluentRe or(String e) {
        return new FluentRe(Re.choice(expression, e));
    }

    public FluentRe or(Expression e) {
        return new FluentRe(Re.choice(expression, e));
    }

    public CaptureGroup capture() {
        return Re.capture(expression);
    }

    public FluentRe separatedBy1(FluentRe e) {
        return new FluentRe(Re.separatedBy1(e.expression, expression));
    }

    public FluentRe separatedBy1(Expression e) {
        return new FluentRe(Re.separatedBy1(e, expression));
    }

    public FluentRe separatedBy1(String e) {
        return new FluentRe(Re.separatedBy1(e, expression));
    }

    public FluentRe separatedBy1(char e) {
        return new FluentRe(Re.separatedBy1(e, expression));
    }

    public FluentRe separatedBy1(CharClass e) {
        return new FluentRe(Re.separatedBy1(e, expression));
    }

    public FluentRe separatedBy1Possessive(FluentRe e) {
        return new FluentRe(Re.separatedBy1Possessive(e.expression, expression));
    }

    public FluentRe separatedBy1Possessive(Expression e) {
        return new FluentRe(Re.separatedBy1Possessive(e, expression));
    }

    public FluentRe separatedBy1Possessive(String e) {
        return new FluentRe(Re.separatedBy1Possessive(e, expression));
    }

    public FluentRe separatedBy1Possessive(char e) {
        return new FluentRe(Re.separatedBy1Possessive(e, expression));
    }

    public FluentRe separatedBy1Possessive(CharClass e) {
        return new FluentRe(Re.separatedBy1Possessive(e, expression));
    }

    public FluentRe separatedBy(FluentRe e) {
        return new FluentRe(Re.separatedBy(e.expression, expression));
    }

    public FluentRe separatedBy(Expression e) {
        return new FluentRe(Re.separatedBy(e, expression));
    }

    public FluentRe separatedBy(String e) {
        return new FluentRe(Re.separatedBy(e, expression));
    }

    public FluentRe separatedBy(char e) {
        return new FluentRe(Re.separatedBy(e, expression));
    }

    public FluentRe separatedBy(CharClass e) {
        return new FluentRe(Re.separatedBy(e, expression));
    }

    public FluentRe separatedByPossessive(FluentRe e) {
        return new FluentRe(Re.separatedByPossessive(e.expression, expression));
    }

    public FluentRe separatedByPossessive(Expression e) {
        return new FluentRe(Re.separatedByPossessive(e, expression));
    }

    public FluentRe separatedByPossessive(String e) {
        return new FluentRe(Re.separatedByPossessive(e, expression));
    }

    public FluentRe separatedByPossessive(char e) {
        return new FluentRe(Re.separatedByPossessive(e, expression));
    }

    public FluentRe separatedByPossessive(CharClass e) {
        return new FluentRe(Re.separatedByPossessive(e, expression));
    }

    public FluentRe then(FluentRe e) {
        return new FluentRe(Re.sequence(expression, e.expression));
    }

    public FluentRe then(Expression e) {
        return new FluentRe(Re.sequence(expression, e));
    }

    public FluentRe then(String s) {
        return new FluentRe(Re.sequence(expression, s));
    }

    public FluentRe then(char c) {
        return new FluentRe(Re.sequence(expression, c));
    }

    public FluentRe then(CharClass c) {
        return new FluentRe(Re.sequence(expression, c));
    }

    public FluentRe optional() {
        return new FluentRe(Re.optional(expression));
    }

    public Pattern compile() {
        return Pattern.compile(expression);
    }
}
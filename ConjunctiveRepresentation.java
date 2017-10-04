final class ConjunctiveRepresentation {

    private final String representation;
    private final boolean negation;

    ConjunctiveRepresentation(String expression, boolean negation) {
        this.representation = expression;
        this.negation = negation;
    }

    public final String getRepresentation() {
        return representation;
    }

    public final boolean isNegated() {
        return negation;
    }
}

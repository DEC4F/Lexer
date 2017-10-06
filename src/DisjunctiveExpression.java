import java.util.Scanner;

/**
 * Created by Stanley Tian on 9/24/2017.
 */
public final class DisjunctiveExpression {

    // the factor of the expression
    private final Factor factor;
    // indicator of whether it starts with a not
    private final boolean positive;

    /**
     * a constructor that gives values to fields
     * @param factor is the factor
     * @param positive is the indicator
     */
    private DisjunctiveExpression(Factor factor, boolean positive){
        this.factor = factor;
        this.positive = positive;
    }

    /**
     * Builder class as an inner class
     */
    public static final class Builder {
        /**
         * build a new instance of DisjunctiveExpression
         * @param token is the first token to processed
         * @param lexer outputs the rest of the tokens
         * @return a disjunctive expression representation of the expression
         * @throws ParserException when
         */
        public static final DisjunctiveExpression build(LocationalToken token, DisjunctiveLexer lexer) throws ParserException {
            boolean isPositive = (token.getTokenType() != Token.Type.NOT);
            if (!isPositive)
                token = lexer.nextValid().get();
            return newDisExp(token, lexer, isPositive);
        }

        /**
         * a private helper method that returns the disjunctive expression instance of the input
         * @param token is the first token to be evaluated
         * @param lexer outputs the following tokens
         * @param isPositive tells if the expression is negated
         * @return an disjunctive expression instance of the input
         * @throws ParserException when the token's type matches with neither ID nor OPEN
         */
        private static DisjunctiveExpression newDisExp(LocationalToken token, DisjunctiveLexer lexer, boolean isPositive) throws ParserException {
            if (token.getTokenType() == Token.Type.ID)
                return new DisjunctiveExpression(Identifier.Builder.build(token), isPositive);
            return new DisjunctiveExpression(CompoundFactor.Builder.build(token, lexer), isPositive);
        }
    }

    /**
     * flips the positivity of the expression
     * @return a negated version of DisjunctiveExpression
     */
    public final DisjunctiveExpression negate() {
        return new DisjunctiveExpression(factor, !positive);
    }

    /**
     * return the string representation of the conjunctive form of this disjunctive expression
     * @return
     */
    public final String conjunctiveRepresentation() {
        ConjunctiveRepresentation rep = factor.conjunctiveRepresentation();
        if (rep.isNegated() == positive)
            return "not " + rep.getRepresentation();
        return rep.getRepresentation();
    }

    /**
     * override toString() to make testing easier
     * @return string representation of a DisjunctiveExpression instance
     */
    @Override
    public String toString() {
        return "DisjunctiveExpression{" +
                "factor=" + factor +
                ", positive=" + positive +
                '}';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String boolExp = sc.nextLine();
        DisjunctiveLexer lexer = new DisjunctiveLexer(boolExp);
        DisjunctiveExpression factor;
        try {
            factor = DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer);
            System.out.println(factor.conjunctiveRepresentation());
        } catch (ParserException e1) {
            System.out.println("Error: " + e1);
        }
    }

}

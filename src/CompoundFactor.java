/**
 * Created by Stanley Tian on 9/23/2017.
 */
public class CompoundFactor implements Factor{

    // the left part of the expression
    private final DisjunctiveExpression leftExpression;
    // the right part of the expression
    private final DisjunctiveExpression rightExpression;

    /**
     * a constructor that sets value for left expression and right expression
     * @param leftExpression is the left part of the expression
     * @param rightExpression is the right part of the expression
     */
    private CompoundFactor(DisjunctiveExpression leftExpression, DisjunctiveExpression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    /**
     * Builder class as an inner class
     */
    public static final class Builder {

        /**
         * build a new instance of CompoundFactor
         * @param token should always have an OPEN type
         * @param lexer should always have a ID, AND, ID, CLOSE style output
         * @return the CompoundFactor representation of the input
         * @throws ParserException when type doesn't match with the OPEN, ID, AND, ID, CLOSE sequence
         */
        public static final CompoundFactor build(LocationalToken token, DisjunctiveLexer lexer) throws ParserException {
            ParserException.verify(Token.Type.OPEN, token);
            DisjunctiveExpression left = expression(lexer);
            // I used lexer.nextValid.get() directly because I used ParserException.verify() on every Optional<LocationalToken> that's been output
            ParserException.verify(Token.Type.AND, lexer.nextValid().get());
            DisjunctiveExpression right = expression(lexer);
            ParserException.verify(Token.Type.CLOSE, lexer.nextValid().get());
            return new CompoundFactor(left, right);
        }

        /**
         * helper method that build a new expression out of the lexer
         * @param lexer is the disjunctive lexer
         * @return a disjunctive lexer
         * @throws ParserException when the lexer contains an Optional.empty token
         */
        private static DisjunctiveExpression expression (DisjunctiveLexer lexer) throws ParserException{
            return DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer);
        }
    }

    /**
     * finds the conjunctive representation of this compound factor
     * @return the conjuncative representation of this compound factor
     */
    @Override
    public ConjunctiveRepresentation conjunctiveRepresentation() {
        String str = "( " + leftExpression.negate().conjunctiveRepresentation() + " or "
                         + rightExpression.negate().conjunctiveRepresentation() + ")";
        return new ConjunctiveRepresentation(str, true);
    }

    /**
     * override toString() to make testing easier
     * @return string representation of a CompoundFactor instance
     */
    @Override
    public String toString() {
        return "CompoundFactor{" +
                "leftExpression=" + leftExpression.toString() +
                ", rightExpression=" + rightExpression.toString() +
                '}';
    }
}

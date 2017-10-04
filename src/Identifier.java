/**
 * Created by Stanley Tian on 9/23/2017.
 */
public final class Identifier implements Factor {
    // is the data in the identifier
    private final String id;

    /**
     * a constructor
     * @param tokenID is the data in the ID token
     */
    private Identifier(String tokenID) {
        id = tokenID;
    }

    /**
     * Builder class as an inner class
     */
    public static final class Builder {

        public static final Identifier build(LocationalToken token) throws ParserException {
            if (token.getTokenType() == Token.Type.ID)
                return new Identifier(token.getTokenData().get());
            throw new ParserException(ParserException.ErrorCode.ID_EXPECTED);
        }
    }

    /**
     * finds the conjunctive representation of this identifier
     * @return the conjunctive representation of this identifier
     */
    @Override
    public ConjunctiveRepresentation conjunctiveRepresentation() {
        return new ConjunctiveRepresentation(id, false);
    }

    /**
     * override toString() to make testing easier
     * @return string representation of a DisjunctiveExpression instance
     */
    @Override
    public String toString() {
        return "Identifier{" +
                "id='" + id + '\'' +
                '}';
    }
}

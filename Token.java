import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Stanley Tian on 9/10/2017.
 */
public final class Token {

    public enum Type {
        NOT("not", false, Optional.empty(), false),
        AND("and", false, Optional.of(ParserException.ErrorCode.AND_EXPECTED), true),
        OR("or", false, Optional.empty(), true),
        OPEN("\\(", false, Optional.of(ParserException.ErrorCode.OPEN_EXPECTED), false),
        CLOSE("\\)", false, Optional.of(ParserException.ErrorCode.CLOSE_EXPECTED), false),
        ID("(?!and\\b|or\\b|not\\b)\\b([a-z]+)", true, Optional.of(ParserException.ErrorCode.ID_EXPECTED), false),
        NUMBER("\\d+", true, Optional.empty(), false),
        BINARYOP("\\+|-|\\*|/", true, Optional.empty(), false),
        WHITESPACE("\\s+", false, Optional.empty(), false);

        // pattern of the type
        private final String pattern;
        // indicator of whether it has ancillary data or not
        private final Boolean hasData;
        // complexity estimator
        private boolean isComplex;
        // error code for parser exception
        private Optional<ParserException.ErrorCode> errorCode;

        /**
         * type constructor
         * @param pattern is the pattern of the type
         * @param hasData tells if the type has ancillary data
         */
        Type(String pattern, Boolean hasData, Optional<ParserException.ErrorCode> errorCode, boolean isComplex) {
            this.pattern = pattern;
            this.hasData = hasData;
            this.errorCode = errorCode;
            this.isComplex = isComplex;
        }

        /**
         * type pattern getter
         * @return the pattern of the type
         */
        public String getPattern() {
            return pattern;
        }

        /**
         * check if this type has data
         * @return true if it has data, false if it doesn't
         */
        public Boolean hasDataOrNot() {
            return hasData;
        }

        /**
         * isComplex getter
         * @return true if it is a complex type, false if it's not
         */
        public Boolean getIsComplex() {
            return isComplex;
        }

        public ParserException.ErrorCode getErrorCode() {
            if (errorCode.isPresent())
                return errorCode.get();
            return null;
        }

    }

    // type of the token
    private final Type tokenType;
    // ancillary data of the token, could be null
    private final Optional<String> data;
    // a hash map that stores all the token created
    private static Map<Builder, Token> tokenMap = new HashMap<>();

    /**
     * token constructor
     * @param tokenType is the type of the token
     * @param data      is the ancillary data of the token
     */
    private Token(Type tokenType, Optional<String> data) {
        this.tokenType = tokenType;
        this.data = data;
    }

    /**
     * token type getter
     * @return type of the token
     */
    public Type getTokenType() {
        return tokenType;
    }

    /**
     * ancillary data getter
     * @return ancillary data of the token even it's empty
     */
    public Optional<String> getData() {
        return data;
    }

    /**
     * Builder class as an inner class
     */
    private static class Builder {
        // type of the token to be built
        private final Type plannedTokenType;
        // data of the token to be built, could be null
        private final Optional<String> plannedData;

        /**
         * Builder constructor
         * @param plannedTokenType is the type of the token to be built
         * @param plannedData      is the data of the token to be built
         */
        Builder(Type plannedTokenType, Optional<String> plannedData) {
            this.plannedTokenType = plannedTokenType;
            this.plannedData = plannedData;
        }

        /**
         * creates a new token and store it in tokenMap
         * @return a new token
         */
        private Token build() {
            return new Token(plannedTokenType, plannedData);
        }

        /**
         * check if the parameter has the same hash code as the token
         * @param arg0 is the object to be compared with builder
         * @return true if they are the same builder, false if they don't match
         */
        @Override
        public boolean equals(Object arg0) {
            if (this == arg0)
                return true;
            if (arg0 == null || getClass() != arg0.getClass())
                return false;

            Builder builder = (Builder) arg0;

            return (plannedTokenType == builder.plannedTokenType) && (plannedData.equals(builder.plannedData));
        }

        /**
         * generate a hash code for the token
         * @return a hash code
         */
        @Override
        public int hashCode() {
            return plannedTokenType.hashCode() * 137 + plannedData.hashCode();
        }
    }

    /**
     * helper method that checks if ancillary data matches the pattern of the type
     * @param type      is the type of the token
     * @param inputData is the ancillary data of the token
     * @return the ancillary data if it matches with pattern, throws an exception if it doesn't
     */
    private static Optional<String> checkPattern(Type type, String inputData) {
        String pattern = type.getPattern();
        if (inputData != null) {
            if (inputData.matches(pattern)) {
                return Optional.ofNullable(inputData);
            } else
                throw new IllegalArgumentException();
        } else
            return Optional.ofNullable(inputData);
    }

    /**
     * returns a token of given type and data. If it was created before, it returns an old token, but if it wasn't created, a new token
     * @param type is the type of the token to be returned
     * @param data is the data of the token to be returned
     * @return a token of given type and data
     */
    public static Token of(Type type, String data) {
        Optional<String> optData = checkPattern(type, data);
        Builder newTokenBuilder = new Builder(type, optData);

        if (tokenMap.containsKey(newTokenBuilder)) {
            Token oldToken = tokenMap.get(newTokenBuilder);
            return oldToken;
        }
        else {
            Token newToken = newTokenBuilder.build();
            tokenMap.put(newTokenBuilder, newToken);
            return newToken;
        }
    }

    /**
     * checks if the parameter has the same type and data as the token itself
     * @param newToken the token to be compared
     * @return true if they are have the same type and data, false if they don't match
     */
    @Override
    public boolean equals(Object newToken) {
        if (this == newToken)
            return true;
        if (newToken == null || getClass() != newToken.getClass())
            return false;

        Token token = (Token) newToken;

        if (tokenType != token.tokenType)
            return false;
        return data.equals(token.data);
    }

    /**
     * generates a hashcode for the token
     * @return a hashcode
     */
    @Override
    public int hashCode() {
        return tokenType.hashCode() * 113 + data.hashCode();
    }

    /**
     * represents a token in string
     * @return a string representation of token
     */
    @Override
    public String toString() {
        String str = "tokenType = " + this.getTokenType().toString();
        if (this.getTokenType().hasDataOrNot()) {
            str += ", data = " + this.getData();
        }
        return str;
    }
}
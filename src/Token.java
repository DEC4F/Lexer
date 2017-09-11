import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Dr.Black Jack on 9/10/2017.
 */
public final class Token {

    public enum Type
    {
        NOT("not", false),
        AND("and", false),
        OR("or", false),
        OPEN("(", false),
        CLOSE(")", false),

        ID("", true),
        NUMBER("", true),
        BINARYOP("", true),
        WHITESPACE("", false);

        private final String pattern;
        private final Boolean hasData;

        private Type (String pat, Boolean dataOrNot) {
            pattern = pat;
            hasData = dataOrNot;
        }

        public String getPattern() {
            return pattern;
        }

        public Boolean gotData() {
            return hasData;
        }

    }

    private final Type tokenType;
    private final Optional<String> data;
    private static Map<Builder, Token> tokenMap = new HashMap<>();

    private Token (Type type, Optional<String> ancillaryData) {
        tokenType = type;
        data = ancillaryData;
    }

    public Type getTokenType() {
        return tokenType;
    }

    public Optional<String> getData() {
        if (data.isPresent())
            return data;
        else
            return null;
    }

    private static class Builder {
        private final Type tokenType;
        private final Optional<String> ancillaryData;

        Builder(Type type, Optional<String> data) {
            tokenType = type;
            ancillaryData = data;
        }

        private Token build() {
            Token newToken = new Token(tokenType, ancillaryData);
            return newToken;
        }

        @Override
        public boolean equals(Object arg0) {
            // some code
            return false;
        }

        @Override
        public int hashCode() {
            // some code
            return 1;
        }
    }

    public static Token of(Type type, String data) {
        if ()
    }

    @Override
    public boolean equals(Object arg0) {
        // some code
        return false;
    }

    @Override
    public int hashCode() {
        // some code
        return 1;
    }

    @Override
    public String toString() {
        // some code
        return "Hahaha";
    }

    public static void main(String[] args) {

    }
}
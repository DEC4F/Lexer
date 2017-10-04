import java.util.Optional;

public final class ParserException extends Exception{
    static final long serialVersionUID = 293L;

    public enum ErrorCode {
        TOKEN_EXPECTED,
        INVALID_TOKEN,
        TRAILING_INPUT,
        AND_EXPECTED,
        ID_EXPECTED,
        OPEN_EXPECTED,
        CLOSE_EXPECTED;
    }

    private final ErrorCode errorCode;
    private final int location;

    /**
     * a constructor that initializes error code and location
     * @param token is the locational token of the location
     * @param errorCode is the error code
     */
    ParserException(LocationalToken token, ErrorCode errorCode){
        this.errorCode = errorCode;
        location = token.getLocation();
    }

    /**
     * a constructor that initializes error code and sets location to -1
     * @param errorCode is the error code
     */
    ParserException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        location = -1;
    }

    /**
     * error code getter
     * @return error code
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    /**
     * location getter
     * @return location
     */
    public int getLocation() {
        return location;
    }

    /**
     * checks if the token is present
     * @param token is the token being verified
     * @throws ParserException if the token is not present
     */
    public static void verify(Optional<LocationalToken> token) throws ParserException {
        if (!token.isPresent())
            throw new ParserException(ErrorCode.TOKEN_EXPECTED);
    }

    public final static void verify (Token.Type expectedType, LocationalToken token) throws ParserException {
        if (token.getTokenType() != expectedType) {
            throw new ParserException(expectedType.getErrorCode());
        }
    }

    /**
     * checks if the token is present
     * @param token is the token being verified
     * @throws ParserException if the token is present
     */
    public static void verifyEnd(Optional<LocationalToken> token) throws ParserException {
        if (token.isPresent())
            throw new ParserException(ErrorCode.TRAILING_INPUT);
    }

    /**
     * turns the parser exception to a string
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return "ParserException{" +
                "serialVersionUID=" + serialVersionUID +
                ", errorCode=" + errorCode +
                ", location=" + location +
                '}';
    }
}

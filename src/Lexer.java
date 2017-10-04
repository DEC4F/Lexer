import groovy.sql.SqlWhereVisitor;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Stanley Tian on 9/19/2017.
 */
public class Lexer {

    private final Matcher matcher;
    private static Pattern tokenPatterns;
    // initializes tokenPatterns with named capturing group in static block
    static {
        StringBuilder finalPattern = new StringBuilder();
        for (Token.Type type : Token.Type.values()) {
            finalPattern.append(String.format("|(?<%s>%s)", type.name(), type.getPattern()));
        }
        tokenPatterns = Pattern.compile(finalPattern.substring(1));
    }

    /**
     * a constructor that initializes matcher
     * @param input is the string input to be examined
     */
    public Lexer(String input) {
        matcher = tokenPatterns.matcher(input);
    }

    /**
     * checks if the next input sub-sequence is an instance of tokenPatterns
     * @return true if it is, false if not
     */
    public Boolean hasNext() {
        if(matcher.find()) {
            matcher.region(matcher.start(), matcher.regionEnd());
            return true;
        }
        return false;
    }

    /**
     * private helper method to lower complexity of next() and validNext()
     * @param type is the type that found matched to string input
     * @return locational token of given type and data
     */
    private LocationalToken newToken (Token.Type type) {
        if (type.hasDataOrNot())
            return LocationalToken.setNewLocToken(type, matcher.group(), matcher.end());
        else
            return LocationalToken.setNewLocToken(type, null, matcher.end());
    }

    /**
     * parse the input string and return the locational token found one at a time
     * @return the locational token found
     * @throws ParserException when no more token is present
     */
    public LocationalToken next() throws ParserException {
        while (hasNext()) {
            matcher.find();
            for (Token.Type type : Token.Type.values()) {
                if (matcher.group().matches(type.getPattern()))
                    return newToken(type);
            }
        }
        throw new ParserException(ParserException.ErrorCode.TOKEN_EXPECTED);
    }

    /**
     * finds locational token of valid types and throws exception if token of invalid types is found
     * @param validTypes are the types allowed
     * @param invalidTypes are the types not allowed
     * @return locational token of valid types
     * @throws ParserException when there's an invalid type showed up in the input
     */
    public Optional<LocationalToken> nextValid (Set <Token.Type> validTypes, Set <Token.Type> invalidTypes) throws ParserException {
        while (hasNext()) {
            LocationalToken token = next();
            if (invalidTypes.contains(token.getTokenType()))
                throw new ParserException(ParserException.ErrorCode.INVALID_TOKEN);
            else if (validTypes.contains(token.getTokenType()))
                return Optional.of(token);
            else {
                // silently ignore others
            }
        }
        return Optional.empty();
    }
}
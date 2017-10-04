import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Stanley Tian on 9/23/2017.
 */
public final class DisjunctiveLexer {

    // a set of valid types
    public static final Set<Token.Type> validTypes = Stream.of(Token.Type.AND, Token.Type.ID, Token.Type.NOT, Token.Type.OPEN, Token.Type.CLOSE).collect(Collectors.toSet());
    // a set of invalid types
    public static final Set<Token.Type> invalidTypes = Stream.of(Token.Type.OR, Token.Type.NUMBER, Token.Type.BINARYOP).collect(Collectors.toSet());
    // a private lexer to pass token returned in nextValid()
    private Lexer lexer;

    /**
     * a constructor that sets up the lexer
     * @param input is the string input to be processed
     */
    DisjunctiveLexer(String input) {
        lexer = new Lexer(input);
    }

    /**
     * return the next valid locational token
     */
    public Optional<LocationalToken> nextValid() {
        Optional<LocationalToken> token = Optional.empty();
        try {
            token = lexer.nextValid(validTypes, invalidTypes);
        } catch (ParserException e1) {
            System.out.println("Error! " + e1);
        }
        try {
            // verifies that all token being output will be non empty
            ParserException.verify(token);
        }catch (ParserException e2) {
            System.out.println("Error! " + e2);
        }
        return token;
    }
}

import java.util.Optional;

/**
 * Created by Stanley Tian on 9/10/2017.
 */
public class LocationalToken {
    private final Token token;
    private final int location;

    private LocationalToken(Token token, int location){
        this.token = token;
        this.location = location;
    }

    /**
     * getter of the type of the locational token
     * @return the type of the token
     */
    public Token.Type getTokenType() {
        return token.getTokenType();
    }

    /**
     * getter of the data of the locational token
     * @return the data of the token
     */
    public Optional<String> getTokenData() {
        return token.getData();
    }

    /**
     * getter of the location of the locational token
     * @return the location of the token
     */
    public int getLocation(){
        return location;
    }

    @Override
    public String toString() {
        return "LocationalToken{" +
                "type = " + token.getTokenType() +
                ", data = " + token.getData() +
                ", location=" + getLocation() +
                '}';
    }

    /**
     * create a new locational token
     * @param type is the type of the locational token
     * @param data is the data of the locational token
     * @param location is the location of the locational token
     * @return a new locational token
     */
    public static LocationalToken setNewLocToken(Token.Type type, String data, int location) {
        LocationalToken newLocTok = new LocationalToken(Token.of(type, data), location);
        return newLocTok;
    }
}

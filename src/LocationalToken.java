/**
 * Created by Dr.Black Jack on 9/11/2017.
 */
public class LocationalToken {
    private final Token locationalToken;
    private final int location;

    private LocationalToken(Token token, int loc){
        locationalToken = token;
        location = loc;
    }

    public Token getLocationalToken(){
        return locationalToken;
    }

    public int getLocation(){
        return location;
    }
}

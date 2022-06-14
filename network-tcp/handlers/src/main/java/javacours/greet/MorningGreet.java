package javacours.greet;

import javacours.net.Greetable;

public class MorningGreet extends Greetable {
    @Override
    public String buildResponse(String userName) {
        return "GoogMorning, " + userName;
    }
}

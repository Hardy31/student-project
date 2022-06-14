package edu.javacours.greet;

import edu.javacours.net.Greetable;

public class MorningGreet extends Greetable {
    @Override
    public String buildResponse(String userName) {
        return "GoogMorning, " + userName;
    }
}

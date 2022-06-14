package edu.javacours.greet;

import edu.javacours.net.Greetable;

public class DayGreet extends Greetable {
    @Override
    public String buildResponse(String userName) {
        return "GoogDay, " + userName;
    }
}


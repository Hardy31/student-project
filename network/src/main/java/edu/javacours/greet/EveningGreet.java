package edu.javacours.greet;

import edu.javacours.net.Greetable;

public class EveningGreet extends Greetable {
    @Override
    public String buildResponse(String userName) {
        return "GoogEvening, " + userName;
    }
}

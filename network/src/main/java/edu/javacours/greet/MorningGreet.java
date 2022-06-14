package main.java.edu.javacours.greet;

import main.java.edu.javacours.net.Greetable;

public class MorningGreet extends Greetable {
    @Override
    public String buildResponse(String userName) {
        return "GoogMorning, " + userName;
    }
}

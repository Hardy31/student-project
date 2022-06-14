package javacours.greet;

import javacours.net.Greetable;

public class EveningGreet extends Greetable {
    @Override
    public String buildResponse(String userName) {
        return "GoogEvening, " + userName;
    }
}

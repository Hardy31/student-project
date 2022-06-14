package javacours.greet;

import javacours.net.Greetable;

public class HelloGreet extends Greetable {
    @Override
    public String buildResponse(String userName) {
        return "Hello, " + userName;
    }
}

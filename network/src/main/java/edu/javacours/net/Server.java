package main.java.edu.javacours.net;

import main.java.edu.javacours.net.Greetable;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Server {
    public static void main(String[] args) throws IOException {
        Integer port = 25225;
        ServerSocket serverSocket = new ServerSocket(port);
        Map<String, Greetable> handlers = loadHandlers();


        System.out.println( "Server is started");
        while (true){
            Socket client = serverSocket.accept();
            SimpeleServer  SimpeleServer = new SimpeleServer(client, handlers);
            SimpeleServer.start();
        }
    }

    private static Map<String, Greetable> loadHandlers() {
        Map<String,Greetable> result = new HashMap<>();

//        server.properties network/src/main/java/resources/server.properties

        System.out.println( "Server.loadHandlers  is started");
        try(InputStream is = Server.class.getClassLoader().getResourceAsStream("main/java/resources/server.properties");){
            Properties properties = new Properties();

            System.out.println( properties.getProperty("EVENIHG"));
            properties.load(is);
        for (Object comand: properties.keySet()) {
                String key = comand.toString();
                String className = properties.getProperty(key);
                Class<Greetable> cl = (Class<Greetable>) Class.forName(className);
//                Constructor cst = cl.getConstructor();
//                Greetable handler = cst.newInstance();
                Greetable handler = cl.getConstructor().newInstance() ;
                result.put(key, handler);
            }
        }catch (IOException ex){
            ex.printStackTrace();
            throw  new RuntimeException(ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


//                try(InputStream is = Server.class.getClassLoader().getResourceAsStream("edu/javacourse/resources/server.properties");){
//                network/src/main/java/resources/server.properties
//
//                properties.load(is);
//            }catch (IOException ex){
//                ex.printStackTrace();
//                throw  new RuntimeException(ex);
//            }

        return result;
    }

}

class SimpeleServer extends Thread{
    private Socket clientSocket;
    private Map<String, Greetable> handlers;

    public SimpeleServer ( Socket clientSoc, Map<String, Greetable> handlers){

        this.clientSocket = clientSoc;
        this.handlers = handlers;
    }
    @Override
    public void run(){
        handleRequest(clientSocket);
    }

    private  void handleRequest(Socket client) {
//        System.out.println( "ServerSocket connect Client");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));





            String incomingMessage = br.readLine();
            System.out.println( "ServerSocket read : " + incomingMessage);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace(System.out);
            }

            String[] words = incomingMessage.split("\\s+");
            String command = words[0];
            String userName = words[1] + " " + words[2];
//            sb.append(userName);
            System.out.println( "command "+command);
            System.out.println( "userName "+userName);

            String response = buildResponse(command, userName);

            bw.write(response);
            bw.newLine();
            bw.flush();
            br.close();
            bw.close();
            client.close();
            System.out.println( "ServerSocket "+userName+" close");
        } catch ( IOException ioE) {
            ioE.printStackTrace(System.out);
        }
    }

    private String buildResponse(String command, String userName){
//        switch (command){
//            case "HELLO" :                 return "HELLO "+ userName;
//            case "MORNING" :                 return "Good morning "+ userName;
//            case "DAY" :                 return "Good day  "+ userName;
//            case "EVENIHG" :                 return "Good evening "+ userName;
//            default:                    return "Hi "+ userName;
//        }
        Greetable handler = handlers.get(command);
        if (handler != null){
            return  handler.buildResponse(userName);
        }
        return "Hi "+ userName;
    }
}

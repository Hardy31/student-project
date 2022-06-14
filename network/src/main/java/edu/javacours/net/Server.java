
        package edu.javacours.net;
        import edu.javacours.greet.EveningGreet;
        import edu.javacours.greet.MorningGreet;

        import java.io.*;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.util.HashMap;
        import java.util.Map;

        public class Server {
    public static void main(String[] args) throws IOException {
        Integer port = 25225;
        ServerSocket serverSocket = new ServerSocket(port);

        Map<String, Greetable> handlers = loadHandlers();



        System.out.println( "Server is started");
        while (true){
            Socket client = serverSocket.accept();
            SimpeleServer  SimpeleServer = new SimpeleServer(client, handlers );
            SimpeleServer.start();
        }
    }




            private static Map<String, Greetable> loadHandlers() {
                Map<String,Greetable> result = new HashMap<>();

                result.put("MORNING", new MorningGreet());
                result.put("EVENIHG", new EveningGreet());
                

                System.out.println( "Server.loadHandlers  is started");
//                try(InputStream is = Server.class.getClassLoader().getResourceAsStream("main/java/resources/server.properties");){
//                    Properties properties = new Properties();
//
//                    System.out.println( properties.getProperty("EVENIHG"));
//                    properties.load(is);
//                    for (Object comand: properties.keySet()) {
//                        String key = comand.toString();
//                        String className = properties.getProperty(key);
//                        Class<Greetable> cl = (Class<Greetable>) Class.forName(className);
//                        Greetable handler = cl.getConstructor().newInstance() ;
//                        result.put(key, handler);
//                    }
//                }catch (IOException ex){
//                    ex.printStackTrace();
//                    throw  new RuntimeException(ex);
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                } catch (NoSuchMethodException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                }

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


            bw.write(userName.toString());
            bw.write(response);
            bw.newLine();
            bw.flush();
            br.close();
            bw.close();
            client.close();
            System.out.println( "ServerSocket "+userName+"close");
        } catch ( IOException ioE) {
            ioE.printStackTrace(System.out);
        }
    }

    private String buildResponse(String command, String userName){
        Greetable handler = handlers.get(command);
        if (handler != null){
            return  handler.buildResponse(userName);
        }
        return "Hi "+ userName;
    }



}
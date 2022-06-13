package edu.javacours.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        Integer port = 25225;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println( "Server is started");
        while (true){
            Socket client = serverSocket.accept();
            SimpeleServer  SimpeleServer = new SimpeleServer(client);
            SimpeleServer.start();
        }
    }

}

class SimpeleServer extends Thread{
    private Socket clientSocket;

    public SimpeleServer ( Socket clientSoc){
        this.clientSocket = clientSoc;
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



            bw.write(userName.toString());
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


}

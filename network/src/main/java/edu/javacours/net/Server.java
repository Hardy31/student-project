package edu.javacours.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        Integer port = 25225;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println( "Server is started1");
        while (true){
            Socket client = serverSocket.accept();
            handleRequest(client);
        }
    }
    //handler переводится как обработчик
    private static void handleRequest(Socket client) throws IOException {
        System.out.println( "ServerSocket connect Client");
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        StringBuilder sb = new StringBuilder("Hello, ");
        String userName = br.readLine();
        System.out.println( "ServerSocket read : " + userName);
        sb.append(userName);
        bw.write(sb.toString());
        bw.newLine();
        bw.flush();
        br.close();
        bw.close();
        client.close();
        System.out.println( "ServerSocket close");
    }
}

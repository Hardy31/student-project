package edu.javacours.net;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class Clients {



    public static void main(String[] args) throws IOException {
        for(int i = 0; i<10; i++){
            //        sendRequest();
            SimpleClient sc = new SimpleClient(i);
            sc.start();
        }
    }

}
class  SimpleClient extends Thread {

    private int cmdNumber;
    private final static  String[] COMMANDS = {"Hello", "MORNING", "DAY", "EVENIHG"};

    public SimpleClient (int cmdNumber){
        this.cmdNumber = cmdNumber;
    }
    @Override
    public void run() {
        try {
            LocalDateTime start = LocalDateTime.now();
//            System.out.println( "SimpleClient "+cmdNumber+" отправил запрос в " + start);
            Socket socket = new Socket("127.0.0.1", 25225);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String command = COMMANDS[cmdNumber%COMMANDS.length];

            String name = command + " Alex " + cmdNumber ;   // Сообщение отправляемое серверу
            bw.write(name);
            bw.newLine();
            bw.flush();
            System.out.println( "SimpleClient "+cmdNumber+" отправил запрос в " + start);


            String ansver = br.readLine();
            System.out.println("Ответ Сервера : " + ansver);    //Сообщение получаемое от сервера

            br.close();
            bw.close();
            socket.close();
            LocalDateTime finish = LocalDateTime.now();

            System.out.println( "SimpleClient "+cmdNumber+" получил ответ от Сервера в "  + finish  );
        } catch (IOException ioE) {
            ioE.printStackTrace(System.out);
        }

    }
}

package edu.javacours.net;

import java.io.*;
import java.net.Socket;

public class Clients {
    public static void main(String[] args) throws IOException {
        for(int i = 0; i<10; i++){
            //        sendRequest();
            SimpleClient sc = new SimpleClient(i);
            sc.start();

        }

        
    }

//    private static void sendRequest() throws IOException {
//        Socket socket = new Socket("127.0.0.1", 25225);
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//
//        String name = "Alex343";
//        bw.write(name);
//        bw.newLine();
//        bw.flush();
//
//
//        String  ansver = br.readLine();
//        System.out.println("Client got ansver : " + ansver);
//
//        br.close();
//        bw.close();
//        socket.close();
//    }

}
class  SimpleClient extends Thread {

    int num;
    public SimpleClient (int i){
        num = i;
    }
    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", 25225);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String name = "Alex " + this.num +  " Thread";
            bw.write(name);
            bw.newLine();
            bw.flush();


            String ansver = br.readLine();
            System.out.println("Client got ansver : " + ansver);

            br.close();
            bw.close();
            socket.close();
        } catch (IOException ioE) {
            ioE.printStackTrace(System.out);
        }

    }
}

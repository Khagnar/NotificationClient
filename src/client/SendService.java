package client;

import java.io.*;
import java.net.Socket;

public class SendService {

    private Socket socket;
    private DataOutputStream toServer;
    private static final int PORT = 5959;

    SendService(){
        try {
            socket = new Socket("localhost", PORT);
        }
        catch (IOException e) {
            System.err.println("Host is unknown");
            e.printStackTrace();
        }
    }

    void send(String subject, String message, String email, int year, int month, int day){
        try {
            try {
                toServer = new DataOutputStream(socket.getOutputStream());
                toServer.writeBytes(subject + "\n" +message  + "\n" +email + "\n" + year + "\n" + month + "\n" + day);

            }
            finally {
                toServer.close();
            }
        }
        catch (IOException e) {
            System.err.println("Can't send message");
            e.printStackTrace();
        }
    }
}

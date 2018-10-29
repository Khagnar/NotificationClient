package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.GregorianCalendar;

public class SendService {

    private Socket socket;
    private DataOutputStream toServer;

    public SendService(int port){
        try {
            socket = new Socket("localhost", port);
        }
        catch (IOException e) {
            System.err.println("Host is unknown");
            e.printStackTrace();
        }
    }

    public void send(Time enterTime){
        GregorianCalendar time = new GregorianCalendar();
        readTime(time, enterTime);
        try {
            sendNotification("1", "CHECK-1", time, "http", "http://yandex.ru");
            sendNotification("2", "CHECK-2", time, "mail", "a@gmail.com");
        }
        catch (Exception e) {
            System.err.println("Can't send message");
            e.printStackTrace();
        }
    }

    private void readTime(GregorianCalendar time, Time enterTime) {
        time.set(enterTime.getYear(), enterTime.getMonth(), enterTime.getDay(), enterTime.getHour(), enterTime.getMinutes());
    }

    private void sendNotification(String external_id, String message, GregorianCalendar time, String notification_type, String extra_params) throws Exception {
        toServer = new DataOutputStream(socket.getOutputStream());
        toServer.writeBytes(external_id + "\n" + message + "\n" + time.getTimeInMillis() + "\n" + notification_type + "\n" + extra_params + "\n");
    }
}

package Client;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        String serverAddress = "localhost";
        int port = 5000;

        try {
            Socket socket = new Socket(serverAddress, port);
            System.out.println("Connected to chat server");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Thread to read messages
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (Exception e) {
                    System.out.println("Disconnected from server");
                }
            }).start();

            Scanner sc = new Scanner(System.in);
            while (true) {
                String message = sc.nextLine();
                out.println(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
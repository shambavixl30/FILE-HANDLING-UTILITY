package ChatApplication;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatApplication {

    static Vector<PrintWriter> clients = new Vector<>();

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter mode (server/client): ");
        String mode = sc.nextLine();

        if (mode.equalsIgnoreCase("server")) {
            startServer();
        } else if (mode.equalsIgnoreCase("client")) {
            startClient();
        } else {
            System.out.println("Invalid option");
        }
    }

    // ---------------- SERVER ----------------
    static void startServer() {
        int port = 5000;
        System.out.println("Server started...");
        System.out.println("Waiting for clients...");

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                clients.add(out);

                new Thread(() -> handleClient(socket, out)).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void handleClient(Socket socket, PrintWriter out) {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println("Received: " + msg);
                broadcast(msg);
            }
        } catch (Exception e) {
            System.out.println("Client disconnected");
        }
    }

    static void broadcast(String msg) {
        for (PrintWriter pw : clients) {
            pw.println(msg);
        }
    }

    // ---------------- CLIENT ----------------
    static void startClient() {
        String host = "localhost";
        int port = 5000;

        try {
            Socket socket = new Socket(host, port);
            System.out.println("Connected to server");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Read messages from server
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (Exception e) {
                    System.out.println("Disconnected");
                }
            }).start();

            // Send messages
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

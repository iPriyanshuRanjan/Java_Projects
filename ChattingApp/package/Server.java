import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    private ServerSocket server; // ServerSocket for accepting client connections
    private Socket socket; // Socket for communication with the connected client
    private BufferedReader br; // Reader for reading messages from the client
    private PrintWriter out; // Writer for sending messages to the client

    public Server() {
        try {
            server = new ServerSocket(7777); // Create a ServerSocket on port 7777 to accept client connections
            System.out.println("Server is ready to connect.");
            System.out.println("Waiting...");

            socket = server.accept(); // Accept a client connection and initialize input and output streams
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            startReading(); // Start a thread for reading messages
            startWriting(); // Start a thread for writing messages
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startReading() {
        Runnable r1 = () -> {
            System.out.println("Reading Started");
            try {
                while (true) {
                    String msg = br.readLine(); // Read a message from the client
                    if (msg == null || msg.equals("exit")) {
                        System.out.println("Client terminated the chat.");
                        break;
                    }
                    System.out.println("Client: " + msg);
                }
                socket.close(); // Close the socket when the chat ends
            } catch (IOException e) {
                // e.printStackTrace();
                System.out.println("Connection Closed.");
            }
        };
        new Thread(r1).start();
    }

    public void startWriting() {
        Runnable r2 = () -> {
            System.out.println("Writer Started.");
            try {

                while (!socket.isClosed()) {
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String content = br1.readLine(); // Read a message from the server's console input
                    out.println(content); // Send the message to the client
                    out.flush();
                    if (content.equals("exit")) {
                        socket.close();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        new Thread(r2).start();
    }

    public static void main(String[] args) {
        System.out.println("------Developer: Priyanshu Ranjan------");
        System.out.println("This is Server... Going to start Server");
        new Server();
    }
}

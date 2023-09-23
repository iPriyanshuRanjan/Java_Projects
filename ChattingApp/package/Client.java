import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class Client {
    private Socket socket; // Socket for connecting to the server
    private BufferedReader br; // Reader for reading messages from the server
    private PrintWriter out; // Writer for sending messages to the server

    public Client() {
        try {
            // Connect to the server running on localhost (127.0.0.1) on port 7777
            socket = new Socket("127.0.0.1", 7777);

            // Initialize input and output streams for communication
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            // Start separate threads for reading and writing messages
            startReading();
            startWriting();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Thread for reading messages from the server
    public void startReading() {
        Runnable r1 = () -> {
            System.out.println("Reading Started");
            try {
                while (true) {
                    String msg = br.readLine();
                    if (msg == null || msg.equals("exit")) {
                        System.out.println("Server terminated the chat.");
                        break;
                    }
                    System.out.println("Server: " + msg);
                }
                // Close the socket when the chat ends
                socket.close();
            } catch (IOException e) {
                // e.printStackTrace(); //this will prints Exception 
                System.out.println("Connection Closed.");
            }
        };
        new Thread(r1).start();
    }

    // Thread for writing messages to the server
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
        System.out.println("This is Client... Going to start Client");
        new Client();
    }
}

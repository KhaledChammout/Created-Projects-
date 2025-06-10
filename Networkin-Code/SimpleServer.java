package lab10;
import java.io.*;
import java.net.*;

public class SimpleServer {
    private int serverPort;
    private ServerSocket serverSocket;
    private Socket connection;
    private DataInputStream input;
    private DataOutputStream output;

    public SimpleServer() {
        this(1254);
    }

    public SimpleServer(int port) {
        this.serverPort = port;
    }

    public void runServer() {
        try {
            serverSocket = new ServerSocket(serverPort);
            System.out.println("Server is running on port " + serverPort);

            while (true) { 
                waitForConnection();
                getStream();
                processConnection();
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    private void waitForConnection() throws IOException {
        System.out.println("Waiting for client...");
        connection = serverSocket.accept();
        System.out.println("Client connected: " + connection.getInetAddress());
    }

    private void getStream() throws IOException {
        input = new DataInputStream(connection.getInputStream());
        output = new DataOutputStream(connection.getOutputStream());
    }

    private void processConnection() {
        try {
            output.writeUTF("Hello from Server! Type 'exit' to disconnect.");
            String message;

            while (true) {
                message = input.readUTF();
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }
                System.out.println("Client: " + message);
                output.writeUTF("Server received: " + message);
            }
        } catch (IOException e) {
            System.out.println("Connection lost.");
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() {
        try {
            if (input != null) input.close();
            if (output != null) output.close();
            if (connection != null) connection.close();
        } catch (IOException e) {
            System.err.println("Error closing connections: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new SimpleServer().runServer();
    }
}
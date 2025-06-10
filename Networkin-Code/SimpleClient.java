package lab10;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SimpleClient {
    private int serverPort; 
    private String hostServer;
    private Socket connection;
    private DataInputStream input;
    private DataOutputStream output;
    private Scanner scanner;

    public SimpleClient(String host) {
        this(host, 1254);
    }

    public SimpleClient(String host, int serverPort) {
        this.hostServer = host;
        this.serverPort = serverPort;
        this.scanner = new Scanner(System.in);
    }

    public void runClient() {
        try {
            connectToServer();
            getStream();
            processConnection();
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    private void connectToServer() throws IOException {
        System.out.println("Connecting to server " + hostServer + " on port " + serverPort);
        connection = new Socket(hostServer, serverPort);
    }

    private void getStream() throws IOException {
        input = new DataInputStream(connection.getInputStream());
        output = new DataOutputStream(connection.getOutputStream());
    }

    private void processConnection() {
        try {
            System.out.println("Message from server: " + input.readUTF());

            while (true) {
                System.out.print("Enter message: ");
                String message = scanner.nextLine();
                output.writeUTF(message); 

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Disconnecting from server...");
                    break;
                }

                String serverResponse = input.readUTF();
                System.out.println(serverResponse);
            }
        } catch (IOException e) {
            System.out.println("Server connection lost.");
        }
    }

    private void closeConnection() {
        try {
            if (input != null) input.close();
            if (output != null) output.close();
            if (connection != null) connection.close();
            scanner.close();
        } catch (IOException e) {
            System.err.println("Error closing connections: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new SimpleClient("localhost").runClient();
    }
}
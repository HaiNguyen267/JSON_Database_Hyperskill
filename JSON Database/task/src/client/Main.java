package client;

import com.beust.jcommander.JCommander;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {
        String jsonRequest = parseRequest(args);
        final String SERVER_ADDRESS = "127.0.0.1";
        final int PORT = 23456;

        try (
                Socket socket = new Socket(SERVER_ADDRESS, PORT);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {

            System.out.println("Client started!");
            output.writeUTF(jsonRequest);
            System.out.println("Sent: " + jsonRequest);
            System.out.println("Received: " + input.readUTF());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String parseRequest(String[] args) {
        Request request = new Request();
        JCommander.newBuilder()
                .addObject(request)
                .build()
                .parse(args);

        if (request.getFileName() != null) {
            return readJsonRequestInFile(request.getFileName());
        } else {
            return request.toJsonString();
        }
    }

    private static String readJsonRequestInFile(String fileName) {
        try {
            String folder = "C:\\Users\\NKcomputer\\IdeaProjects\\JSON Database\\JSON Database\\task\\src\\client\\data\\";
            JsonObject jsonObject = new JsonParser().parse(new String(Files.readAllBytes(Paths.get(folder + fileName)))).getAsJsonObject();
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

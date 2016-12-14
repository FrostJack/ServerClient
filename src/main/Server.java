package main;

import java.io.*;
import java.net.*;

public class Server {

    public static final int Port = 4444;

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Server");
        BufferedReader in = null;
        PrintWriter    out= null;

        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(Port);

        }  catch (IOException e){
            System.out.println("Can't reach " + Port);
            System.exit(-1);
        }

        try {
            System.out.print("Waiting for a client...");
            socket = server.accept();
            System.out.println("Client connected:" + socket);
        } catch (IOException e) {
            System.out.println("Can't accept");
            System.exit(-1);
        }

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);

        String s = in.readLine();
        System.out.println(s);
        out.println("HTTP/1.1 OK");

        String ad = s.split(" ")[1].substring(1);

        BufferedReader read = new BufferedReader(new FileReader(ad));
        String text = read.readLine();
        while (text != null){
            System.out.println(text);
            out.println(text);
            text = read.readLine();
        }

        out.flush();
        read.close();
        socket.close();
        server.close();
    }
}

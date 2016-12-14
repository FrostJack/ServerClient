package main;

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws IOException{
        System.out.println("Welcome to Client");

        InetAddress address = InetAddress.getByName("localhost");
        Socket server = new Socket(address, Server.Port);


        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        PrintWriter out = new PrintWriter(server.getOutputStream(), true);

        out.println("GET /Index.htm /HTTP1.1");
        out.flush();

        String s = in.readLine();
        while (s  != null){
            System.out.println(s);
            s = in.readLine();
        }

        out.close();
        in.close();
        server.close();
    }
}

package com.jfspps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args){
        // some sockets have already been assigned a port number, so try to build an instance and handle error if thrown
        // In Java, ServerSockets wait for requests and Sockets generate requests
        try (ServerSocket serverSocket = new ServerSocket(5000)){

            // Socket is used by this server to connect to a client and establish I/O streams
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            // true to flush data automatically and ensure data is sent
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while(true){
                String echoString = input.readLine();
                if (echoString.equals("exit")){
                    break;
                }
                output.println("Echo from server: " + echoString);
            }

        } catch (IOException exception){
            System.out.println("Server exception thrown: " + exception.getMessage());
        }
    }
}

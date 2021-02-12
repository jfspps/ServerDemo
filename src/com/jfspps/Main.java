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
            // enable more client connections
            while(true){
                // connection terminated by EchoMessage with "exit"
                new EchoMessage(serverSocket.accept()).start();
            }
        } catch (IOException exception){
            System.out.println("Server exception thrown: " + exception.getMessage());
        }
    }
}

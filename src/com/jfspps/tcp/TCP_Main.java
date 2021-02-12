package com.jfspps.tcp;

import java.io.IOException;
import java.net.ServerSocket;

public class TCP_Main {

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

// TCP connections (ServerSocket) are slower but more reliable and check for dropped packets
// UDP connections (DatagramSocket) are faster though they allow datagrams ('UDP packets') to drop (used for VOIP or video streaming)
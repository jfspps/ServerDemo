package com.jfspps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// handles connections with a client on a individual ServerDemo thread
public class EchoMessage extends Thread{

    private Socket socket;

    public EchoMessage(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (true){
                String echoString = input.readLine();
                if (echoString.equals("exit")){
                    break;
                }

                output.println(echoString);
            }
        } catch (IOException e){
            System.out.println("Server error: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException exception) {
                System.out.println("Problem disconnecting from client: " + exception.getMessage());
            }
        }
    }
}

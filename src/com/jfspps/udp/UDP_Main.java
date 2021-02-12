package com.jfspps.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class UDP_Main {

    public static void main(String[] args){
        try {

            DatagramSocket socket = new DatagramSocket(5000);

            while(true){
                byte[] buffer = new byte[50];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                // this is blocking (nothing is returned to the client; cf. to TCP)
                socket.receive(packet);

                System.out.println("Packet received with text: " + new String(buffer));

                String returnString = "echo: " + new String(buffer);

                // resend the datagram back to the client with the same address and port (this is just for demo purposes,
                // as it's not common to return UDP datagrams as it occurs with TCP packets
                byte[] buffer2 = returnString.getBytes();
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buffer2, buffer2.length, address, port);
                socket.send(packet);
            }

        } catch (SocketException exception){
            System.out.println("Socket exception: " + exception.getMessage());
        } catch (IOException ioException){
            System.out.println("I_O exception: " + ioException.getMessage());
        }
    }
}

// TCP connections (ServerSocket) are slower but more reliable and check for dropped packets
// UDP connections (DatagramSocket) are faster though they allow datagrams ('UDP packets') to drop (used for VOIP or video streaming)
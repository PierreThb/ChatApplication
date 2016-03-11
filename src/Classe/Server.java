/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

import Gui.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Pierre
 */
public class Server {

    private static final ServerFrame sf = new ServerFrame();

    private final ArrayList<DataInputStream> clientSocket;
    private final ArrayList<DataOutputStream> outputStreams;
    private final WaitToConnection waiter;

    private final ServerSocket serverSocket;

    String pattern = "^me:([\\d\\D]+)$";
    Pattern r = Pattern.compile(pattern);

    public Server() throws IOException {
        serverSocket = new ServerSocket(5001);
        clientSocket = new ArrayList<>();
        outputStreams = new ArrayList<>();
        waiter = new WaitToConnection();
    }

    public void run() {
        System.out.println("**** Server ****");
        waiter.start();
        while (true) {
            synchronized (clientSocket) {
                for (DataInputStream din : clientSocket) {
                    try {
                        if (din.available() > 0) {
                            String msg = din.readUTF();
                            Matcher m = r.matcher(msg);
                            if (m.find()) {
                                System.out.println("New pseudo receive: " + m.group(1));
                            } else {
                                sendGlobalMessage(msg);
                                System.out.println("New message receive: " + msg);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }
    }

    public void sendGlobalMessage(String msg) throws IOException {
        synchronized (outputStreams) {
            for (DataOutputStream dout : outputStreams) {
                dout.writeUTF(msg);
                dout.flush();
            }
        }

    }

    public void sendPrivateMessage(String msg, int id) throws IOException {

    }

    public static void main(String[] args) {
        try {
            sf.setVisible(true);
            Server mserver = new Server();
            mserver.run();
        } catch (IOException ex) {
            System.out.println("Problem when creation or the object Server");
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class WaitToConnection extends Thread {

        public void run() {
            while (true) {
                try {
                    Socket client = serverSocket.accept();
                    DataInputStream in = new DataInputStream(client.getInputStream());
                    DataOutputStream out = new DataOutputStream(client.getOutputStream());
                    synchronized (outputStreams) {
                        outputStreams.add(out);
                    }
                    synchronized (clientSocket) {
                        clientSocket.add(in);
                    }
                    System.out.println("New connection");
                } catch (IOException ex) {
                    System.out.println("Exception in run() of class waitconnecction" + ex.getMessage());
                }
            }

        }
    }

}

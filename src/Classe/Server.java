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
    private final ArrayList<String> listClientName;
    private final WaitToConnection waiter;

    private final ServerSocket serverSocket;

    String pattern = "^me:([\\d\\D]+)$"; //me:clientName
    Pattern r = Pattern.compile(pattern);

    String patternPrivate = "^¤¤([^:]+):(.*)$"; //¤¤clientName:message
    Pattern patPrivate = Pattern.compile(patternPrivate);

    String patternAll = "^all:([\\d\\D]+)$"; //all:message
    Pattern patAll = Pattern.compile(patternAll);

    String patternLeave = "^¤leave¤:([\\d\\D]+)$"; //¤Leave¤:clientName
    Pattern patLeave = Pattern.compile(patternLeave);
    

    public Server() throws IOException {
        serverSocket = new ServerSocket(5000);
        clientSocket = new ArrayList<>();
        outputStreams = new ArrayList<>();
        listClientName = new ArrayList<>();
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
                            Matcher mPrivate = patPrivate.matcher(msg);
                            Matcher mAll = patAll.matcher(msg);
                            Matcher mLeave = patLeave.matcher(msg);
                            if (m.find()) {
                                System.out.println("New pseudo receive: " + m.group(1));
                                listClientName.add(m.group(1));
                                sf.setListClient(m.group(1));
                                sendGlobalMessage(listClientName.toString());
                            } else if (mAll.find()) {
                                System.out.println("New message for all receive: " + mAll.group(1));
                                sendGlobalMessage(mAll.group(1));
                                sf.setListMessage(mAll.group(1));
                            } else if (mPrivate.find()) {
                                System.out.println("New private message receive: " + mPrivate.group(2));
                                sendPrivateMessage(mPrivate.group(2), mPrivate.group(1));
                                sf.setListMessage(mPrivate.group(1));
                            } else if (mLeave.find()){
                                System.out.println("Client "+mLeave.group(1)+" left");
                                clientLeave(mLeave.group(1));
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

    public void sendPrivateMessage(String msg, String clientName) throws IOException {
        int id = listClientName.indexOf(clientName);
        synchronized (outputStreams) {
            DataOutputStream dout = outputStreams.get(id);
            dout.writeUTF(msg);
            dout.flush();
        }
    }
    
    public void clientLeave(String clientName) throws IOException{
        int id = listClientName.indexOf(clientName);
        synchronized(outputStreams){
            outputStreams.remove(id);
            listClientName.remove(id);
            sendGlobalMessage(listClientName.toString());
        }
    }

    public static void main(String[] args) {
        try {
            //sf.setVisible(true);
            Server mserver = new Server();
            mserver.run();
        } catch (IOException ex) {
            System.out.println("Problem when creation of the object Server" + ex.getMessage());
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

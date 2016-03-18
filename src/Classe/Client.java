/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Pierre
 */
public class Client {

    private Socket s;
    private String id;
    private String lastMessage;
    private DataOutputStream dout;
    private DataInputStream din;
    private boolean boolNewMessage = false;
    private boolean boolNewClient = false;
    private final ArrayList<String> listClientName;

    /* Constructor where we initialize the connection with the server and
    the id of the client (number or name?), also initialize the dout and din
    and send to the server the id of the client and start the class waitForMessage
     */
    public Client(String id) {
        listClientName = new ArrayList<>();
        try {
            Socket socket = new Socket("localhost", 5000);
            this.s = socket;
            this.id = id;
            dout = new DataOutputStream(s.getOutputStream());
            din = new DataInputStream(s.getInputStream());
            dout.writeUTF("me:" + this.id);
            dout.flush();
            new waitForMessage().start();
        } catch (IOException ex) {
            System.out.println("Exception in Client()" + ex.getMessage());
        }
    }

    /* setter */
    public void setListConnectedClient(String str) {
        synchronized (listClientName) {
            listClientName.clear();
            String[] arr = str.replaceAll("\\[", "").replaceAll("\\]", "").split(", ");
            listClientName.addAll(Arrays.asList(arr));
            System.out.println("listclientname: " + listClientName);
            setNewClientBool(true);
        }
    }

    /* getter */
    public ArrayList<String> getListClientName() {
        return listClientName;
    }

    /* Setter */
    public synchronized void setNewMessageBool(boolean bool) {
        this.boolNewMessage = bool;
    }

    /* Getter */
    public synchronized boolean getNewMessageBool() {
        return boolNewMessage;
    }

    public synchronized void setNewClientBool(boolean bool) {
        this.boolNewClient = bool;
    }

    public synchronized boolean getNewClientBool() {
        return boolNewClient;
    }

    /* Setter */
    public void setLastMessage(String message) {
        this.lastMessage = message;
        setNewMessageBool(true);
    }

    /* Getter */
    public String getLastMessage() {
        return lastMessage;
    }

    /* Function which send a mesage for all client to the server */
    public void sendNewMessage(String message) {
        try {
            dout.writeUTF("all:" + id + ": " + message);
        } catch (IOException ex) {
            System.out.println("Exception in sendNewMessage()" + ex.getMessage());
        }
    }

    /* Function which send a message for all client to the server */
    public void sendPrivateMessage(String message, String clientName) {
        try {
            dout.writeUTF("¤¤" + clientName + ":" + id + ": " + message);
        } catch (Exception e) {
            System.out.println("Exception in sendPrivateMessage()" + e.getMessage());
        }
    }

    /* Funtion which send a message saying he leaves the server */
    public void leave() {
        try {
            dout.writeUTF("¤leave¤:" + this.id);
        } catch (Exception e) {
            System.out.println("Exception in sendPrivateMessage()" + e.getMessage());
        }
    }

    /* Class which wait for a new message comming from the server 
    and set the boolean at true to display in the ClientFrame
     */
    class waitForMessage extends Thread {

        public void run() {
            String msg;
            boolean error = false;
            while (!error) {
                try {
                    if ((msg = din.readUTF()) != null) { //check if there is a data in din
                        if (msg.startsWith("[")) {
                            setListConnectedClient(msg);
                        } else {
                            System.out.println("receive:" + msg);
                            setLastMessage(msg);
                        }
                        msg = null;
                    }
                } catch (IOException ex) {
                    error = true;
                    System.out.println("Error, the server in closed");
                    //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}

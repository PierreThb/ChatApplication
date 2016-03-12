/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private boolean newMessage = false;

    /* Constructor where we initialize the connection with the server and
    the id of the client (number or name?), also initialize the dout and din
    and send to the server the id of the client and start the class waitForMessage
     */
    public Client(String id) {
        try {
            Socket socket = new Socket("localhost", 5001);
            this.s = socket;
            this.id = id;
            dout = new DataOutputStream(s.getOutputStream());
            din = new DataInputStream(s.getInputStream());
            dout.writeUTF("me:" + this.id);
            dout.flush();
            new waitForMessage().start();
        } catch (IOException ex) {
            System.out.println("Exception in Client()");
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* Setter */
    public void setNewMessageBool(boolean bool) {
        this.newMessage = bool;
    }

    /* Getter */
    public boolean getNewMessageBool() {
        return newMessage;
    }

    /* Setter */
    public void setLastMessage(String message) {
        this.lastMessage = message;
    }

    /* Getter */
    public String getLastMessage() {
        return lastMessage;
    }

    /* Function which send a mesage for all client to the server */
    public void sendNewMessage(String message) {
        try {
            dout.writeUTF("all:"+message);
        } catch (IOException ex) {
            System.out.println("Exception in sendNewMessage()");
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /* Function which send a message for all client to the server */
    public void sendPrivateMessage(String message, String clientName){
        try {
            dout.writeUTF("private:"+message);
        } catch (Exception e) {
            System.out.println("Exception in sendPrivateMessage()"+e.getMessage());
        }
    }

    /* Class which wait for a new message comming from the server 
    and set the boolean at true to display in the ClientFrame
     */
    class waitForMessage extends Thread {

        public void run() {
            while (true) {
                try {
                    if (din.available() > 0) { //check if there is a data in din
                        System.out.println("receive:" + din.readUTF());
                        setLastMessage(din.readUTF());
                        setNewMessageBool(true);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}

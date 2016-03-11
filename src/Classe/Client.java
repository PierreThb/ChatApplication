/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pierre
 */
public class Client {

    private Socket s;
    private int id;
    private String message;
    private DataOutputStream dout;
    private DataInputStream din;
    

    public Client(int id) {
        try {
            Socket socket = new Socket("localhost", 5001);
            this.s = socket;
            this.id = id;
            dout = new DataOutputStream(s.getOutputStream());
            din = new DataInputStream(s.getInputStream());
            dout.writeUTF("me:"+this.id);
            dout.flush();
            new waitForMessage().start();
        } catch (IOException ex) {
            System.out.println("Exception in Client()");
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getId() {
        return id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void getMessage(String msg) {
        
    }

    public void sendNewMessage(String message) {
        try {
            dout.writeUTF(message);
        } catch (IOException ex) {
            System.out.println("Exception in sendNewMessage()");
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class waitForMessage extends Thread{
        public void run(){
            while (true) {                
                try {
                    if(din.available()>0){
                        System.out.println("receive:"+din.readUTF());
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Classe.*;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.DefaultListModel;

/**
 *
 * @author Pierre
 */
public class ClientFrame extends javax.swing.JFrame {

    private String username = "";
    private String msg = "";
    private String privateMsg = "";
    private ArrayList<String> listclients;
    private Client client;
    DefaultListModel model = new DefaultListModel();

    /* class which extends of Thread class and wait for a new message to display in the GUI */
    class waitForMessage extends Thread {

        public void run() {
            while (true) {
                if (client.getNewMessageBool()) {
                    displayMessage();
                    client.setNewMessageBool(false);
                }
            }
        }
    }

    /* class which extends of Thread class and wait for a new client to display in the list in the GUI */
    class waitForNewClient extends Thread {

        public void run() {
            while (true) {
                if (client.getNewClientBool()) {
                    setListClientConnected();
                    client.setNewClientBool(false);
                }
            }
        }
    }

    /**
     * Creates new form ClientFrame
     */
    public ClientFrame() {
        initComponents();
        field_message.setEditable(false);
        button_message.setEnabled(false);
        button_privatemessage.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        button_username = new javax.swing.JButton();
        field_username = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        anonymousLogin = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaMessages = new javax.swing.JTextArea();
        button_message = new javax.swing.JButton();
        button_privatemessage = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listclientconnected = new javax.swing.JList<>();
        button_leave = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        field_message = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Client login"));

        button_username.setBackground(new java.awt.Color(242, 95, 64));
        button_username.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sign.png"))); // NOI18N
        button_username.setText("Ok");
        button_username.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        button_username.setIconTextGap(10);
        button_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_usernameActionPerformed(evt);
            }
        });

        field_username.setBackground(new java.awt.Color(248, 215, 109));
        field_username.setForeground(new java.awt.Color(0, 0, 0));
        field_username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                field_usernameKeyPressed(evt);
            }
        });

        jLabel1.setText("User name :");

        anonymousLogin.setBackground(new java.awt.Color(242, 95, 64));
        anonymousLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sign.png"))); // NOI18N
        anonymousLogin.setText("Anonymous");
        anonymousLogin.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        anonymousLogin.setIconTextGap(10);
        anonymousLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anonymousLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(button_username)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(anonymousLogin))
                    .addComponent(field_username))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(field_username, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_username, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anonymousLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {button_username, field_username});

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Message"));

        areaMessages.setEditable(false);
        areaMessages.setBackground(new java.awt.Color(248, 215, 109));
        areaMessages.setColumns(20);
        areaMessages.setForeground(new java.awt.Color(0, 0, 0));
        areaMessages.setRows(5);
        jScrollPane1.setViewportView(areaMessages);

        button_message.setBackground(new java.awt.Color(242, 95, 64));
        button_message.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/symbol (1).png"))); // NOI18N
        button_message.setText("Send All");
        button_message.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        button_message.setIconTextGap(10);
        button_message.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_messageMouseClicked(evt);
            }
        });

        button_privatemessage.setBackground(new java.awt.Color(242, 95, 64));
        button_privatemessage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/security.png"))); // NOI18N
        button_privatemessage.setText("Private message");
        button_privatemessage.setToolTipText("");
        button_privatemessage.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        button_privatemessage.setIconTextGap(10);
        button_privatemessage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_privatemessageMouseClicked(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lobby"));

        listclientconnected.setBackground(new java.awt.Color(248, 215, 109));
        listclientconnected.setForeground(new java.awt.Color(0, 0, 0));
        listclientconnected.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listclientconnectedMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listclientconnected);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        button_leave.setBackground(new java.awt.Color(242, 95, 64));
        button_leave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/circle.png"))); // NOI18N
        button_leave.setText("Leave");
        button_leave.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        button_leave.setIconTextGap(10);
        button_leave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_leaveMouseClicked(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Type Field"));

        field_message.setBackground(new java.awt.Color(248, 215, 109));
        field_message.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(field_message)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(field_message, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(button_message, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                        .addComponent(button_leave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(button_privatemessage, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button_leave))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(button_message, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button_privatemessage))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/chatApp.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/people.png"))); // NOI18N
        jLabel3.setText("Client");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel3.setIconTextGap(10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel3)
                        .addGap(39, 39, 39)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /* if click on button to send a message for all */
    private void button_messageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_messageMouseClicked
        if (!field_message.getText().equals("")) {
            setAndSendMessage();
        }
    }//GEN-LAST:event_button_messageMouseClicked

    /* validate field username when press enter */
    private void field_usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field_usernameKeyPressed
        if (!field_username.getText().equals("") && (evt.getKeyCode() == KeyEvent.VK_ENTER)) {
            setUserName();
        }
    }//GEN-LAST:event_field_usernameKeyPressed

    /* if click on Button to send a private message */
    private void button_privatemessageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_privatemessageMouseClicked
        if (!field_message.getText().equals("")) {
            setAndSendPrivateMessage();
        }
    }//GEN-LAST:event_button_privatemessageMouseClicked

    /* if click on button to "Ok" to set the username */
    private void button_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_usernameActionPerformed
        // TODO add your handling code here:
        if (!field_username.getText().equals("")) {
            setUserName();
        }
    }//GEN-LAST:event_button_usernameActionPerformed

    /* if click on button for an anonymous connection */
    private void anonymousLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anonymousLoginActionPerformed
        // TODO add your handling code here:
        field_message.setEditable(true);
        field_username.setEditable(false);
        button_message.setEnabled(true);
        button_username.setEnabled(false);
        anonymousLogin.setEnabled(false);
        String anon = "anon";
        Random generator = new Random();
        int i = generator.nextInt(999) + 1; // generate a random number for the connection 
        String is = String.valueOf(i);
        anon = anon.concat(is);
        username = anon;
        field_username.setText(anon);
        client = new Client(anon);
        new waitForMessage().start();
        new waitForNewClient().start();
        areaMessages.setText("Welcome " + username + " to the Chat App\n");
    }//GEN-LAST:event_anonymousLoginActionPerformed

    /* if click on the button to leave the server */
    private void button_leaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_leaveMouseClicked
        // TODO add your handling code here:
        client.leave(); // call the function from Client class
        System.exit(0);
    }//GEN-LAST:event_button_leaveMouseClicked

    /* if select a client in the list of connected client, the button to set a private message is enable */
    private void listclientconnectedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listclientconnectedMouseClicked
        // TODO add your handling code here:
        button_privatemessage.setEnabled(true);
    }//GEN-LAST:event_listclientconnectedMouseClicked

    /* set the user name, create the new object Client and start thread waitForMessage */
    private void setUserName() {
        field_message.setEditable(true);
        field_username.setEditable(false);
        button_message.setEnabled(true);
        button_username.setEnabled(false);
        anonymousLogin.setEnabled(false);
        username = field_username.getText();
        System.out.println("UserName: " + username);
        client = new Client(username);
        new waitForMessage().start();
        new waitForNewClient().start();
        areaMessages.setText("Welcome " + username + " to the Chat App\n");
    }

    /* to diplay receive message in the GUI */
    private void displayMessage() {
        areaMessages.setText(areaMessages.getText() + client.getLastMessage() + "\n");
    }

    /* set private message and send it by calling the function from the Client class */
    private void setAndSendPrivateMessage() {
        privateMsg = field_message.getText();
        client.sendPrivateMessage(privateMsg, listclientconnected.getSelectedValue());
        field_message.setText("");
        button_privatemessage.setEnabled(false);
    }

    /* set normal message and send it by calling the function from the Client class */
    private void setAndSendMessage() {
        msg = field_message.getText();
        client.sendNewMessage(msg);
        field_message.setText("");
    }

    /* to set and display the list of connected client in the GUI */
    private void setListClientConnected() {
        model.clear();
        listclients = client.getListClientName();
        for (String str : listclients) {
            model.addElement(str);
        }
        listclientconnected.setModel(model);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anonymousLogin;
    private javax.swing.JTextArea areaMessages;
    private javax.swing.JButton button_leave;
    private javax.swing.JButton button_message;
    private javax.swing.JButton button_privatemessage;
    private javax.swing.JButton button_username;
    private javax.swing.JTextField field_message;
    private javax.swing.JTextField field_username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listclientconnected;
    // End of variables declaration//GEN-END:variables
}

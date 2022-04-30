package system;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

public class event extends gui{

    int statusPort=1234 ,userPort=1235,passwordPort=1236,msgPort=1237,receiverPort=1238 ,newUserPort=1239,newPassPort=1240,senderPort=1241,mailSenderPort=1242,mailReceiverPort=1243,mailMsgPort=1244,mailToServerPort=1245,counter=0;
    Socket statusSocket , usernameSocket ,passwordSocket , newUsernameSocket, newPasswordSocket , msgSocket , receiverSocket,senderSocket,mailSenderSocket,mailSocket,mailReceiverSocket;
    DataOutputStream writeUsername , writePassword ,writeReceiver, writeMessage ,writeNewUsername,writeNewPassword;
    DataInputStream readSignInResponse,readSignUpResponse,readReceiver,readSender,readEmail;
    ArrayList mailBox,senders,receivers;
    static String LogInUsername,serverAddress="192.168.1.10";

    event(){



        signup_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signin_frame.setVisible(false);
                signup_frame.setVisible(true);
            }
        });


        register_btn_signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sending data to the server to store it into server database:-
                String newUser=username_txt_signup.getText();
                String newPass=new String(Password_txt_signup.getPassword());

                username_txt_signup.setText("");
                Password_txt_signup.setText("");

                try{
                    //sending data to the server to be handled:-
                    newUsernameSocket=new Socket(InetAddress.getByName(serverAddress),newUserPort);
                    writeNewUsername=new DataOutputStream(newUsernameSocket.getOutputStream());
                    writeNewUsername.writeUTF(newUser);
                    writeNewUsername.flush();
                    writeNewUsername.close();


                    newPasswordSocket=new Socket(InetAddress.getByName(serverAddress),newPassPort);
                    writeNewPassword=new DataOutputStream(newPasswordSocket.getOutputStream());
                    writeNewPassword.writeUTF(newPass);
                    writeNewPassword.flush();
                    writeNewPassword.close();


                    //getting status from the server:-
                    statusSocket=new Socket(InetAddress.getByName(serverAddress),statusPort);
                    readSignUpResponse=new DataInputStream(statusSocket.getInputStream());
                    String response= readSignUpResponse.readUTF();

                    if(response.equals("Registered:yes!")){
                        signin_frame.setVisible(true);
                        signup_frame.setVisible(false);
                    }else if(response.equals("Registration:failed!")){
                        signUpStatus.setText("Register:Refused!");
                    }


                }catch (Exception er){
                    signUpStatus.setText("Error Occurred!");
                }
            }
        });


        back_btn_signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signin_frame.setVisible(true);
                signup_frame.setVisible(false);
            }
        });


        signin_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LogInUsername=username_txt.getText();

                    //getting the values from the gui:-
                    String username=username_txt.getText();
                    String password=new String(password_txt.getPassword());

                    //requesting from the server to handle it:-
                    //opening connection with the server:-
                    statusSocket = new Socket(InetAddress.getByName(serverAddress), statusPort);

                    //sending the username:-
                    usernameSocket=new Socket(InetAddress.getByName(serverAddress),userPort);
                    writeUsername=new DataOutputStream(usernameSocket.getOutputStream());
                    writeUsername.writeUTF(username);
                    writeUsername.flush();
                    writeUsername.close();

                    //sending the password:-
                    passwordSocket=new Socket(InetAddress.getByName(serverAddress),passwordPort);
                    writePassword=new DataOutputStream(passwordSocket.getOutputStream());
                    writePassword.writeUTF(password);
                    writePassword.flush();
                    writePassword.close();

                    readSignInResponse=new DataInputStream(statusSocket.getInputStream());
                    String response=readSignInResponse.readUTF();

                    //receiving response from the server from another sender of mail:-
                    if(response.equals("yes")){
                        signin_frame.setVisible(false);
                        app_frame.setVisible(true);
                        LogInUsername=username;
                        mailBox=new ArrayList<String>();

                    }
                    if(response.equals("no")){
                        status.setText("Invalid Credentials!");
                    }

                }catch (Exception err){
                    status.setText("Error!");
                }

                password_txt.setText("");

            }
        });




        refresh_btn_NCTUMAIL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /////////////////////////////////////////////////synchronous updating the MailBox of the users:-//////////////////////////////////////////////////////
                Thread clientMail=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                //receive emails from the server :-
                                mailSenderSocket = new Socket(InetAddress.getByName(serverAddress), mailSenderPort);
                                mailReceiverSocket = new Socket(InetAddress.getByName(serverAddress), mailReceiverPort);
                                mailSocket = new Socket(InetAddress.getByName(serverAddress), mailMsgPort);
                            } catch (Exception er) {
                                System.out.println("er:" + er);
                            }

                            try {
                                readSender = new DataInputStream(mailSenderSocket.getInputStream());
                                String sender = readSender.readUTF();
                                readSender.close();
                                readReceiver = new DataInputStream(mailReceiverSocket.getInputStream());
                                String receiver = readReceiver.readUTF();
                                readReceiver.close();
                                readEmail = new DataInputStream(mailSocket.getInputStream());
                                String mail = readEmail.readUTF();
                                readEmail.close();
                                String msg = "sender:" + sender + "\n\n" + mail + "\n\n\n\n\n";


                                if ((!sender.equals("") && !receiver.equals("") && !msg.equals("")) && (!sender.equals(null) && !receiver.equals(null) && !msg.equals(null))) {
                                    if (LogInUsername.equals(receiver)) {
                                        if (!mailBox.contains(msg)) {
                                            mailBox.add(msg);
                                            Emails_txt.setText(Arrays.toString(mailBox.toArray()));
                                        }
                                    }
                                }

                            } catch (Exception err) {
                                System.out.println("Error: receiving mail!!!" + err);
                            }
                        }

                    }
                });
                //starting mail for client service(thread):-
                clientMail.start();
            }
        });




        signOut_btn_NCTUMAIL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app_frame.setVisible(false);
                signin_frame.setVisible(true);
                Emails_txt.setText("");
            }
        });


        send_btn_NCTUMAIL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app_frame.setVisible(false);
                sendEmail_frame.setVisible(true);
                sender_txt_sendEmail.setText(LogInUsername);
            }
        });

        back_btn_sendEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app_frame.setVisible(true);
                sendEmail_frame.setVisible(false);
            }
        });




        send_btn_sendEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    if(!receiver_txt_sendEmail.getText().equals("") && !mail_txt_sendEmail.getText().equals("")) {
                        //sending the sender name and receiver name to the server:-
                        senderSocket = new Socket(InetAddress.getByName(serverAddress), senderPort);
                        writeUsername = new DataOutputStream(senderSocket.getOutputStream());
                        writeUsername.writeUTF(LogInUsername);
                        writeUsername.flush();
                        writeUsername.close();
                        senderSocket.close();


                        receiverSocket = new Socket(InetAddress.getByName(serverAddress), receiverPort);
                        writeReceiver = new DataOutputStream(receiverSocket.getOutputStream());
                        writeReceiver.writeUTF(receiver_txt_sendEmail.getText());
                        writeReceiver.flush();
                        writeReceiver.close();
                        receiverSocket.close();


                        msgSocket = new Socket(InetAddress.getByName(serverAddress), mailToServerPort);
                        writeMessage = new DataOutputStream(msgSocket.getOutputStream());
                        writeMessage.writeUTF(mail_txt_sendEmail.getText());
                        writeMessage.flush();
                        writeMessage.close();
                        msgSocket.close();

                        sender_txt_sendEmail.setText("");
                        receiver_txt_sendEmail.setText("");
                        mail_txt_sendEmail.setText("");

                        sendEmail_frame.setVisible(false);
                        app_frame.setVisible(true);

                        System.out.println("mail sent successfully!");

                        counter++;
                    }

                }catch (Exception er){
                    System.out.println("send_btn_sendEmail error:"+er);
                }

            }
        });



    }
}

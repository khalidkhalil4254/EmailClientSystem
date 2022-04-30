package system;
import javax.swing.*;
import java.awt.*;

public class gui {


    JFrame signin_frame , signup_frame, app_frame , sendEmail_frame;
    JLabel username_lbl , password_lbl , status ,username_lbl_signup,Password_lbl_signup ,signUpStatus ,mailBox_lbl,sender_lbl_sendEmail,receiver_lbl_sendEmail,mail_lbl_sendEmail;
    JTextField username_txt  ,username_txt_signup , sender_txt_sendEmail, receiver_txt_sendEmail;
    JPasswordField password_txt,Password_txt_signup ;
    JButton signin_btn , signup_btn ,back_btn_signup , register_btn_signup , signOut_btn_NCTUMAIL , send_btn_NCTUMAIL,refresh_btn_NCTUMAIL , send_btn_sendEmail ,back_btn_sendEmail;
    JTextArea Emails_txt,mail_txt_sendEmail;
    JScrollPane scroll,scroll1;
    Image signInIcon,userIcon,sendMailIcon,signUpIcon ;



    gui(){


        //defining the title-bars icons:-
        signInIcon= Toolkit.getDefaultToolkit().getImage("log-in.png");
        userIcon= Toolkit.getDefaultToolkit().getImage("user.png");
        sendMailIcon=Toolkit.getDefaultToolkit().getImage("email.png");
        signUpIcon=Toolkit.getDefaultToolkit().getImage("add-user.png");

        //creating the JFrames of the app:-
        signin_frame=new JFrame("SignIn");
        signin_frame.setIconImage(signInIcon);
        signup_frame=new JFrame("SignUp");
        signup_frame.setIconImage(signUpIcon);
        app_frame=new JFrame("NCTU-MAIL System");
        app_frame.setIconImage(userIcon);
        sendEmail_frame=new JFrame("SEND EMAIL");
        sendEmail_frame.setIconImage(sendMailIcon);
        sendEmail_frame.setResizable(false);
        sendEmail_frame.setSize(500,500);
        sendEmail_frame.setLayout(null);
        sendEmail_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signin_frame.setResizable(false);
        signin_frame.setSize(500,235);
        signin_frame.setLayout(null);
        signin_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signup_frame.setResizable(false);
        signup_frame.setSize(480,360);
        signup_frame.setLayout(null);
        signup_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app_frame.setResizable(false);
        app_frame.setSize(1024,678);
        app_frame.setLayout(null);
        app_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        //creating the components of the signIn JFrame:-
        status=new JLabel("SignIn");
        status.setBounds(220,100,50,20);

        username_lbl=new JLabel("Username:");
        username_lbl.setFont(new Font("Verdana", Font.PLAIN, 15));
        username_lbl.setBounds(70,20,100,20);

        password_lbl=new JLabel("Password:");
        password_lbl.setFont(new Font("Verdana", Font.PLAIN, 15));
        password_lbl.setBounds(70,60,100,20);

        username_txt=new JTextField();
        username_txt.setBounds(180,20,210,30);

        password_txt=new JPasswordField();
        password_txt.setBounds(180,60,210,30);

        signin_btn=new JButton("SignIn");
        signin_btn.setBounds(300,120,80,35);

        signup_btn=new JButton("SignUp");
        signup_btn.setBounds(90,120,80,35);


        //adding the components of the signIn JFrame:-
        signin_frame.add(username_lbl);
        signin_frame.add(password_lbl);
        signin_frame.add(username_txt);
        signin_frame.add(password_txt);
        signin_frame.add(signin_btn);
        signin_frame.add(signup_btn);
        signin_frame.add(status);









        //creating the components of the signUp JFrame:-
        username_lbl_signup=new JLabel("New Username:");
        username_lbl_signup.setFont(new Font("Verdana", Font.PLAIN, 15));
        username_lbl_signup.setBounds(40,50,135,30);

        username_txt_signup=new JTextField();
        username_txt_signup.setBounds(180,50,220,30);

        Password_lbl_signup=new JLabel("Create Password:");
        Password_lbl_signup.setFont(new Font("Verdana", Font.PLAIN, 15));
        Password_lbl_signup.setBounds(40,120,135,30);

        Password_txt_signup=new JPasswordField();
        Password_txt_signup.setBounds(180,120,220,30);

        back_btn_signup=new JButton("Back");
        back_btn_signup.setBounds(40,200,80,50);

        register_btn_signup=new JButton("Register");
        register_btn_signup.setBounds(280,200,100,50);

        signUpStatus=new JLabel("SignUp");
        signUpStatus.setBounds(158,180,80,20);

        //adding the components of the signUp JFrame:-
        signup_frame.add(username_lbl_signup);
        signup_frame.add(Password_lbl_signup);
        signup_frame.add(back_btn_signup);
        signup_frame.add(username_txt_signup);
        signup_frame.add(Password_txt_signup);
        signup_frame.add(register_btn_signup);
        signup_frame.add(signUpStatus);



        //creating the components of the app JFrame:-
        mailBox_lbl=new JLabel("Your MailBox :-");
        mailBox_lbl.setFont(new Font("Verdana", Font.BOLD, 18));
        mailBox_lbl.setBounds(35,35,180,20);

        Emails_txt=new JTextArea();
        Emails_txt.setFont(new Font("Verdana", Font.PLAIN, 18));
        Emails_txt.setToolTipText("MailBox");
        Emails_txt.setEditable(false);
        scroll = new JScrollPane (Emails_txt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(35,80,935,420);

        signOut_btn_NCTUMAIL=new JButton("SignOut");
        signOut_btn_NCTUMAIL.setBounds(35,550,80,50);

        refresh_btn_NCTUMAIL=new JButton("Refresh");
        refresh_btn_NCTUMAIL.setBounds(442,550,80,50);

        send_btn_NCTUMAIL=new JButton("Send Email");
        send_btn_NCTUMAIL.setBounds(850,550,120,50);


        //adding the components of the app JFrame:-
        app_frame.add(mailBox_lbl);
        app_frame.add(scroll);
        app_frame.add(signOut_btn_NCTUMAIL);
        app_frame.add(send_btn_NCTUMAIL);
        app_frame.add(refresh_btn_NCTUMAIL);



        //creating the components of the sendEmail JFrame:-
        sender_lbl_sendEmail=new JLabel("TO :");
        sender_lbl_sendEmail.setFont(new Font("Verdana", Font.PLAIN, 15));
        sender_lbl_sendEmail.setBounds(35,80,80,20);

        sender_txt_sendEmail=new JTextField();
        sender_txt_sendEmail.setEditable(false);
        sender_txt_sendEmail.setToolTipText("sender");
        sender_txt_sendEmail.setBounds(100,40,240,30);

        receiver_lbl_sendEmail=new JLabel("From :");
        receiver_lbl_sendEmail.setFont(new Font("Verdana", Font.PLAIN, 15));
        receiver_lbl_sendEmail.setBounds(35,40,80,20);

        receiver_txt_sendEmail=new JTextField();
        receiver_txt_sendEmail.setToolTipText("receiver");
        receiver_txt_sendEmail.setBounds(100,80,240,30);

        mail_lbl_sendEmail=new JLabel("Mail:");
        mail_lbl_sendEmail.setFont(new Font("Verdana", Font.BOLD, 16));
        mail_lbl_sendEmail.setBounds(35,140,100,20);


        mail_txt_sendEmail=new JTextArea();
        mail_txt_sendEmail.setFont(new Font("Verdana", Font.PLAIN, 18));
        mail_txt_sendEmail.setToolTipText("MailBox");
        scroll1 = new JScrollPane (mail_txt_sendEmail, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll1.setBounds(100,140,240,200);


        send_btn_sendEmail=new JButton("SEND");
        send_btn_sendEmail.setBounds(365,380,80,50);

        back_btn_sendEmail=new JButton("BACK");
        back_btn_sendEmail.setBounds(35,380,80,50);


        //adding the components of the sendEmail JFrame:-
        sendEmail_frame.add(sender_lbl_sendEmail);
        sendEmail_frame.add(sender_txt_sendEmail);
        sendEmail_frame.add(receiver_lbl_sendEmail);
        sendEmail_frame.add(receiver_txt_sendEmail);
        sendEmail_frame.add(send_btn_sendEmail);
        sendEmail_frame.add(back_btn_sendEmail);
        sendEmail_frame.add(mail_lbl_sendEmail);
        sendEmail_frame.add(scroll1);




        signin_frame.setVisible(true);

    }
}

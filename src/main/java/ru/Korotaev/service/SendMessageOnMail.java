package ru.Korotaev.service;


import ru.Korotaev.Model.UserDto;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMessageOnMail {

    final Properties properties = new Properties();
    public void sendMessageOnMail(String mail, UserDto userDto){
        try {
            properties.load(SendMessageOnMail.class.getClassLoader().getResourceAsStream("mail.properties"));
            String senderEmailAddress = properties.getProperty("sender.mail");
            String senderName = properties.getProperty("sender.name");
            String senderPassword = properties.getProperty("sender.password");
            if(senderEmailAddress ==null){
                throw new Exception("sender mail not exists");
            }
            if(senderName == null || senderPassword == null){
                throw new Exception("sender name or sender password is absent");
            }
            Session mailSession = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(senderEmailAddress));
            message.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(mail)));
            message.setSubject("Patient id-" + userDto.getPatientId() + " with not standard data");
            message.setText(userDto.toString());

            Transport transport = mailSession.getTransport();
            transport.connect(senderName , senderPassword);
            transport.sendMessage(message,message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

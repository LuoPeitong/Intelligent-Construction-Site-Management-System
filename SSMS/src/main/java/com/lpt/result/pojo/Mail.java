package com.lpt.result.pojo;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {
    // 邮箱服务器地址
    private static final String MAIL_SMTP_HOST = "smtp.qq.com";
    // 邮箱服务器端口
    private static final int MAIL_SMTP_PORT = 465;
    // 发送邮件的邮箱地址
    private static final String MAIL_USERNAME = "luopeitong0739@foxmail.com";
    // 发送邮件的邮箱密码或授权码
    private static final String MAIL_PASSWORD = "ogrprzvwzleebeia";

    public static void sendMail(String to, String subject, String content) throws MessagingException {
        // 创建连接邮件服务器的会话对象
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", MAIL_SMTP_HOST);
        props.setProperty("mail.smtp.port", String.valueOf(MAIL_SMTP_PORT));
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.enable", "true");
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MAIL_USERNAME, MAIL_PASSWORD);
            }
        });
        // 创建邮件消息对象
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(MAIL_USERNAME));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setText(content);
        // 发送邮件
        Transport.send(message);
    }
}

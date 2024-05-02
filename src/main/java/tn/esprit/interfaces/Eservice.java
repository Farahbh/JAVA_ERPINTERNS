package tn.esprit.interfaces;

import javax.mail.MessagingException;

public interface Eservice <User>{


    void sendEmail(String to, String descri, String subject) throws MessagingException;
}

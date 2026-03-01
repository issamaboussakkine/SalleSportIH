package util;

import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtil {
    
    private static final String EMAIL_EXPEDITEUR = "hafsaajabboune@gmail.com";
    private static final String MOT_DE_PASSE = "wiud sxgo fuxo uqrf".replace(" ", "");    
    
    public static boolean envoyerEmailRecuperation(String destinataire, String nouveauMotDePasse) {
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_EXPEDITEUR, MOT_DE_PASSE);
            }
        });
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_EXPEDITEUR));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinataire));
            message.setSubject("Récupération de mot de passe - Salle de Sport");
            
            String contenuHTML = 
                "<html>" +
                "<body style='font-family: Arial, sans-serif;'>" +
                "<div style='background-color: #2c3e50; color: white; padding: 20px; text-align: center;'>" +
                "<h2>Salle de Sport</h2>" +
                "</div>" +
                "<div style='padding: 20px;'>" +
                "<h3>Bonjour,</h3>" +
                "<p>Vous avez demandé la réinitialisation de votre mot de passe.</p>" +
                "<p>Voici votre nouveau mot de passe temporaire :</p>" +
                "<div style='background-color: #f8f9fa; border: 2px solid #e74c3c; padding: 20px; text-align: center; font-size: 24px; font-weight: bold; color: #e74c3c; margin: 20px 0;'>" +
                nouveauMotDePasse +
                "</div>" +
                "<p>Connectez-vous avec ce mot de passe et changez-le dans votre profil.</p>" +
                "</div>" +
                "</body>" +
                "</html>";
            
            message.setContent(contenuHTML, "text/html; charset=utf-8");
            
            Transport.send(message);
            return true;
            
        } catch (MessagingException e) {
            return false;
        }
    }
    
    public static String genererMotDePasseTemp() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";
        StringBuilder mdp = new StringBuilder();
        Random r = new Random();
        
        for (int i = 0; i < 8; i++) {
            mdp.append(chars.charAt(r.nextInt(chars.length())));
        }
        
        return mdp.toString();
    }
}
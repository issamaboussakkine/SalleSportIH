/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Swing;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JDialog;
import javax.swing.SwingWorker;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import util.EmailUtil;
import dao.AbonneDao;
import entities.Abonne;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.mindrot.jbcrypt.BCrypt;
import service.AbonneService;
/**
 *
 * @author ASUS
 */
public class AuthentificationFrame extends javax.swing.JInternalFrame {
     private AbonneService abonneService;
private javax.swing.JButton btnMotDePasseOublie;
    /**
     */
    public AuthentificationFrame() {
        initComponents();
            abonneService = new AbonneService();   
         lblLogin.setText("Login:");
    lblPassword.setText("Mot de passe:");
    jLabel1.setText("BIENVENUE À LA SALLE DE SPORT IH");
    jTextField3.setText("SALLE DE SPORT IH - 2026");
    btnConnexion.setText("CONNEXION");
    ajouterBoutonMotDePasseOublie();
    this.setSize(500, 400);
    
    try {
    if (abonneService.findByLogin("admin") == null) {
        Abonne admin = new Abonne("admin", 20, "M");
        admin.setMotDePasse("admin");
        admin.setEmail("hafsaajabboune@gmail.com"); 
        abonneService.create(admin);
        System.out.println(" Admin créé automatiquement");
    }
} catch (Exception e) {
    e.printStackTrace();
}
    
    }
    
    
      
    private void ajouterBoutonMotDePasseOublie() {
        btnMotDePasseOublie = new javax.swing.JButton("Mot de passe oublié ?");
        btnMotDePasseOublie.setForeground(Color.BLUE);
        btnMotDePasseOublie.setBorderPainted(false);
        btnMotDePasseOublie.setContentAreaFilled(false);
        btnMotDePasseOublie.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMotDePasseOublie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMotDePasseOublieActionPerformed(evt);
            }
        });
        
      javax.swing.GroupLayout jPanel1Layout = (javax.swing.GroupLayout) jPanel1.getLayout();
jPanel1Layout.setHorizontalGroup(
    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
    .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(btnMotDePasseOublie)
            .addComponent(btnConnexion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
);

jPanel1Layout.setVerticalGroup(
    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jPanel1Layout.createSequentialGroup()
        .addGap(30, 30, 30)
        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(40, 40, 40)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(lblLogin)
            .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lblPassword)
            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(20, 20, 20)
        .addComponent(btnMotDePasseOublie)
        .addGap(20, 20, 20)
        .addComponent(btnConnexion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(30, 30, 30)
        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(50, Short.MAX_VALUE))
);
    }
 
  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        lblLogin = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnConnexion = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setBackground(new java.awt.Color(51, 102, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Authentification");
        setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        lblLogin.setBackground(new java.awt.Color(255, 255, 255));
        lblLogin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblLogin.setText(" Login:");

        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });

        lblPassword.setBackground(new java.awt.Color(255, 255, 255));
        lblPassword.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblPassword.setText("Mot de passe:");

        btnConnexion.setBackground(new java.awt.Color(51, 0, 255));
        btnConnexion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnConnexion.setText("CONNEXION");
        btnConnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnexionActionPerformed(evt);
            }
        });

        jTextField3.setBackground(new java.awt.Color(102, 153, 255));
        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTextField3.setText("SALLE DE SPORT IH - 2026  ");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("BIENVENUE À LA SALLE  DE SPORT IH ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(371, 371, 371)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(464, 464, 464)
                            .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(38, 38, 38)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(525, 525, 525)
                            .addComponent(lblLogin)
                            .addGap(54, 54, 54)
                            .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(220, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnConnexion, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(391, 391, 391))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(320, 320, 320))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGap(99, 99, 99)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLogin))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(93, 93, 93)
                .addComponent(btnConnexion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 216, Short.MAX_VALUE)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoginActionPerformed

    private void btnConnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnexionActionPerformed
 String loginSaisi = txtLogin.getText();
String passwordSaisi = new String(txtPassword.getPassword());

Abonne abonne = null;
         try {
             abonne = abonneService.findByLogin(loginSaisi);
         } catch (Exception ex) {
             Logger.getLogger(AuthentificationFrame.class.getName()).log(Level.SEVERE, null, ex);
         }

if (abonne != null && abonneService.verifierMotDePasse(passwordSaisi, abonne.getMotDePasse())) {
    JOptionPane.showMessageDialog(this, "Connexion réussie !");
    Main mainFrame = (Main) SwingUtilities.getWindowAncestor(this);
    mainFrame.setEstConnecte(true);
    this.dispose();
} else {
    JOptionPane.showMessageDialog(this, "Login ou mot de passe incorrect !");
}
    }//GEN-LAST:event_btnConnexionActionPerformed

    private void btnMotDePasseOublieActionPerformed(java.awt.event.ActionEvent evt) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel label = new JLabel("Entrez votre adresse email :");
        JTextField emailField = new JTextField(25);
        panel.add(label, BorderLayout.NORTH);
        panel.add(emailField, BorderLayout.CENTER);
        
        int result = JOptionPane.showConfirmDialog(
            this,
            panel,
            "Récupération de mot de passe",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );
        
        if (result == JOptionPane.OK_OPTION) {
            String email = emailField.getText().trim();
                System.out.println(" Email saisi : " + email); 
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer votre adresse email.");
                return;
            }
            
            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                JOptionPane.showMessageDialog(this, "Format d'email invalide.");
                return;
            }
            
            traiterRecuperationMotDePasse(email);
        }
    }
    
  private boolean mettreAJourMotDePasse(String email, String nouveauMdp) {
    try {
        AbonneDao abonneDao = new AbonneDao();
        Abonne abonne = abonneDao.findByEmail(email);
        if (abonne != null) {
            String mdpHache = BCrypt.hashpw(nouveauMdp, BCrypt.gensalt());
            abonne.setMotDePasse(mdpHache);
            return abonneDao.update(abonne);
        }
        return false;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
  private void traiterRecuperationMotDePasse(String email) {
       try {
        if (!emailExisteDansBD(email)) {
            JOptionPane.showMessageDialog(this, "Aucun compte associé à cet email.");
            return;
        }

        String nouveauMdp = EmailUtil.genererMotDePasseTemp();

        if (!mettreAJourMotDePasse(email, nouveauMdp)) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour.");
            return;
        }

        JTextField textField = new JTextField(nouveauMdp);
        textField.setEditable(false);           
        textField.setSelectionStart(0);          
        textField.setSelectionEnd(nouveauMdp.length());
        textField.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JOptionPane.showMessageDialog(this,
            new Object[]{"Votre nouveau mot de passe est :", textField},
            "Récupération réussie",
            JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erreur technique.");
    }
}
private boolean emailExisteDansBD(String email) {
    try {
        AbonneDao abonneDao = new AbonneDao();
        return abonneDao.findByEmail(email) != null;
    } catch (Exception e) {
        return false;
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnexion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables

   
}

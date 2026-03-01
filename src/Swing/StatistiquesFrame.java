package Swing;

import service.AbonneService;
import service.PaiementService;
import entities.Abonne;
import entities.Paiement;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class StatistiquesFrame extends JInternalFrame {
    
    private AbonneService abonneService;
    private PaiementService paiementService;
    private JLabel lblNbAbonnes;
    private JLabel lblTotalPaiements;
    private JLabel lblPaiementsMois;
    private JLabel lblAgeMoyen;
    private JList<String> listeImpayes;
    private DefaultListModel<String> modeleImpayes;
    
    public StatistiquesFrame() {
        setTitle("Statistiques des revenus");
        setSize(1000, 800);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        
        abonneService = new AbonneService();
        paiementService = new PaiementService();
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel title = new JLabel("STATISTIQUES DES REVENUS", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(title, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        
        JPanel leftPanel = new JPanel(new BorderLayout(5, 10));
        
        JPanel statsPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        statsPanel.setBorder(BorderFactory.createTitledBorder("📊 Chiffres clés"));
        
        statsPanel.add(new JLabel("Nombre total d'abonnés :"));
        lblNbAbonnes = new JLabel("0", JLabel.RIGHT);
        statsPanel.add(lblNbAbonnes);
        
        statsPanel.add(new JLabel("Total des paiements :"));
        lblTotalPaiements = new JLabel("0 DH", JLabel.RIGHT);
        statsPanel.add(lblTotalPaiements);
        
        statsPanel.add(new JLabel("Paiements ce mois-ci :"));
        lblPaiementsMois = new JLabel("0 DH", JLabel.RIGHT);
        statsPanel.add(lblPaiementsMois);
        
        statsPanel.add(new JLabel("Âge moyen :"));
        lblAgeMoyen = new JLabel("0 ans", JLabel.RIGHT);
        statsPanel.add(lblAgeMoyen);
        
        leftPanel.add(statsPanel, BorderLayout.NORTH);
        
        JPanel chartPanel = createChartPanel();
        leftPanel.add(chartPanel, BorderLayout.CENTER);
        
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("🔴 IMPAYÉS"));
        
        modeleImpayes = new DefaultListModel<>();
        listeImpayes = new JList<>(modeleImpayes);
        listeImpayes.setFont(new Font("Monospaced", Font.BOLD, 14));
        listeImpayes.setBackground(new Color(255, 240, 240));
        
        JScrollPane scrollImpayes = new JScrollPane(listeImpayes);
        rightPanel.add(scrollImpayes, BorderLayout.CENTER);
        
        centerPanel.add(leftPanel);
        centerPanel.add(rightPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        JButton btnRetour = new JButton("RETOUR");
        btnRetour.addActionListener(e -> dispose());
        btnRetour.setFont(new Font("Arial", Font.BOLD, 16));
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnRetour);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        loadStats();
        loadImpayes();
    }
    
    private JPanel createChartPanel() {
        try {
            List<Paiement> tousPaiements = paiementService.findAll();
            if (tousPaiements.isEmpty()) {
                JPanel emptyPanel = new JPanel();
                emptyPanel.add(new JLabel("Aucune donnée de paiement"));
                return emptyPanel;
            }
            
            Map<String, Double> revenusParMois = new HashMap<>();
            SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
            
            for (Paiement p : tousPaiements) {
                String moisAnnee = sdf.format(p.getDatePaiement());
                double montant = p.getMontant();
                if (revenusParMois.containsKey(moisAnnee)) {
                    revenusParMois.put(moisAnnee, revenusParMois.get(moisAnnee) + montant);
                } else {
                    revenusParMois.put(moisAnnee, montant);
                }
            }
            
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (Map.Entry<String, Double> entry : revenusParMois.entrySet()) {
                dataset.addValue(entry.getValue(), "Revenus", entry.getKey());
            }
            
            JFreeChart chart = ChartFactory.createBarChart(
                "Revenus mensuels", "Mois", "Montant (DH)", 
                dataset, PlotOrientation.VERTICAL, true, true, false);
            
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(500, 300));
            chartPanel.setBorder(BorderFactory.createTitledBorder("📈 Revenus mensuels"));
            return chartPanel;
            
        } catch (Exception e) {
            e.printStackTrace();
            JPanel errorPanel = new JPanel();
            errorPanel.add(new JLabel("Erreur: " + e.getMessage()));
            return errorPanel;
        }
    }
    
    private void loadStats() {
        try {
            List<Abonne> abonnes = abonneService.findAll();
            List<Paiement> paiements = paiementService.findAll();
            
            int nbAbonnes = abonnes.size();
            double totalPaiements = 0;
            double paiementsMois = 0;
            int sommeAges = 0;
            
            java.util.Calendar cal = java.util.Calendar.getInstance();
            int moisActuel = cal.get(java.util.Calendar.MONTH);
            int anneeActuelle = cal.get(java.util.Calendar.YEAR);
            
            for (Paiement p : paiements) {
                totalPaiements += p.getMontant();
                cal.setTime(p.getDatePaiement());
                if (cal.get(java.util.Calendar.MONTH) == moisActuel && 
                    cal.get(java.util.Calendar.YEAR) == anneeActuelle) {
                    paiementsMois += p.getMontant();
                }
            }
            
            for (Abonne a : abonnes) {
                sommeAges += a.getAge();
            }
            
            double ageMoyen = nbAbonnes > 0 ? (double) sommeAges / nbAbonnes : 0;
            
            lblNbAbonnes.setText(String.valueOf(nbAbonnes));
            lblTotalPaiements.setText(String.format("%.2f DH", totalPaiements));
            lblPaiementsMois.setText(String.format("%.2f DH", paiementsMois));
            lblAgeMoyen.setText(String.format("%.1f ans", ageMoyen));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadImpayes() {
        modeleImpayes.clear();
        try {
            List<Abonne> tousAbonnes = abonneService.findAll();
            java.util.Calendar cal = java.util.Calendar.getInstance();
            java.util.Date aujourdhui = cal.getTime();
            
            for (Abonne abonne : tousAbonnes) {
                List<Paiement> paiements = paiementService.findByAbonne(abonne.getId());
                if (paiements.isEmpty()) {
                    modeleImpayes.addElement("• " + abonne.getNom() + " - Jamais payé");
                } else {
                    Paiement dernierPaiement = paiements.get(paiements.size() - 1);
                    long diff = aujourdhui.getTime() - dernierPaiement.getDatePaiement().getTime();
                    long jours = diff / (1000 * 60 * 60 * 24);
                    if (jours > 30) {
                        modeleImpayes.addElement("• " + abonne.getNom() + " - " + jours + " jours");
                    }
                }
            }
            
            if (modeleImpayes.isEmpty()) {
                modeleImpayes.addElement("✅ Aucun impayé");
                listeImpayes.setForeground(Color.BLACK);
            } else {
                listeImpayes.setForeground(Color.RED);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setClosable(true);
        setForeground(new java.awt.Color(255, 0, 51));
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("📊 STATISTIQUES DES REVENUS");
        setToolTipText("");
        setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 860, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 345, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
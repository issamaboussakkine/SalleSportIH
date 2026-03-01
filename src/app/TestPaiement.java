package app;

import service.AbonneService;
import service.PaiementService;
import entities.Abonne;
import entities.Paiement;
import java.util.Date;
import java.util.List;

public class TestPaiement {
    public static void main(String[] args) {
        System.out.println("TEST DU SERVICE PAIEMENT");
        
        try {
            AbonneService abonneService = new AbonneService();
            PaiementService paiementService = new PaiementService();
            
            System.out.println("\n1. Préparation des abonnés :");
            
            Abonne a1 = new Abonne("Achraf", 27, "M");
            int id1 = abonneService.create(a1);
            System.out.println("   Achraf ajouté avec ID: " + id1);
            
            Abonne a2 = new Abonne("Fatima", 20, "F");
            int id2 = abonneService.create(a2);
            System.out.println("   Fatima ajoutée avec ID: " + id2);
            
            System.out.println("\n2. Ajout de paiements :");
            
            Paiement p1 = new Paiement(a1, new Date(), 150.00);
            int idP1 = paiementService.create(p1);
            System.out.println("   Paiement de Achraf: " + p1.getMontant() + "DH, ID: " + idP1);
            
            Thread.sleep(1000);
            
            Paiement p2 = new Paiement(a2, new Date(), 100.00);
            int idP2 = paiementService.create(p2);
            System.out.println("   Paiement de Fatima: " + p2.getMontant() + "DH, ID: " + idP2);
            
            Paiement p3 = new Paiement(a2, new Date(), 50.00);
            int idP3 = paiementService.create(p3);
            System.out.println("   Paiement de Fatima: " + p3.getMontant() + "DH, ID: " + idP3);
            
            System.out.println("\n3. Liste de tous les paiements :");
            List<Paiement> tous = paiementService.findAll();
            for (Paiement p : tous) {
                System.out.println("   ID " + p.getId() + " - " + p.getAbonne().getNom() + 
                                 ": " + p.getDatePaiement() + " - " + p.getMontant() + "DH");
            }
            
            System.out.println("\n4. Paiements de Achraf (ID " + id1 + ") :");
            List<Paiement> paiementsAchraf = paiementService.findByAbonne(id1);
            double totalAchraf = 0;
            for (Paiement p : paiementsAchraf) {
                System.out.println("   " + p.getDatePaiement() + ": " + p.getMontant() + "DH");
                totalAchraf += p.getMontant();
            }
            System.out.println("   Total payé par Achraf: " + totalAchraf + "DH");
            
            System.out.println("\n5. Paiements de Fatima (ID " + id2 + ") :");
            List<Paiement> paiementsFatima = paiementService.findByAbonne(id2);
            double totalFatima = 0;
            for (Paiement p : paiementsFatima) {
                System.out.println("   " + p.getDatePaiement() + ": " + p.getMontant() + "DH");
                totalFatima += p.getMontant();
            }
            System.out.println("   Total payé par Fatima: " + totalFatima + "DH");
            
            System.out.println("\n6. Total par abonné :");
            double totalAchraf2 = paiementService.getTotalPaiementsByAbonne(id1);
            double totalFatima2 = paiementService.getTotalPaiementsByAbonne(id2);
            System.out.println("   Achraf: " + totalAchraf2 + "DH");
            System.out.println("   Fatima: " + totalFatima2 + "DH");
            
            System.out.println("\n7. Chiffre d'affaires total :");
            double caTotal = paiementService.getChiffreAffairesTotal();
            System.out.println("   " + caTotal + "DH");
            
        } catch (Exception e) {
            System.out.println("ERREUR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
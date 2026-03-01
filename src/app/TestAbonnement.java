package app;

import service.AbonnementService;
import entities.Abonnement;
import java.util.List;

public class TestAbonnement {
    public static void main(String[] args) {
        System.out.println("TEST DU SERVICE ABONNEMENT");
        
        try {
            AbonnementService service = new AbonnementService();
            
            System.out.println("\n1. Ajout d'abonnements :");
            
            Abonnement mensuel = new Abonnement("Mensuel", 30, 200.00);
            int id1 = service.create(mensuel);
            System.out.println("   Mensuel ajouté avec ID: " + id1);
            
            Abonnement annuel = new Abonnement("Annuel", 365,2000.00 );
            int id2 = service.create(annuel);
            System.out.println("   Annuel ajouté avec ID: " + id2);
            
            Abonnement trimestriel = new Abonnement("Trimestriel", 90, 600.00);
            int id3 = service.create(trimestriel);
            System.out.println("   Trimestriel ajouté avec ID: " + id3);
            
            System.out.println("\n2. Recherche par ID :");
            Abonnement trouve = service.findById(id1);
            System.out.println("   Abonnement trouvé: " + trouve.getType() + 
                             ", " + trouve.getDuree() + " jours, " + trouve.getPrix() + "DH");
            
            System.out.println("\n3. Liste de tous les abonnements :");
            List<Abonnement> list = service.findAll();
            for (Abonnement a : list) {
                System.out.println("   " + a.getId() + " - " + a.getType() + 
                                 ": " + a.getDuree() + " jours, " + a.getPrix() + "DH");
            }
            
           
        } catch (Exception e) {
            System.out.println("ERREUR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
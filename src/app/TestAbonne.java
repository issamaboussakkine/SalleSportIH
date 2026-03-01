package app;

import service.AbonneService;
import entities.Abonne;
import java.util.List;

public class TestAbonne {
    public static void main(String[] args) {
        System.out.println("TEST DU SERVICE ABONNE");
        
        try {
            AbonneService service = new AbonneService();
            
            System.out.println("\n1. Ajout d'abonnés :");
            
            Abonne a1 = new Abonne("Issam", 20, "M");
            int id1 = service.create(a1);
            System.out.println("   Issam ajouté avec ID: " + id1);
            
            Abonne a2 = new Abonne("Hafsa", 20, "F");
            int id2 = service.create(a2);
            System.out.println("   Hafsa ajoutée avec ID: " + id2);
            
            System.out.println("\n2. Recherche par ID :");
            Abonne trouve = service.findById(id1);
            System.out.println("   Abonné trouvé: " + trouve.getNom() + ", " + trouve.getAge() + " ans");
            
            System.out.println("\n3. Liste de tous les abonnés :");
            List<Abonne> list = service.findAll();
            for (Abonne a : list) {
                System.out.println("   " + a.getId() + " - " + a.getNom() + 
                                 " (" + a.getAge() + " ans, " + a.getSexe() + ")");
            }
            
            System.out.println("\n4. Modification de l'abonné ID " + id1 + " :");
            a1.setAge(23);
            boolean modifie = service.update(a1);
            if (modifie) {
                System.out.println("   Abonné modifié avec succès");
                Abonne apresModif = service.findById(id1);
                System.out.println("   Nouvel âge: " + apresModif.getAge() + " ans");
            }
            
      System.out.println("\n3. Suppression de l'abonné ID " + id2 + " (Hafsa) : ");
            
            boolean resultat = service.delete(id2);
            
            if (resultat) {
                System.out.println("   ✓ Abonné supprimé avec succès");
            } else {
                System.out.println("   ✗ Échec de la suppression");
            }

        } catch (Exception e) {
            System.out.println("ERREUR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
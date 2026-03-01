package service;

import dao.AbonneDao;
import entities.Abonne;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class AbonneService {
    
    private AbonneDao abonneDao;
    
    public AbonneService() {
        abonneDao = new AbonneDao();
    }
    
    
    public Abonne findById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("L'ID doit être positif");
        }
        Abonne abonne = abonneDao.findById(id);
        if (abonne == null) {
            throw new Exception("Aucun abonné trouvé avec l'ID " + id);
        }
        return abonne;
    }
    
    public List<Abonne> findAll() throws Exception {
        return abonneDao.findAll();
    }
    
    public int create(Abonne abonne) throws Exception {
         if (abonne.getNom() == null || abonne.getNom().trim().isEmpty()) {
        throw new Exception("Le nom est obligatoire");
    }
    if (abonne.getAge() < 14 || abonne.getAge() > 120) {
        throw new Exception("L'âge doit être entre 14 et 120 ans");
    }
    if (abonne.getSexe() == null || 
        (!abonne.getSexe().equalsIgnoreCase("M") && !abonne.getSexe().equalsIgnoreCase("F"))) {
        throw new Exception("Le sexe doit être 'M' ou 'F'");
    }

    List<Abonne> existants = abonneDao.findAll();
    for (Abonne a : existants) {
        if (a.getNom().equalsIgnoreCase(abonne.getNom().trim())) {
            throw new Exception("Un abonné avec ce nom existe déjà");
        }
    }

    String mdpHache = BCrypt.hashpw(abonne.getMotDePasse(), BCrypt.gensalt());
    abonne.setMotDePasse(mdpHache);
    return abonneDao.insert(abonne);
    }
    
    public boolean update(Abonne abonne) throws Exception {
        if (abonne.getId() <= 0) {
            throw new Exception("ID invalide");
        }
        
        findById(abonne.getId());
        
        if (abonne.getNom() == null || abonne.getNom().trim().isEmpty()) {
            throw new Exception("Le nom est obligatoire");
        }
        if (abonne.getAge() < 14 || abonne.getAge() > 120) {
            throw new Exception("L'âge doit être entre 5 et 120 ans");
        }
        if (abonne.getSexe() == null || 
            (!abonne.getSexe().equalsIgnoreCase("M") && !abonne.getSexe().equalsIgnoreCase("F"))) {
            throw new Exception("Le sexe doit être 'M' ou 'F'");
        }
        
        return abonneDao.update(abonne);
    }
    
    public boolean delete(int id) throws Exception {
        findById(id);
        
        
        return abonneDao.delete(id);
    }
    
    
    public List<Abonne> findBySexe(String sexe) throws Exception {
        if (!sexe.equalsIgnoreCase("M") && !sexe.equalsIgnoreCase("F")) {
            throw new Exception("Sexe invalide. Utilisez 'M' ou 'F'");
        }
        
        List<Abonne> resultats = new java.util.ArrayList<>();
        List<Abonne> tous = abonneDao.findAll();
        
        for (Abonne a : tous) {
            if (a.getSexe().equalsIgnoreCase(sexe)) {
                resultats.add(a);
            }
        }
        
        return resultats;
    }
    
    public List<Abonne> searchByNom(String nom) throws Exception {
        if (nom == null || nom.trim().isEmpty()) {
            throw new Exception("Le nom de recherche ne peut pas être vide");
        }
        
        List<Abonne> resultats = new java.util.ArrayList<>();
        List<Abonne> tous = abonneDao.findAll();
        
        for (Abonne a : tous) {
            if (a.getNom().toLowerCase().contains(nom.toLowerCase())) {
                resultats.add(a);
            }
        }
        
        return resultats;
    }
    
    public double getAgeMoyen() throws Exception {
        List<Abonne> tous = abonneDao.findAll();
        if (tous.isEmpty()) {
            return 0;
        }
        
        int somme = 0;
        for (Abonne a : tous) {
            somme += a.getAge();
        }
        
        return (double) somme / tous.size();
    }
    
    public Abonne findByNom(String nom) throws Exception {
    List<Abonne> abonnes = abonneDao.findAll();
    for (Abonne a : abonnes) {
        if (a.getNom().equalsIgnoreCase(nom)) {
            return a;
        }
    }
    return null;
}
    
    public boolean verifierMotDePasse(String mdpClair, String mdpHache) {
    return BCrypt.checkpw(mdpClair, mdpHache);
}
    public Abonne findByLogin(String login) throws Exception {
    List<Abonne> abonnes = abonneDao.findAll();
    for (Abonne a : abonnes) {
        if (a.getNom().equalsIgnoreCase(login)) {
            return a;
        }
    }
    return null;
}
}
package service;

import dao.AbonnementDao;
import entities.Abonnement;
import java.util.List;

public class AbonnementService {
    
    private AbonnementDao abonnementDao;
    
    public AbonnementService() {
        abonnementDao = new AbonnementDao();
    }
    
    public Abonnement findById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("L'ID doit être positif");
        }
        Abonnement abonnement = abonnementDao.findById(id);
        if (abonnement == null) {
            throw new Exception("Aucun abonnement trouvé avec l'ID " + id);
        }
        return abonnement;
    }
    
    public List<Abonnement> findAll() throws Exception {
        return abonnementDao.findAll();
    }
    
    public int create(Abonnement abonnement) throws Exception {
        if (abonnement.getType() == null || abonnement.getType().trim().isEmpty()) {
            throw new Exception("Le type d'abonnement est obligatoire");
        }
        if (abonnement.getDuree() <= 0) {
            throw new Exception("La durée doit être positive");
        }
        if (abonnement.getPrix() <= 0) {
            throw new Exception("Le prix doit être positif");
        }
        
        List<Abonnement> existants = abonnementDao.findAll();
        for (Abonnement a : existants) {
            if (a.getType().equalsIgnoreCase(abonnement.getType().trim())) {
                throw new Exception("Un abonnement de type '" + abonnement.getType() + "' existe déjà");
            }
        }
        
        return abonnementDao.insert(abonnement);
    }
    
    public boolean update(Abonnement abonnement) throws Exception {
        if (abonnement.getId() <= 0) {
            throw new Exception("ID invalide");
        }
        
        findById(abonnement.getId());
        
        if (abonnement.getType() == null || abonnement.getType().trim().isEmpty()) {
            throw new Exception("Le type d'abonnement est obligatoire");
        }
        if (abonnement.getDuree() <= 0) {
            throw new Exception("La durée doit être positive");
        }
        if (abonnement.getPrix() <= 0) {
            throw new Exception("Le prix doit être positif");
        }
        
        return abonnementDao.update(abonnement);
    }
    
    public boolean delete(int id) throws Exception {
        findById(id); 
        return abonnementDao.delete(id);
    }
    
    
    public Abonnement getPlusCher() throws Exception {
        List<Abonnement> tous = abonnementDao.findAll();
        if (tous.isEmpty()) {
            return null;
        }
        
        Abonnement plusCher = tous.get(0);
        for (Abonnement a : tous) {
            if (a.getPrix() > plusCher.getPrix()) {
                plusCher = a;
            }
        }
        return plusCher;
    }
    
    public Abonnement getMoinsCher() throws Exception {
        List<Abonnement> tous = abonnementDao.findAll();
        if (tous.isEmpty()) {
            return null;
        }
        
        Abonnement moinsCher = tous.get(0);
        for (Abonnement a : tous) {
            if (a.getPrix() < moinsCher.getPrix()) {
                moinsCher = a;
            }
        }
        return moinsCher;
    }
    
    public Abonnement getPlusLong() throws Exception {
        List<Abonnement> tous = abonnementDao.findAll();
        if (tous.isEmpty()) {
            return null;
        }
        
        Abonnement plusLong = tous.get(0);
        for (Abonnement a : tous) {
            if (a.getDuree() > plusLong.getDuree()) {
                plusLong = a;
            }
        }
        return plusLong;
    }
}
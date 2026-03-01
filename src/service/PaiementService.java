package service;

import dao.PaiementDao;
import dao.AbonneDao;
import entities.Paiement;
import entities.Abonne;
import java.util.Date;
import java.util.List;

public class PaiementService {
    
    private PaiementDao paiementDao;
    private AbonneDao abonneDao;
    private AbonneService abonneService;
    
    public PaiementService() {
        this.paiementDao = new PaiementDao();
        this.abonneDao = new AbonneDao();
        this.abonneService = new AbonneService();
    }
    
    public Paiement findById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("L'ID doit être positif");
        }
        Paiement paiement = paiementDao.findById(id);
        if (paiement == null) {
            throw new Exception("Aucun paiement trouvé avec l'ID " + id);
        }
        return paiement;
    }
    
    public List<Paiement> findAll() throws Exception {
        return paiementDao.findAll();
    }
    
    public int create(Paiement paiement) throws Exception {
        if (paiement.getAbonne() == null) {
            throw new Exception("L'abonné est obligatoire");
        }
        if (paiement.getAbonne().getId() <= 0) {
            throw new Exception("ID d'abonné invalide");
        }
        
        abonneService.findById(paiement.getAbonne().getId());
        
        if (paiement.getDatePaiement() == null) {
            throw new Exception("La date de paiement est obligatoire");
        }
        if (paiement.getDatePaiement().after(new Date())) {
            throw new Exception("La date de paiement ne peut pas être dans le futur");
        }
        if (paiement.getMontant() <= 0) {
            throw new Exception("Le montant doit être positif");
        }
        
        return paiementDao.insert(paiement);
    }
    
    public boolean update(Paiement paiement) throws Exception {
        if (paiement.getId() <= 0) {
            throw new Exception("ID invalide");
        }
        
        findById(paiement.getId());
        
        if (paiement.getAbonne() == null || paiement.getAbonne().getId() <= 0) {
            throw new Exception("ID d'abonné invalide");
        }
        
        abonneService.findById(paiement.getAbonne().getId());
        
        if (paiement.getDatePaiement() == null) {
            throw new Exception("La date de paiement est obligatoire");
        }
        if (paiement.getMontant() <= 0) {
            throw new Exception("Le montant doit être positif");
        }
        
        return paiementDao.update(paiement);
    }
    
    public boolean delete(int id) throws Exception {
        findById(id); 
        return paiementDao.delete(id);
    }
    
    
    public List<Paiement> findByAbonne(int abonneId) throws Exception {
        abonneService.findById(abonneId);
        
        List<Paiement> resultats = new java.util.ArrayList<>();
        List<Paiement> tous = paiementDao.findAll();
        
        for (Paiement p : tous) {
            if (p.getAbonne().getId() == abonneId) {
                resultats.add(p);
            }
        }
        
        return resultats;
    }
    
    public double getTotalPaiementsByAbonne(int abonneId) throws Exception {
        List<Paiement> paiements = findByAbonne(abonneId);
        
        double total = 0;
        for (Paiement p : paiements) {
            total += p.getMontant();
        }
        return total;
    }
    
    public List<Paiement> findByDateRange(Date debut, Date fin) throws Exception {
        if (debut == null || fin == null) {
            throw new Exception("Les dates ne peuvent pas être nulles");
        }
        if (debut.after(fin)) {
            throw new Exception("La date de début doit être avant la date de fin");
        }
        
        List<Paiement> resultats = new java.util.ArrayList<>();
        List<Paiement> tous = paiementDao.findAll();
        
        for (Paiement p : tous) {
            if (!p.getDatePaiement().before(debut) && !p.getDatePaiement().after(fin)) {
                resultats.add(p);
            }
        }
        
        return resultats;
    }
    
    public double getChiffreAffairesTotal() throws Exception {
        List<Paiement> tous = paiementDao.findAll();
        
        double total = 0;
        for (Paiement p : tous) {
            total += p.getMontant();
        }
        return total;
    }
}
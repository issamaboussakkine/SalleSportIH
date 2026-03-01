package entities;

import java.util.Date;

public class Paiement {
    private int id;
    private Abonne abonne;
    private Date datePaiement;
    private double montant;

    public Paiement() {}

    public Paiement(Abonne abonne, Date datePaiement, double montant) {
        this.abonne = abonne;
        this.datePaiement = datePaiement;
        this.montant = montant;
    }

    public Paiement(int id, Abonne abonne, Date datePaiement, double montant) {
        this(abonne, datePaiement, montant);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Abonne getAbonne() {
        return abonne;
    }

    public void setAbonne(Abonne abonne) {
        this.abonne = abonne;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
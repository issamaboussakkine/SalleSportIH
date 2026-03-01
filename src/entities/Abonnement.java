package entities;

public class Abonnement {
    private int id;
    private String type;
    private int duree;
    private double prix;

    public Abonnement() {}

    public Abonnement(String type, int duree, double prix) {
        this.type = type;
        this.duree = duree;
        this.prix = prix;
    }

    public Abonnement(int id, String type, int duree, double prix) {
        this(type, duree, prix);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
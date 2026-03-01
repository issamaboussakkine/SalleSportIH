package entities;

public class Abonne {
    private int id;
    private String nom;
    private int age;
    private String sexe;
    private String email;
    private String motDePasse;
    private String telephone;

    public Abonne() {}

    public Abonne(String nom, int age, String sexe) {
        this.nom = nom;
        this.age = age;
        this.sexe = sexe;
    }

    public Abonne(int id, String nom, int age, String sexe) {
        this(nom, age, sexe);
        this.id = id;
    }

    // Getters et Setters existants
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    // NOUVEAUX champs
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
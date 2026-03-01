package dao;

import entities.Abonne;
import util.Connexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbonneDao implements IDao<Abonne> {

    @Override
    public Abonne findById(int id) throws Exception {
        String sql = "SELECT * FROM abonne WHERE id = ?";
        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Abonne a = new Abonne(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getInt("age"),
                        rs.getString("sexe")
                    );
                    a.setEmail(rs.getString("email"));
                    a.setMotDePasse(rs.getString("motDePasse"));
                    a.setTelephone(rs.getString("telephone"));
                    return a;
                }
            }
        }
        return null;
    }

    @Override
    public List<Abonne> findAll() throws Exception {
        String sql = "SELECT * FROM abonne";
        List<Abonne> liste = new ArrayList<>();
        try (Statement st = Connexion.getInstance().getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Abonne a = new Abonne(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getInt("age"),
                    rs.getString("sexe")
                );
                a.setEmail(rs.getString("email"));
                a.setMotDePasse(rs.getString("motDePasse"));
                a.setTelephone(rs.getString("telephone"));
                liste.add(a);
            }
        }
        return liste;
    }

    @Override
    public int insert(Abonne a) throws Exception {
        String sql = "INSERT INTO abonne (nom, age, sexe, email, motDePasse, telephone) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = Connexion.getInstance().getConnection()
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, a.getNom());
            ps.setInt(2, a.getAge());
            ps.setString(3, a.getSexe());
            ps.setString(4, a.getEmail());
            ps.setString(5, a.getMotDePasse());
            ps.setString(6, a.getTelephone());
            ps.executeUpdate();
            
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    a.setId(id);
                    return id;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean update(Abonne a) throws Exception {
        String sql = "UPDATE abonne SET nom = ?, age = ?, sexe = ?, email = ?, motDePasse = ?, telephone = ? WHERE id = ?";
        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setString(1, a.getNom());
            ps.setInt(2, a.getAge());
            ps.setString(3, a.getSexe());
            ps.setString(4, a.getEmail());
            ps.setString(5, a.getMotDePasse());
            ps.setString(6, a.getTelephone());
            ps.setInt(7, a.getId());
            return ps.executeUpdate() == 1;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM abonne WHERE id = ?";
        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }

    public Abonne findByEmail(String email) {
    System.out.println("🔍 Recherche email dans DAO : " + email);

    String sql = "SELECT * FROM abonne WHERE email = ?";
    try (Connection conn = Connexion.getInstance().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, email);
        System.out.println("Requête préparée");

        try (ResultSet rs = ps.executeQuery()) {
            System.out.println("⚡ Requête exécutée");
            if (rs.next()) {
                System.out.println(" Résultat trouvé");
                Abonne a = new Abonne(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getInt("age"),
                    rs.getString("sexe")
                );
                a.setEmail(rs.getString("email"));
                a.setMotDePasse(rs.getString("motDePasse"));
                a.setTelephone(rs.getString("telephone"));
                return a;
            } else {
                System.out.println(" Aucun résultat");
            }
        }
    } catch (Exception e) {
        System.out.println(" Erreur dans findByEmail : " + e.getMessage());
        e.printStackTrace();
    }
    return null;
}
    
}
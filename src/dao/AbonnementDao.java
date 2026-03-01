package dao;

import entities.Abonnement;
import util.Connexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbonnementDao implements IDao<Abonnement> {

    @Override
    public Abonnement findById(int id) throws Exception {
        String sql = "SELECT * FROM abonnement WHERE id = ?";
        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Abonnement(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getInt("duree"),
                        rs.getDouble("prix")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Abonnement> findAll() throws Exception {
        String sql = "SELECT * FROM abonnement";
        List<Abonnement> liste = new ArrayList<>();
        try (Statement st = Connexion.getInstance().getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                liste.add(new Abonnement(
                    rs.getInt("id"),
                    rs.getString("type"),
                    rs.getInt("duree"),
                    rs.getDouble("prix")
                ));
            }
        }
        return liste;
    }

    @Override
    public int insert(Abonnement a) throws Exception {
        String sql = "INSERT INTO abonnement (type, duree, prix) VALUES (?, ?, ?)";
        try (PreparedStatement ps = Connexion.getInstance().getConnection()
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, a.getType());
            ps.setInt(2, a.getDuree());
            ps.setDouble(3, a.getPrix());
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
    public boolean update(Abonnement a) throws Exception {
        String sql = "UPDATE abonnement SET type = ?, duree = ?, prix = ? WHERE id = ?";
        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setString(1, a.getType());
            ps.setInt(2, a.getDuree());
            ps.setDouble(3, a.getPrix());
            ps.setInt(4, a.getId());
            return ps.executeUpdate() == 1;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM abonnement WHERE id = ?";
        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }
}
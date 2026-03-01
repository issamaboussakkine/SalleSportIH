package dao;

import entities.Paiement;
import entities.Abonne;
import util.Connexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaiementDao implements IDao<Paiement> {
    
    private AbonneDao abonneDao = new AbonneDao();

    @Override
    public Paiement findById(int id) throws Exception {
        String sql = "SELECT * FROM paiement WHERE id = ?";
        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Abonne a = abonneDao.findById(rs.getInt("abonne_id"));
                    return new Paiement(
                        rs.getInt("id"),
                        a,
                        rs.getDate("datePaiement"),
                        rs.getDouble("montant")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Paiement> findAll() throws Exception {
        String sql = "SELECT * FROM paiement";
        List<Paiement> liste = new ArrayList<>();
        try (Statement st = Connexion.getInstance().getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Abonne a = abonneDao.findById(rs.getInt("abonne_id"));
                liste.add(new Paiement(
                    rs.getInt("id"),
                    a,
                    rs.getDate("datePaiement"),
                    rs.getDouble("montant")
                ));
            }
        }
        return liste;
    }

    @Override
    public int insert(Paiement p) throws Exception {
        String sql = "INSERT INTO paiement (abonne_id, datePaiement, montant) VALUES (?, ?, ?)";
        try (PreparedStatement ps = Connexion.getInstance().getConnection()
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, p.getAbonne().getId());
            ps.setDate(2, new java.sql.Date(p.getDatePaiement().getTime()));
            ps.setDouble(3, p.getMontant());
            ps.executeUpdate();
            
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    p.setId(id);
                    return id;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean update(Paiement p) throws Exception {
        String sql = "UPDATE paiement SET abonne_id = ?, datePaiement = ?, montant = ? WHERE id = ?";
        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, p.getAbonne().getId());
            ps.setDate(2, new java.sql.Date(p.getDatePaiement().getTime()));
            ps.setDouble(3, p.getMontant());
            ps.setInt(4, p.getId());
            return ps.executeUpdate() == 1;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM paiement WHERE id = ?";
        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }
}
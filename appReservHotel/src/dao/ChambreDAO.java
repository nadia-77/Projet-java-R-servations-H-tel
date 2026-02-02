/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Chambre;
import java.sql.Connection;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import util.DBConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class ChambreDAO implements GenericDAO<Chambre> {
    @Override
    public void create(Chambre c) {
        String sql = "INSERT INTO chambre(numero, type, prix_par_nuit) VALUES (?, ?, ?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, c.getNumero());
            ps.setString(2, c.getType());
            ps.setDouble(3, c.getPrixParNuit());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                c.setId(rs.getInt(1));
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("La Chambre Existe Deja : " + c.getNumero());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ChambreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Chambre c) {
        String sql = "UPDATE chambre SET numero=?, type=?, prix_par_nuit=? WHERE id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, c.getNumero());
            ps.setString(2, c.getType());
            ps.setDouble(3, c.getPrixParNuit());
            ps.setInt(4, c.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ChambreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM chambre WHERE id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ChambreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Chambre findById(int id) {
        String sql = "SELECT * FROM chambre WHERE id=?";
        Chambre c = null;
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Chambre();
                c.setId(rs.getInt("id"));
                c.setNumero(rs.getInt("numero"));
                c.setType(rs.getString("type"));
                c.setPrixParNuit(rs.getDouble("prix_par_nuit"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ChambreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    public List<Chambre> findAll() {
        List<Chambre> list = new ArrayList<>();
        String sql = "SELECT * FROM chambre";
        try (Connection cn = DBConnection.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Chambre c = new Chambre();
                c.setId(rs.getInt("id"));
                c.setNumero(rs.getInt("numero"));
                c.setType(rs.getString("type"));
                c.setPrixParNuit(rs.getDouble("prix_par_nuit"));
                list.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ChambreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }


    public List<Chambre> findDisponibles(LocalDate debut, LocalDate fin, String type) {
    List<Chambre> list = new ArrayList<>();

    String sql = "SELECT * FROM chambre c WHERE " + "(? = 'Tous' OR c.type = ?) AND " + "c.id NOT IN ( " + "   SELECT r.chambre_id FROM reservation r " +
        "   WHERE NOT (r.date_fin < ? OR r.date_debut > ?) " + ")";

    try (Connection cn = DBConnection.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setString(1, type);        
        ps.setString(2, type);         
        ps.setDate(3, Date.valueOf(debut));  
        ps.setDate(4, Date.valueOf(fin));   

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Chambre c = new Chambre();
            c.setId(rs.getInt("id"));
            c.setNumero(rs.getInt("numero"));
            c.setType(rs.getString("type"));
            c.setPrixParNuit(rs.getDouble("prix_par_nuit")); 
            list.add(c);
        }

    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Erreur lors de la recherche des chambres disponibles");
    }

    return list;
}

    public List<String> getTypes() {
    List<String> types = new ArrayList<>();

    String sql = "SELECT DISTINCT type FROM chambre ORDER BY type";

    try (Connection cn = DBConnection.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            types.add(rs.getString("type"));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    types.add("Tous");

    return types;
}

    
}
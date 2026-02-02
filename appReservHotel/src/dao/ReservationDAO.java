/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import model.Reservation;
import util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class ReservationDAO implements GenericDAO<Reservation>{

    
    @Override
    public void create(Reservation r) {

        if (chevauchement(r)) {
            System.out.println("Réservation refusée il y a un chevauchement)");
            return;
        }

        String sql = "INSERT INTO reservation(chambre_id, client_id, date_debut, date_fin) "+ "VALUES (?, ?, ?, ?)";

        try {
            Connection cn = DBConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setInt(1, r.getChambreId());
            ps.setInt(2, r.getClientId());
            ps.setDate(3, Date.valueOf(r.getDateDebut()));
            ps.setDate(4, Date.valueOf(r.getDateFin()));

            ps.executeUpdate();
            cn.close();

            System.out.println("Réservation bien ajoutée");

        } catch (Exception e) {
            System.out.println("Erreur de l'ajout de la réservation");
        }
    }

    
    @Override
    public void update(Reservation r) {

        String sql = "UPDATE reservation SET chambre_id=?, client_id=?, " + "date_debut=?, date_fin=? WHERE id=?";

        try {
            Connection cn = DBConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setInt(1, r.getChambreId());
            ps.setInt(2, r.getClientId());
            ps.setDate(3, Date.valueOf(r.getDateDebut()));
            ps.setDate(4, Date.valueOf(r.getDateFin()));
            ps.setInt(5, r.getId());

            ps.executeUpdate();
            cn.close();

            System.out.println("Réservation bien modifiée");

        } catch (Exception e) {
            System.out.println("Erreur de la modification de la réservation");
        }
    }

    @Override
    public void delete(int id) {

        try {
            Connection cn = DBConnection.getConnection();
            PreparedStatement ps =
                cn.prepareStatement("DELETE FROM reservation WHERE id=?");

            ps.setInt(1, id);
            ps.executeUpdate();
            cn.close();

            System.out.println("Réservation bien supprimée");

        } catch (Exception e) {
            System.out.println("Erreur de la suppression de la réservation");
        }
    }


    @Override
    public Reservation findById(int id) {

        Reservation r = null;

        try {
            Connection cn = DBConnection.getConnection();
            PreparedStatement ps =
                cn.prepareStatement("SELECT * FROM reservation WHERE id=?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                r = new Reservation();
                r.setId(rs.getInt("id"));
                r.setChambreId(rs.getInt("chambre_id"));
                r.setClientId(rs.getInt("client_id"));
                r.setDateDebut(rs.getDate("date_debut").toLocalDate());
                r.setDateFin(rs.getDate("date_fin").toLocalDate());
            }
            cn.close();

        } catch (Exception e) {
            System.out.println("Erreur de recherche de la réservation");
        }
        return r;
    }

    
    @Override
    public List<Reservation> findAll() {

        List<Reservation> list = new ArrayList<>();

        try {
            Connection cn = DBConnection.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM reservation");

            while (rs.next()) {
                Reservation r = new Reservation();
                r.setId(rs.getInt("id"));
                r.setChambreId(rs.getInt("chambre_id"));
                r.setClientId(rs.getInt("client_id"));
                r.setDateDebut(rs.getDate("date_debut").toLocalDate());
                r.setDateFin(rs.getDate("date_fin").toLocalDate());
                list.add(r);
            }
            cn.close();

        } catch (Exception e) {
            System.out.println("Erreur d'affichage des réservations");
        }
        return list;
    }

    
    private boolean chevauchement(Reservation r) {

        try {
            Connection cn = DBConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM reservation "+ "WHERE chambre_id=? " + "AND date_debut <= ? "+ "AND date_fin >= ?");

            ps.setInt(1, r.getChambreId());
            ps.setDate(2, Date.valueOf(r.getDateFin()));
            ps.setDate(3, Date.valueOf(r.getDateDebut()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cn.close();
                return true;
            }
            cn.close();

        } catch (Exception e) {
            return true;
        }
        return false;
    }
    
    
    public List<Reservation> findByClientWithFilter(int clientId, String typeChambre, LocalDate dateDebut, LocalDate dateFin) {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT r.* FROM reservation r";

        boolean filtreType = typeChambre != null && !typeChambre.isEmpty();
        if (filtreType) {
            sql += " JOIN chambre c ON r.chambre_id = c.id WHERE r.client_id=? AND c.type=?";
        } else {
            sql += " WHERE r.client_id=?";
        }

        if (dateDebut != null && dateFin != null) {
            if (filtreType) {
                sql += " AND r.date_debut >= ? AND r.date_fin <= ?";
            } else {
                sql += " AND r.date_debut >= ? AND r.date_fin <= ?";
            }
        }

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            int index = 1;
            ps.setInt(index++, clientId);

            if (filtreType) {
                ps.setString(index++, typeChambre);
            }

            if (dateDebut != null && dateFin != null) {
                ps.setDate(index++, Date.valueOf(dateDebut));
                ps.setDate(index++, Date.valueOf(dateFin));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reservation r = new Reservation();
                r.setId(rs.getInt("id"));
                r.setChambreId(rs.getInt("chambre_id"));
                r.setClientId(rs.getInt("client_id"));
                r.setDateDebut(rs.getDate("date_debut").toLocalDate());
                r.setDateFin(rs.getDate("date_fin").toLocalDate());
                list.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("!! Erreur de Filtrage !!");
        }

        return list;
    }

    public boolean existeChevauchement(int chambreId, LocalDate debut, LocalDate fin) {
        
        boolean existe = false;
        String sql = "SELECT * FROM reservation WHERE chambre_id=? AND date_debut <= ? AND date_fin >= ?";

        try (Connection cn = DBConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql)) {

              ps.setInt(1, chambreId);
              ps.setDate(2, Date.valueOf(fin));
              ps.setDate(3, Date.valueOf(debut));

              ResultSet rs = ps.executeQuery();
            if (rs.next()) {
              existe = true;
            }

    }   catch (Exception e) {
            e.printStackTrace();
    }

        return existe;
    }

    public void addReservation(int chambreId, int clientId, LocalDate debut, LocalDate fin) {
        
            String sql = "INSERT INTO reservation(chambre_id, client_id, date_debut, date_fin) VALUES (?, ?, ?, ?)";

        try (Connection cn = DBConnection.getConnection();
           PreparedStatement ps = cn.prepareStatement(sql)) {

           ps.setInt(1, chambreId);
           ps.setInt(2, clientId);
           ps.setDate(3, Date.valueOf(debut));
           ps.setDate(4, Date.valueOf(fin));

           ps.executeUpdate();
           System.out.println("Réservation bien ajoutée");

       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("Erreur lors de l'ajout de la réservation");
       }
     }
    
}
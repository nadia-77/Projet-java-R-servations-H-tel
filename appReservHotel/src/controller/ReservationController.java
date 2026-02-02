/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ReservationDAO;
import java.time.LocalDate;
import java.util.List;
import model.Reservation;

/**
 *
 * @author lenovo
 */
public class ReservationController {

    private ReservationDAO dao;

    public ReservationController(Object view) {
        dao = new ReservationDAO();
    }

    public void create(Reservation r) {
        dao.create(r);
    }

    public void delete(Reservation r) {
        dao.delete(r.getId());
    }

    public void update(Reservation r) {
        dao.update(r);
    }

    public Reservation findById(int id) {
        return dao.findById(id);
    }

    public List<Reservation> getAll() {
        return dao.findAll();
    }
    
    public void reserver(int chambreId, int clientId, LocalDate debut, LocalDate fin) throws Exception {

        if (dao.existeChevauchement(chambreId, debut, fin)) {
            throw new Exception("Chambre déjà réservée pour cette période");
        } else {
            dao.addReservation(chambreId, clientId, debut, fin);
        }
       
        
    }
}

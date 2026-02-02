/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author lenovo
 */
public class Reservation {
    private int id;
    private int chambreId;
    private int clientId;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public Reservation(int chambreId, int clientId, LocalDate dateDebut, LocalDate dateFin) {
        this.chambreId = chambreId;
        this.clientId = clientId;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Reservation() {
        
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChambreId() {
        return chambreId;
    }

    public void setChambreId(int chambreId) {
        this.chambreId = chambreId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }



    
}

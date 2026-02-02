/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lenovo
 */
public class Chambre {
    private int id;
    private int numero;
    private String type;
    private double prixParNuit;

    public Chambre(int numero, String type, double prixParNuit) {
        this.numero = numero;
        this.type = type;
        this.prixParNuit = prixParNuit;
    }

    public Chambre() {
        
    }


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrixParNuit() {
        return prixParNuit;
    }

    public void setPrixParNuit(double prixParNuit) {
        this.prixParNuit = prixParNuit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID Chambre : "+ id + " - " + type;
    }

    
}


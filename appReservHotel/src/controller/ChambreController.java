/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ChambreDAO;
import model.Chambre;
import view.ChambreForm;

import java.util.List;

/**
 *
 * @author lenovo
 */
public class ChambreController {
    
    private ChambreForm view;
    private ChambreDAO dao;

   public ChambreController(ChambreForm view) {
        this.view = view;
        this.dao = new ChambreDAO();
    }


   public void ajouter(Chambre c) {
       dao.create(c);
    }

   public List<Chambre> getAll() {
       return dao.findAll();
   }

    public void delete(Chambre c) {
        ChambreDAO dao = new ChambreDAO();
        dao.delete(c.getId());
    }

    public void update(Chambre c) {
        ChambreDAO dao = new ChambreDAO();
        dao.update(c);
    }

    
}
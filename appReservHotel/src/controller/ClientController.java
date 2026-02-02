/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import view.ClientForm;
import dao.ClientDAO;
import java.util.List;
import model.Client;

/**
 *
 * @author lenovo
 */
public class ClientController {
    private ClientDAO dao;
    private ClientForm view;
    
    public ClientController(ClientForm view){
    
        this.view= view;
        this.dao = new ClientDAO();
    }
    
    
    public void ajouter(Client c) {
       dao.create(c);
    }
   public List<Client> getAll() {
       return dao.findAll();
   }

    public void supprimer(Client c) {
        ClientDAO dao = new ClientDAO();
        dao.delete(c.getId());
    }

    public void modifier(Client c) {
        ClientDAO dao = new ClientDAO();
        dao.update(c);
    }

  
}

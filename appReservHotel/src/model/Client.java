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
    public class Client {
        private int id;
        private String nom;
        private String ville;
        private String telephone;

        public Client(String nom, String ville, String telephone) {
            this.nom = nom;
            this.ville = ville;
            this.telephone = telephone;
        }

        public Client() {

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getVille() {
            return ville;
        }

        public void setVille(String ville) {
            this.ville = ville;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        @Override
        public String toString() {
            return "ID Client : "+id +" - "+ nom;
        }


    }

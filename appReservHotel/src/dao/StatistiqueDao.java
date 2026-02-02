/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.time.LocalDate;
import java.util.List;
import model.Chambre;
import model.Reservation;

/**
 *
 * @author lenovo
 */
public class StatistiqueDao {
    private ChambreDAO chambreDAO = new ChambreDAO();
    private ReservationDAO reservationDAO = new ReservationDAO();

    public double[] getTauxOccupation(int year) {
        double[] taux = new double[12];

        try {
            List<Chambre> chambres = chambreDAO.findAll();
            int totalChambres = chambres.size();

            List<Reservation> reservations = reservationDAO.findAll();

            for (int m = 0; m < 12; m++) {
                int joursReserves = 0;

                LocalDate moisDebut = LocalDate.of(year, m + 1, 1);
                LocalDate moisFin = LocalDate.of(year, m + 1, moisDebut.lengthOfMonth());

                int joursTotaux = moisDebut.lengthOfMonth() * totalChambres;

                for (Reservation r : reservations) {
                    LocalDate debut = r.getDateDebut();
                    LocalDate fin = r.getDateFin();

                  
                    LocalDate start = debut.isAfter(moisDebut) ? debut : moisDebut;
                    LocalDate end = fin.isBefore(moisFin) ? fin : moisFin;

                    if (!end.isBefore(start)) {
                        
                        joursReserves += end.getDayOfMonth() - start.getDayOfMonth() + 1;
                    }
                }

                taux[m] = (joursTotaux == 0) ? 0 : ((double) joursReserves / joursTotaux) * 100;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors du calcul du taux d'occupation");
        }

        return taux;
    }
    
}

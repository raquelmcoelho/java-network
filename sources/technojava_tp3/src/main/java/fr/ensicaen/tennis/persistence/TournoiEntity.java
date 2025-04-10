package fr.ensicaen.tennis.persistence;

import java.util.Date;
import jakarta.persistence.*;


@Entity
@Table(name = "Tournoi")
public class TournoiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codeTournoi")
    private int codeTournoi;

    @Column(name = "Nom", length = 100)
    private String nom;

    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "Lieu", length = 100)
    private String lieu;

    // Getters and Setters
    public int getCodeTournoi() {
        return codeTournoi;
    }

    public void setCodeTournoi(int codeTournoi) {
        this.codeTournoi = codeTournoi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
}

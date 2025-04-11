package fr.ensicaen.tennis.persistence;

import jakarta.persistence.*;

import java.util.Date;

public class TournoiEntity {
    private long code_tournoi;
    private String nom;
    private String lieu;
    private Date date;

    @Id
    @Column(name = "code_tournoi")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getCodeTournoi() {
        return code_tournoi;
    }
    public void setCodeTournoi(long code_tournoi) {
        this.code_tournoi = code_tournoi;
    }

    @Basic
    @Column(name="nom", length = 255)
    public String geNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}

    @Basic
    @Column(name="lieu", length = 255)
    public String geLieu() {return lieu;}
    public void setLieu(String lieu) {this.lieu = lieu;}

    @Basic
    @Column(name = "date")
    public Date geDate() {return date;}
    public void setDate(Date date) {this.date = date;}
}

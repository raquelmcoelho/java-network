package fr.ensicaen.tennis.persistence;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="tournoi", schema = "PUBLIC", catalog = "tennis")
public class TournoiEntity {
    private int codeTournoi;
    private String nom;
    private String lieu;
    private Date date;

    @Id
    @Column(name = "codeTournoi")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getCodeTournoi() {
        return codeTournoi;
    }
    public void setCodeTournoi(int codeTournoi) {
        this.codeTournoi = codeTournoi;
    }

    @Basic
    @Column(name="nom", length = 255)
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}

    @Basic
    @Column(name="lieu", length = 255)
    public String getLieu() {return lieu;}
    public void setLieu(String lieu) {this.lieu = lieu;}

    @Basic
    @Column(name = "date")
    public Date getDate() {return date;}
    public void setDate(Date date) {this.date = date;}
}

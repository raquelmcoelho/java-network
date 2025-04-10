package fr.ensicaen.dp.tennis.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "tournoi", schema = "khalfaoui", catalog = "clinique")
public class TournoiEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codetournoi")
    private String codetournoi;
    @Basic
    @Column(name = "nom")
    private String nom;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "lieu")
    private String lieu;

    public String getCodetournoi() {
        return codetournoi;
    }

    public void setCodetournoi(String codetournoi) {
        this.codetournoi = codetournoi;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TournoiEntity that = (TournoiEntity) o;

        if (codetournoi != null ? !codetournoi.equals(that.codetournoi) : that.codetournoi != null) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (lieu != null ? !lieu.equals(that.lieu) : that.lieu != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codetournoi != null ? codetournoi.hashCode() : 0;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (lieu != null ? lieu.hashCode() : 0);
        return result;
    }
}

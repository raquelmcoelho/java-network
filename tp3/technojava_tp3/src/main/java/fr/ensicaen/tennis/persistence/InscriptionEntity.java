package fr.ensicaen.tennis.persistence;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="inscription", schema = "PUBLIC", catalog = "tennis")
public class InscriptionEntity {
    private int numero_adherent;
    private int code_tournoi;
    private Date date_inscription;

    @Id
    @JoinColumn(name = "numero_adherant", referencedColumnName = "numero_adherant")
    public int getNumeroAdherent() { return numero_adherent; }
    public void setNumeroAdherent(int id) { this.numero_adherent = id; }

    @Id
    @JoinColumn(name = "code_tournoi", referencedColumnName = "code _tournoi")
    public int getCodeTournoi() { return code_tournoi; }
    public void setCodeTournoi(int id) { this.code_tournoi = id; }

    @Basic
    @Column(name="date_inscription")
    public Date getDate_inscription() { return date_inscription; }
    public void setDate_inscription(Date date_inscription) { this.date_inscription = date_inscription; }
}

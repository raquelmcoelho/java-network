package fr.ensicaen.tennis.persistence;

import java.sql.Date;
import jakarta.persistence.*;
@Entity
@Table(name = "Inscription")
public class InscriptionEntity {

    @Id
    @JoinColumn(name = "numeroAdherent", referencedColumnName = "numeroAdherent")
    private int numeroAdherent;

    @Id
    @JoinColumn(name = "codeTournoi", referencedColumnName = "codeTournoi")
    private int codeTournoi;

    @Column(name = "DateInscription")
    @Temporal(TemporalType.DATE)
    private Date dateInscription;

    // Getters and Setters
    public int getAdherent() {
        return numeroAdherent;
    }

    public void setAdherent(int adherent) {
        this.numeroAdherent = adherent;
    }

    public int getCodeTournoi() {
        return codeTournoi;
    }

    public void setCodeTournoi(int tournoi) {
        this.codeTournoi = tournoi;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }
}

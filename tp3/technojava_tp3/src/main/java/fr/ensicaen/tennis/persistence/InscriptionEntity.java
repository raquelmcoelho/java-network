package fr.ensicaen.tennis.persistence;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="inscription", schema = "PUBLIC", catalog = "tennis")
public class InscriptionEntity {
    private int numeroAdherent;
    private int codeTournoi;
    private Date dateInscription;

    @Id
    @JoinColumn(name = "numeroAdherent", referencedColumnName = "numeroAdherent")
    public int getNumeroAdherent() { return numeroAdherent; }
    public void setNumeroAdherent(int id) { this.numeroAdherent = id; }

    @Id
    @JoinColumn(name = "codeTournoi", referencedColumnName = "code _tournoi")
    public int getCodeTournoi() { return codeTournoi; }
    public void setCodeTournoi(int id) { this.codeTournoi = id; }

    @Basic
    @Column(name="dateInscription")
    public Date getDateInscription() { return dateInscription; }
    public void setDateInscription(Date dateInscription) { this.dateInscription = dateInscription; }
}

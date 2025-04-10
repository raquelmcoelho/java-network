package fr.ensicaen.dp.tennis.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "inscription", schema = "khalfaoui", catalog = "clinique")
@IdClass(InscriptionEntityPK.class)
public class InscriptionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "numeroadherent")
    private String numeroadherent;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codetournoi")
    private String codetournoi;
    @Basic
    @Column(name = "dateinscription")
    private Date dateinscription;

    public String getNumeroadherent() {
        return numeroadherent;
    }

    public void setNumeroadherent(String numeroadherent) {
        this.numeroadherent = numeroadherent;
    }

    public String getCodetournoi() {
        return codetournoi;
    }

    public void setCodetournoi(String codetournoi) {
        this.codetournoi = codetournoi;
    }

    public Date getDateinscription() {
        return dateinscription;
    }

    public void setDateinscription(Date dateinscription) {
        this.dateinscription = dateinscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InscriptionEntity that = (InscriptionEntity) o;

        if (numeroadherent != null ? !numeroadherent.equals(that.numeroadherent) : that.numeroadherent != null)
            return false;
        if (codetournoi != null ? !codetournoi.equals(that.codetournoi) : that.codetournoi != null) return false;
        if (dateinscription != null ? !dateinscription.equals(that.dateinscription) : that.dateinscription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numeroadherent != null ? numeroadherent.hashCode() : 0;
        result = 31 * result + (codetournoi != null ? codetournoi.hashCode() : 0);
        result = 31 * result + (dateinscription != null ? dateinscription.hashCode() : 0);
        return result;
    }
}

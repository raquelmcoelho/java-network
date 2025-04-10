package fr.ensicaen.dp.tennis.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class InscriptionEntityPK implements Serializable {
    @Column(name = "numeroadherent")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String numeroadherent;
    @Column(name = "codetournoi")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String codetournoi;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InscriptionEntityPK that = (InscriptionEntityPK) o;

        if (numeroadherent != null ? !numeroadherent.equals(that.numeroadherent) : that.numeroadherent != null)
            return false;
        if (codetournoi != null ? !codetournoi.equals(that.codetournoi) : that.codetournoi != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numeroadherent != null ? numeroadherent.hashCode() : 0;
        result = 31 * result + (codetournoi != null ? codetournoi.hashCode() : 0);
        return result;
    }
}

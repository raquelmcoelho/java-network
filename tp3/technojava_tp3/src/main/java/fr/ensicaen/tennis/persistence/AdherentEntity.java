package fr.ensicaen.tennis.persistence;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "adherent", schema = "PUBLIC", catalog = "tennis")
public class AdherentEntity {
    private int numeroAdherent;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String password;

    @Id
    @Column(name = "numeroAdherent")
    // AUTO si Hibernate génère l'id, IDENTITY si c'est la BDD qui prend en charge la génération
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getNumeroAdherent() { return numeroAdherent; }
    public void setNumeroAdherent(int id) { this.numeroAdherent = id; }

    @Basic
    @Column(name="nom", length = 255)
    public String getNom() { return nom; }
    public void setNom(String nom) {this.nom = nom; }

    @Basic
    @Column(name="prenom", length = 255)
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) {this.prenom = prenom; }

    @Basic
    @Column(name = "adresse", length=255)
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) {this.adresse = adresse; }

    @Basic
    @Column(name = "telephone", length=255)
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) {this.telephone = telephone; }

    @Basic
    @Column(name = "email", length=255, nullable = false, unique = true)
    public String getEmail() { return email; }
    public void setEmail(String email) {this.email = email; }

    @Basic
    @Column(name = "password", length=255, nullable = false)
    public String getPassword() { return password; }
    public void setPassword(String password) {this.password = password; }
}

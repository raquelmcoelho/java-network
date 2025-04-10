package fr.ensicaen.tennis.persistence;

import fr.ensicaen.tennis.util.FormatNumber;
import jakarta.persistence.*;

@Entity
@Table(name = "adherent", schema = "PUBLIC", catalog = "tennis")
public class AdherentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Utilisation d'IDENTITY car la BDD gère l'auto-incrémentation
	@Column(name = "numeroAdherent", nullable = false)
	private int numeroAdherent;

	@Column(name = "nom", nullable = false, length = 50)
	private String nom;

	@Column(name = "prenom", nullable = false, length = 50)
	private String prenom;

	@Column(name = "adresse", length = 255)
	private String adresse;

	@Column(name = "telephone", length = 20)
	private String telephone;

	@Column(name = "email", length = 100, unique = true)
	private String email;

	@Column(name = "password", nullable = false, length = 255)
	private String password;

	// Getters et Setters
	public int getNumeroAdherent() {
		return numeroAdherent;
	}

	public void setNumeroAdherent(int numeroAdherent) {
		this.numeroAdherent = numeroAdherent;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return FormatNumber.formatNumber(telephone);
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
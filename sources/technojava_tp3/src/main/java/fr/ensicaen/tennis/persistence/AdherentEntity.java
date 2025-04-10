package fr.ensicaen.tennis.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "adherent", schema = "PUBLIC", catalog = "tennis")
public class AdherentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Utilisation d'IDENTITY car la BDD gère l'auto-incrémentation
	@Column(name = "numeroAdherent", nullable = false)
	private long numeroAdherent;

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
	public long getNumeroAdherent() {
		return numeroAdherent;
	}

	public void setNumeroAdherent(long numeroAdherent) {
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
		return telephone;
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
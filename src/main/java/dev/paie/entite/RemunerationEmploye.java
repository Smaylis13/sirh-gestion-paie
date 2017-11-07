package dev.paie.entite;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class RemunerationEmploye {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String matricule;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dateCreation;

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Date getDateCreation() {
		return dateCreation;
	}

	@ManyToOne
	private Entreprise entreprise;
	@ManyToOne
	private ProfilRemuneration profilRemuneration;
	@ManyToOne
	private Grade grade;
	
	public RemunerationEmploye() {
	}
	public String getMatricule() {
		return matricule;
	}
	public RemunerationEmploye setMatricule(String matricule) {
		this.matricule = matricule;
		return this;
	}
	
	public Entreprise getEntreprise() {
		return entreprise;
	}
	public RemunerationEmploye setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
		return this;
	}
	public ProfilRemuneration getProfilRemuneration() {
		return profilRemuneration;
	}
	public RemunerationEmploye setProfilRemuneration(ProfilRemuneration profilRemuneration) {
		this.profilRemuneration = profilRemuneration;
		return this;
	}
	public Grade getGrade() {
		return grade;
	}
	public RemunerationEmploye setGrade(Grade grade) {
		this.grade = grade;
		return this;
	}
	public Integer getId() {
		return id;
	}
	public RemunerationEmploye setId(Integer id) {
		this.id = id;
		return this;
	}
	
	
	
	

}

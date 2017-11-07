package dev.paie.entite;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import dev.paie.service.CalculerRemunerationService;

@Entity
public class BulletinSalaire {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private RemunerationEmploye remunerationEmploye;
	@ManyToOne
	private Periode periode;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dateCreation;


	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Column
	private BigDecimal primeExceptionnelle;

	public BulletinSalaire() {
	}

	public RemunerationEmploye getRemunerationEmploye() {
		return remunerationEmploye;
	}

	public BulletinSalaire setRemunerationEmploye(RemunerationEmploye remunerationEmploye) {
		this.remunerationEmploye = remunerationEmploye;
		return this;
	}

	public Periode getPeriode() {
		return periode;
	}

	public BulletinSalaire setPeriode(Periode periode) {
		this.periode = periode;
		return this;
	}

	public BigDecimal getPrimeExceptionnelle() {
		return primeExceptionnelle;
	}

	public BulletinSalaire setPrimeExceptionnelle(BigDecimal primeExceptionnelle) {
		this.primeExceptionnelle = primeExceptionnelle;
		return this;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}

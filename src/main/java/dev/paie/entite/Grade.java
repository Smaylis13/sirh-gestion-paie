package dev.paie.entite;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Grade {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String code;
	@Column
	private BigDecimal nbHeuresBase;
	@Column
	private BigDecimal tauxBase;
	
	public Grade() {
	}
	public String getCode() {
		return code;
	}
	public Grade setCode(String code) {
		this.code = code;
		return this;
	}
	public BigDecimal getNbHeuresBase() {
		return nbHeuresBase;
	}
	public Grade setNbHeuresBase(BigDecimal nbHeuresBase) {
		this.nbHeuresBase = nbHeuresBase;
		return this;
	}
	public BigDecimal getTauxBase() {
		return tauxBase;
	}
	public Grade setTauxBase(BigDecimal tauxBase) {
		this.tauxBase = tauxBase;
		return this;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	

}

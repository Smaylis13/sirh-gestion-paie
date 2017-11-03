package dev.paie.entite;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
public class Avantage {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String code;
	@Column
	private String nom;
	@Column
	private BigDecimal montant;

	public Avantage() {
	}
	public String getCode() {
		return code;
	}

	public Avantage setCode(String code) {
		this.code = code;
		return this;
	}

	public String getNom() {
		return nom;
	}

	public Avantage setNom(String nom) {
		this.nom = nom;
		return this;
	}

	public BigDecimal getMontant() {
		return montant;
	}

	public Avantage setMontant(BigDecimal montant) {
		this.montant = montant;
		return this;
	}

	public Integer getId() {
		return id;
	}

	public Avantage setId(Integer id) {
		this.id = id;
		return this;
	}
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Avantage co = (Avantage) obj;
        return new EqualsBuilder().append(code, co.code).isEquals() && new CompareToBuilder()
                .append(nom, co.nom).append(montant, co.montant).toComparison() == 0;
    }

}

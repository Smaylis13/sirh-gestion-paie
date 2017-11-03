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
public class Cotisation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String code;
	@Column
	private String libelle;
	@Column
	private BigDecimal tauxSalarial;
	@Column
	private BigDecimal tauxPatronal;
	
	public Cotisation() {
	}
	public String getCode() {
		return code;
	}
	public Cotisation setCode(String code) {
		this.code = code;
		return this;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public Cotisation setLibelle(String libelle) {
		this.libelle = libelle;
		return this;
	}
	public BigDecimal getTauxSalarial() {
		return tauxSalarial;
	}
	public Cotisation setTauxSalarial(BigDecimal tauxSalarial) {
		this.tauxSalarial = tauxSalarial;
		return this;
	}
	public BigDecimal getTauxPatronal() {
		return tauxPatronal;
	}
	public Cotisation setTauxPatronal(BigDecimal tauxPatronal) {
		this.tauxPatronal = tauxPatronal;
		return this;
	}
	public Integer getId() {
		return id;
	}
	public Cotisation setId(Integer id) {
		this.id = id;
		return this;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + ((tauxPatronal == null) ? 0 : tauxPatronal.hashCode());
		result = prime * result + ((tauxSalarial == null) ? 0 : tauxSalarial.hashCode());
		return result;
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
        Cotisation co = (Cotisation) obj;
        return new EqualsBuilder().append(code, co.code).isEquals() && new CompareToBuilder()
                .append(tauxSalarial, co.tauxSalarial).append(tauxPatronal, co.tauxPatronal).toComparison() == 0;
    }
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	


}

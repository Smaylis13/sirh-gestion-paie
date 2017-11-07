package dev.paie.entite;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Periode {
	
	@Override
	public String toString() {
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		//return  simpleDateFormat.format(dateDebut) + " - " + simpleDateFormat.format(dateFin) ;
		return dateDebut + " - " + dateFin;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	

	@Column
	private LocalDate dateDebut;
	@Column
	private LocalDate dateFin;
	
	//@OneToMany
	//List<Bull>
	
	public Periode(Integer id, LocalDate dateDebut, LocalDate dateFin) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
	
	public Periode() {
	}
	public LocalDate getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}
	public LocalDate getDateFin() {
		return dateFin;
	}
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	

}

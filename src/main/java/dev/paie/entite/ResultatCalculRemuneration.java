package dev.paie.entite;

public class ResultatCalculRemuneration {
	private String salaireDeBase;
	private String salaireBrut;
	private String totalRetenueSalarial;
	private String totalCotisationsPatronales;
	private String netImposable;
	private String netAPayer;
	
	public String getSalaireDeBase() {
		return salaireDeBase;
	}
	public String getSalaireBrut() {
		return salaireBrut;
	}
	public String getTotalRetenueSalarial() {
		return totalRetenueSalarial;
	}
	public String getTotalCotisationsPatronales() {
		return totalCotisationsPatronales;
	}
	public String getNetImposable() {
		return netImposable;
	}
	public String getNetAPayer() {
		return netAPayer;
	}
	public void setSalaireDeBase(String salaireDeBase) {
		this.salaireDeBase = salaireDeBase;
	}
	public void setSalaireBrut(String salaireBrut) {
		this.salaireBrut = salaireBrut;
	}
	public void setTotalRetenueSalarial(String totalRetenueSalarial) {
		this.totalRetenueSalarial = totalRetenueSalarial;
	}
	public void setTotalCotisationsPatronales(String totalCotisationsPatronales) {
		this.totalCotisationsPatronales = totalCotisationsPatronales;
	}
	public void setNetImposable(String netImposable) {
		this.netImposable = netImposable;
	}
	public void setNetAPayer(String netAPayer) {
		this.netAPayer = netAPayer;
	}

	
}

package dev.paie.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
@ContextConfiguration(classes = { ServicesConfig.class })
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {

	@Autowired
	private PaieUtils paieUtils;

	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		
		ResultatCalculRemuneration resultatCalculRemuneration = new ResultatCalculRemuneration();
		Grade grade = bulletin.getRemunerationEmploye().getGrade();
		
		String  salaireBrut =
		paieUtils.formaterBigDecimal(grade.getNbHeuresBase().multiply(grade.getTauxBase()).add(bulletin.getPrimeExceptionnelle()));
		BigDecimal salaireBrutBG = new BigDecimal(0);
		salaireBrutBG = grade.getNbHeuresBase().multiply(grade.getTauxBase()).add(bulletin.getPrimeExceptionnelle());
		resultatCalculRemuneration.setSalaireBrut(salaireBrut);
		

		List<Cotisation> list = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables();
		//SOMME(COTISATION_NON_IMPOSABLE.TAUX_SALARIAL*SALAIRE_BRUT)
		BigDecimal sumSalariale = new BigDecimal(0);
		BigDecimal sumPatronale = new BigDecimal(0);
		for(Cotisation c : list){
			if(c.getTauxSalarial()!=null){
				sumSalariale = sumSalariale.add(c.getTauxSalarial().multiply(salaireBrutBG));
			}
			if(c.getTauxPatronal() != null){
				sumPatronale = sumPatronale.add(c.getTauxPatronal().multiply(salaireBrutBG));
			}
		}
		
		resultatCalculRemuneration.setTotalRetenueSalarial(paieUtils.formaterBigDecimal(sumSalariale));
		//TOTAL_COTISATIONS_PATRONALES =  SOMME(COTISATION_NON_IMPOSABLE.TAUX_PATRONAL*SALAIRE_BRUT)

		resultatCalculRemuneration.setTotalCotisationsPatronales(paieUtils.formaterBigDecimal(sumPatronale));
		
		//NET_IMPOSABLE = SALAIRE_BRUT - TOTAL_RETENUE_SALARIALE
		
		BigDecimal net_imposable = salaireBrutBG.add(sumSalariale.negate());
		
		resultatCalculRemuneration.setNetImposable(paieUtils.formaterBigDecimal(net_imposable));
		
		// NET_A_PAYER = NET_IMPOSABLE - SOMME(COTISATION_IMPOSABLE.TAUX_SALARIAL*SALAIRE_BRUT)
		BigDecimal sumCotisImposable = new BigDecimal(0);
		for(Cotisation c : bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsImposables()){
			if(c.getTauxSalarial() != null)
				sumCotisImposable = sumCotisImposable.add(c.getTauxSalarial().multiply(salaireBrutBG));
		}
		BigDecimal netAPayer = net_imposable.add(sumCotisImposable.negate());
		System.out.println(net_imposable);
		System.out.println(sumCotisImposable);
		resultatCalculRemuneration.setNetAPayer(paieUtils.formaterBigDecimal(netAPayer));
		return resultatCalculRemuneration;
	}

}

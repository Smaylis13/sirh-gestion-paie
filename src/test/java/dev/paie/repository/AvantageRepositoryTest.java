package dev.paie.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;

@ContextConfiguration(classes = { ServicesConfig.class })
@RunWith(SpringRunner.class)
public class AvantageRepositoryTest {

	@Autowired private AvantageRepository avantageRepository;
	
	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {

		// TODO sauvegarder un nouvel avantage
		Avantage avantage =  new Avantage().setCode("Avtg1").setCode("CodeAvtg1").setNom("Avantage Noël")
				.setMontant(new BigDecimal("50")).setId(10);
		avantageRepository.save(avantage);
		
		// TODO vérifier qu'il est possible de récupérer le nouvel avantage via la méthode findOne
		Avantage avantageBD = avantageRepository.findOne(10);
		
		assertThat(avantage).isEqualTo(avantageBD);
		avantage.setId(avantageBD.getId());
		
		// TODO modifier un avantage
		
		avantage.setMontant(new BigDecimal("100")).setNom("Avantage fin d'année");
		avantageRepository.save(avantage);		
		
		// TODO vérifier que les modifications sont bien prises en compte via la méthode findOne
		Avantage avantageVerif = avantageRepository.findOne(10);
		
		assertThat(avantage).isEqualTo(avantageVerif);
	}
	
	@Test
	public void test_find_by_code() {
		Avantage a = avantageRepository.findByCode("CodeAvtg1").get(0);
		assertThat(a.getCode()).isEqualTo("CodeAvtg1");
	}
}

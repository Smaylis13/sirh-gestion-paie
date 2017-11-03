package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.entite.Cotisation;
import dev.paie.spring.DataSourceMySQLConfig;
import dev.paie.spring.JpaConfig;

@ContextConfiguration(classes = { JpaConfig.class })
@RunWith(SpringRunner.class)
public class CotisationServiceJpaTest {

	@Autowired private CotisationService cotisationService;
	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
	// TODO sauvegarder une nouvelle cotisation
		Cotisation cotisInit = new Cotisation().setCode("Cotis1").setLibelle("Lib1")
				.setTauxPatronal(new BigDecimal("123")).setTauxSalarial(new BigDecimal("456") );
	cotisationService.sauvegarder(cotisInit);
	
		
		
	// TODO vérifier qu'il est possible de récupérer la nouvelle cotisation via la méthode lister
		Optional <Cotisation> optCotisation = cotisationService.lister().stream().filter(c -> c.equals(cotisInit)).findFirst();
		if( optCotisation.isPresent()){
			assertThat(optCotisation.get()).isEqualTo(cotisInit);
		}
		
		
	// TODO modifier une cotisation
		cotisInit.setCode("CotiMaJ").setLibelle("LibMaJ");
		cotisationService.mettreAJour(cotisInit);
		
		// TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		Optional <Cotisation> optCotisationMaJ = cotisationService.lister().stream().filter(c -> c.equals(cotisInit)).findFirst();
		if( optCotisationMaJ.isPresent()){
			assertThat(optCotisationMaJ.get()).isEqualTo(cotisInit);
		}
		
	
	}
}

package dev.paie.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.ProfilRemunerationRepository;

@Service
@Transactional
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PeriodeRepository periodeRepository;
	/*
	 * @Autowired private EntrepriseRepository entrepriseRepository;
	 * 
	 * 
	 * 
	 * @Autowired private GradeRepository gradeRepository ;
	 * 
	 * @Autowired private ProfilRemunerationRepository
	 * profilRemunerationRepository;
	 */

	@Override
	public void initialiser() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "entreprises.xml", "grades.xml","profils-remuneration.xml", "cotisations-imposables.xml", "cotisations-non-imposables.xml" });


		Stream.of(Cotisation.class, Entreprise.class, Grade.class, ProfilRemuneration.class).forEach(
				classe -> context.getBeansOfType(classe).values().stream().forEach(object -> em.persist(object)));

		for (int i = 1; i <= 12; i++) {
			LocalDate initial = LocalDate.of(2017, i, 13);
			periodeRepository
					.save(new Periode(i, initial.withDayOfMonth(1), initial.withDayOfMonth(initial.lengthOfMonth())));
		}

	}

}
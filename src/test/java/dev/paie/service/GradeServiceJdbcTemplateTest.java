package dev.paie.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.entite.Grade;
import dev.paie.spring.DataSourceMySQLConfig;
import dev.paie.util.PaieUtils;

@ContextConfiguration(classes = { DataSourceMySQLConfig.class })
@RunWith(SpringRunner.class)
public class GradeServiceJdbcTemplateTest {

	@Autowired
	GradeServiceJdbcTemplate gradeServiceJdbcTemplate;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		// TODO sauvegarder un nouveau grade

		 Grade grade = new Grade();
		 grade.setCode("MyCode");
		 grade.setNbHeuresBase(new BigDecimal("156"));
		 grade.setTauxBase(new BigDecimal("143"));
		gradeServiceJdbcTemplate.sauvegarder(grade);

		// TODO vérifier qu'il est possible de récupérer le nouveau grade via la méthode lister
		List<Grade> gradeList = gradeServiceJdbcTemplate.lister();
		/*Optional<Grade> optGrade = gradeList.stream().filter(p -> p.getCode().equals("MyCode")).collect(Collectors.toList()).get(0);
		
		if( optGrade.isPresent()){
			grade.setId(optGrade.get().getId());
			assert(optGrade.get().getId().equals(grade.getId()));
		}*/
		
		// TODO modifier un grade
		grade.setNbHeuresBase(new BigDecimal("854")).setTauxBase(new BigDecimal("999"));
		
		gradeServiceJdbcTemplate.mettreAJour(grade);
		
		// TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		List<Grade> grades = gradeServiceJdbcTemplate.lister();
		//grades.stream().filter(p -> p.getCode().equals("MyCode"))
		for(Grade g : grades){
			System.out.println(g.getCode());
			if (g.getCode().equals("MyCode")){
				assert(g.getNbHeuresBase().equals(new BigDecimal("854")));
				assert(g.getTauxBase().equals(new BigDecimal("999")));
			}
		}
	
	}

}

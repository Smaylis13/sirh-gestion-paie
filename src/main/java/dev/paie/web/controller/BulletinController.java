package dev.paie.web.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Periode;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationService;

@Controller
@RequestMapping("/bulletins")
public class BulletinController {

	@Autowired
	private CalculerRemunerationService calculerRemunerationService;
	@Autowired
	private PeriodeRepository periodeRepository;

	@Autowired
	private RemunerationEmployeRepository remunerationEmployeRepository;

	@Autowired
	private BulletinSalaireRepository bulletinSalaireRepository;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerFormBulletin() {
		ModelAndView mv = new ModelAndView();

		mv.addObject("periodes", periodeRepository.findAll());
		mv.addObject("matricules", remunerationEmployeRepository.findMatricules());

		mv.setViewName("bulletins/creerBulletin");
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public ModelAndView creerBulletin(@RequestParam("matricule") String pMatricule,
			@RequestParam("prime")String pPrime,@RequestParam("periode") String pPeriode){
		ModelAndView mv = new ModelAndView();

		Periode periode = periodeRepository.findPeriodeByDateDebutAndDateFin(LocalDate.parse(pPeriode.substring(0,10)), LocalDate.parse(pPeriode.substring(13)));
		BigDecimal prime = new BigDecimal(0);
		if(!pPrime.isEmpty()){
			prime = prime.add(new BigDecimal(pPrime));
		}
		RemunerationEmploye remuneration = remunerationEmployeRepository.findAll().stream().filter(r -> r.getMatricule().equals(pMatricule)).findFirst().get();
		BulletinSalaire bulletinSalaire = new BulletinSalaire().setPeriode(periode).setPrimeExceptionnelle(prime).setRemunerationEmploye(remuneration);
		bulletinSalaire.setDateCreation(new Timestamp(System.currentTimeMillis()));
		bulletinSalaireRepository.save(bulletinSalaire);
		List<BulletinSalaire> bulletinSalaires = bulletinSalaireRepository.findAll();
		
		//calcul des resulatas
		List<ResultatCalculRemuneration> resultats = new ArrayList<>();
		
		bulletinSalaires.stream().forEach(bul -> resultats.add(calculerRemunerationService.calculer(bul)));
		mv.addObject("bulletins", bulletinSalaires );
		mv.addObject("resultats", resultats );

		
		mv.setViewName("bulletins/listerBulletin");
		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView listerBulletin() {
		ModelAndView mv = new ModelAndView();
		// les bulletins de salaire
		List<BulletinSalaire> bulletinSalaires = bulletinSalaireRepository.findAll();
		mv.addObject("bulletins", bulletinSalaires );
		
		//calcul des resulatas
		List<ResultatCalculRemuneration> resultats = new ArrayList<>();
		
		bulletinSalaires.stream().forEach(bul -> resultats.add(calculerRemunerationService.calculer(bul)));
		
		mv.addObject("bulletins", bulletinSalaires );
		mv.addObject("resultats", resultats );
		
		mv.setViewName("bulletins/listerBulletin");
		return mv;
	}

}

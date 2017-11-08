package dev.paie.web.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired
	private EntrepriseRepository entrepriseRepository;

	@Autowired
	private GradeRepository gradeRepository;
	@Autowired
	private RemunerationEmployeRepository  remunerationEmployeRepository;
	@Autowired
	ProfilRemunerationRepository profilRemunerationRepository;

//	@Autowired
//	private ProfilRemunerationRepository profilRepository;
//	 @RequestMapping(method = RequestMethod.GET, path = "/creer")
//	 public ModelAndView creerEmploye() {
//	 ModelAndView mv = new ModelAndView();
//	 mv.setViewName("employes/creerEmploye");
//	 mv.addObject("prefixMatricule", "M00");
//	 return mv;
//	 }

	/*
	 * Formulaire créer un Employe
	 * Que pour les admins
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")// activer dans SecurityConfig
	public ModelAndView createForm() {
		ModelAndView mav = new ModelAndView();
		// Récup bdd 
		List<Entreprise> entreprises = entrepriseRepository.findAll();
		List<ProfilRemuneration> profils = profilRemunerationRepository.findAll();
		List<Grade> grades = gradeRepository.findAll();

		// send to view 
		mav.addObject("entreprises", entreprises);
		mav.addObject("profils", profils);
		mav.addObject("grades", grades);

		mav.setViewName("employes/creerEmploye");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView creerEmploye(@RequestParam("matricule") String pMatricule,
			@RequestParam("profil") String pProfil, @RequestParam("grade") String pGrade,@RequestParam("entreprise") String pEntreprise) {
		ModelAndView mav = new ModelAndView();
		RemunerationEmploye employe = null;
		if (pMatricule != null && pProfil != null && pGrade != null) {
			// les find.... 
			Grade grade = gradeRepository.findByCode(pGrade);
			ProfilRemuneration profil = profilRemunerationRepository.findByCode(pProfil);
			Entreprise entreprise = entrepriseRepository.findByDenomination(pEntreprise);
			
			// affectations...
			employe = new RemunerationEmploye();
			employe.setMatricule(pMatricule).setEntreprise(entreprise).setGrade(grade).setProfilRemuneration(profil);
			employe.setDateCreation(new Timestamp(System.currentTimeMillis()));
			remunerationEmployeRepository.save(employe);
		}
		List<RemunerationEmploye> remunerationEmployes = remunerationEmployeRepository.findAll();
		
		mav.addObject("remunerationEmployes", remunerationEmployes);

		mav.setViewName("employes/listerEmploye");
		return mav;

	}
	
	/**
	 * Methode pour lister les employes 
	 * pour les admin et user
	 * 
	 * @return
	 */
	@Secured({"ROLE_ADMINISTRATEUR","ROLE_UTILISATEUR"})
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView listerEmploye() {
		ModelAndView mav = new ModelAndView();
		List<RemunerationEmploye> remunerationEmployes = remunerationEmployeRepository.findAll();
		mav.addObject("remunerationEmployes", remunerationEmployes);

		mav.setViewName("employes/listerEmploye");
		return mav;
	}

}

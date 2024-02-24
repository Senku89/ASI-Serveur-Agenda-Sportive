package fr.upjv.agendasportive.controller;

import fr.upjv.agendasportive.models.Cours;
import fr.upjv.agendasportive.models.Inscription;
import fr.upjv.agendasportive.models.Utilisateur;
import fr.upjv.agendasportive.repositories.CoursRepository;
import fr.upjv.agendasportive.repositories.InscriptionRepository;
import fr.upjv.agendasportive.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cours")
public class CoursController {

    private final CoursRepository coursRepository;
    private final InscriptionRepository inscriptionRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired // Injection des dependances de maniere transparente dans le constructeur
    public CoursController(CoursRepository coursRepository, InscriptionRepository inscriptionRepository, UtilisateurRepository utilisateurRepository) {
        this.coursRepository = coursRepository;
        this.inscriptionRepository = inscriptionRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    // Renvoie la liste de tous les cours
    @GetMapping("/all")
    public ResponseEntity<List<Cours>> getAllCours() {
        List<Cours> listCours = coursRepository.findAll();
        return ResponseEntity.ok().body(listCours);
    }

    /*
    // Renvoie les cours inscrits d'un utilisateur
    @GetMapping("/utilisateur/inscrits/{userId}")
    public ResponseEntity<List<Cours>> getCoursByUtilisateur(@PathVariable int userId) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId);
        if (utilisateur == null) {
            return ResponseEntity.badRequest().body(null); // Utilisateur non trouvé
        }
        List<Inscription> cours = inscriptionRepository.findByUtilisateur(utilisateur);
        return ResponseEntity.ok().body(cours);
    }
    */

    /*
    // Renvoie les cours non inscrits d'un utilisateur
    @GetMapping("/utilisateur/non-inscrits/{userId}")
    public ResponseEntity<List<Cours>> getCoursNonInscrits(@PathVariable int userId) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId);
        if (utilisateur == null) {
            return ResponseEntity.badRequest().body(null); // Utilisateur non trouvé
        }
        List<Cours> cours = coursRepository.findCoursNonInscrits(utilisateur.getId());
        return ResponseEntity.ok().body(cours);
    }
    */
}

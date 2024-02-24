package fr.upjv.agendasportive.controller;

import fr.upjv.agendasportive.models.Cours;
import fr.upjv.agendasportive.models.Inscription;
import fr.upjv.agendasportive.models.InscriptionRequest;
import fr.upjv.agendasportive.models.Utilisateur;
import fr.upjv.agendasportive.repositories.CoursRepository;
import fr.upjv.agendasportive.repositories.InscriptionRepository;
import fr.upjv.agendasportive.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class InscriptionController {

    private final InscriptionRepository inscriptionRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final CoursRepository coursRepository;

    @Autowired
    public InscriptionController(InscriptionRepository inscriptionRepository, UtilisateurRepository utilisateurRepository, CoursRepository coursRepository) {
        this.inscriptionRepository = inscriptionRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.coursRepository = coursRepository;
    }

    @PostMapping("/ajouter-inscription")
    public ResponseEntity<?> ajouterInscription(@RequestBody InscriptionRequest inscriptionRequest) {

        // Trouver l'utilisateur et le cours (gerer si findById retourne null)
        Optional<Utilisateur> optionalUtilisateur = Optional.ofNullable(utilisateurRepository.findById(inscriptionRequest.getUserId()));
        Optional<Cours> optionalCours = Optional.ofNullable(coursRepository.findById(inscriptionRequest.getCoursId()));

        if (optionalUtilisateur.isEmpty() || optionalCours.isEmpty()) {
            return ResponseEntity.badRequest().body("Utilisateur ou cours non trouvé...");
        }

        // Stocker variable tmp
        Utilisateur utilisateur = optionalUtilisateur.get();
        Cours cours = optionalCours.get();

        // Verifier si l'utilisateur est déjà inscrit à ce cours
        List<Inscription> existantsInscription = inscriptionRepository.findByUtilisateurAndCours(utilisateur, cours);
        if (!existantsInscription.isEmpty()) {
            return ResponseEntity.badRequest().body("Utilisateur deja inscrit");
        }

        // Création d'une nouvelle inscription
        Inscription inscription = new Inscription();
        inscription.setUtilisateur(utilisateur);
        inscription.setCours(cours);
        inscriptionRepository.save(inscription);

        // Ajouter l'inscription à la liste des inscriptions de l'utilisateur
        utilisateur.getInscriptions().add(inscription);
        utilisateurRepository.save(utilisateur); // Mettre à jour

        // Reponse OK → Inscription realisée
        return ResponseEntity.ok().body("Inscription realise par l'utilisateur");
    }

    @DeleteMapping("/supprimer-inscription/{inscriptionId}")
    public ResponseEntity<?> supprimerInscription(@PathVariable int inscriptionId) {

        // Vérifier si l'inscription existe
        Optional<Inscription> optionalInscription = Optional.ofNullable(inscriptionRepository.findById(inscriptionId));
        if (optionalInscription.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inscription non trouvée");
        }

        // Stocker variable tmp
        Inscription inscription = optionalInscription.get();

        // Supprimer l'inscription de la liste des inscriptions de l'utilisateur
        Utilisateur utilisateur = inscription.getUtilisateur();
        utilisateur.getInscriptions().remove(inscription);
        utilisateurRepository.save(utilisateur);

        // Supprimer l'inscription
        inscriptionRepository.delete(optionalInscription.get());
        return ResponseEntity.ok().body("Inscription supprimée avec succès");
    }
}
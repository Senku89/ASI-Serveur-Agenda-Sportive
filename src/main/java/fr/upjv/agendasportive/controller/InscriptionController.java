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
@RequestMapping("/api/inscriptions")
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

    /**
     * Obtenir une inscription par son ID
     *
     * @param inscriptionId L'ID de l'inscription à rechercher
     * @return ResponseEntity<?> Le résultat de l'opération de recherche de l'inscription
     *         Si l'inscription est trouvée, retourne un Response avec le code 200 (OK)
     *         et l'inscription dans le body
     *         Si l'inscription spécifiée n'existe pas, retourne un ResponseEntity avec le code 404 (Non trouvé)
     *         et un message d'erreur dans le body
     */
    @GetMapping("/search/{inscriptionId}")
    public ResponseEntity<?> getInscriptionById(@PathVariable int inscriptionId) {
        // Recherche de l'inscription par son ID
        Optional<Inscription> optionalInscription = Optional.ofNullable(inscriptionRepository.findById(inscriptionId));
        if (optionalInscription.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inscription non trouvée");
        }

        // Renvoyer l'inscription trouvée dans la réponse
        return ResponseEntity.ok(optionalInscription.get());
    }

    /**
     * Ajouter une inscription à un cours pour un utilisateur
     *
     * @param inscriptionRequest Les détails de l'inscription à ajouter (ID de l'utilisateur et ID du cours)
     * @return ResponseEntity<?> Le résultat de l'opération d'ajout d'inscription
     *         Si l'inscription est ajoutée avec succès, retourne un ResponseEntity avec le code 200 (OK) et
     *         un message de confirmation dans le body
     *         -
     *         Si l'utilisateur ou le cours spécifié n'existe pas, retourne un Response avec
     *         le code 400 (Mauvaise requête) et un message d'erreur dans le body
     *         Si l'utilisateur est déjà inscrit au cours spécifié, retourne un ResponseEntity avec
     *         le code 400 (Mauvaise requête) et un message d'erreur dans le body
     */
    @PostMapping("/ajouter")
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

        // Ajouter le cours à la liste des inscriptions de l'utilisateur
        utilisateur.getInscriptions().add(inscription);
        utilisateurRepository.save(utilisateur); // Mettre à jour

        // Reponse OK → Inscription realisée
        return ResponseEntity.ok().body("Inscription realise par l'utilisateur");
    }

    /**
     * Pour supprimer une inscription spécifiée par son ID
     *
     * @param inscriptionId L'ID de l'inscription à supprimer
     * @return ResponseEntity<?> Le résultat de l'opération de suppression de l'inscription
     *         Si l'inscription est supprimée avec succès, retourne un Response avec le code 200 (OK)
     *         et un message de confirmation dans le body
     *         Si l'inscription spécifiée n'existe pas, retourne un Response avec le code 404 (Non trouvé)
     *         et un message d'erreur dans le body
     */
    @DeleteMapping("/supprimer/{inscriptionId}")
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
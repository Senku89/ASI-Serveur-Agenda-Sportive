package fr.upjv.agendasportive.controller;

import fr.upjv.agendasportive.models.Cours;
import fr.upjv.agendasportive.models.Inscription;
import fr.upjv.agendasportive.models.Utilisateur;
import fr.upjv.agendasportive.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    /**
     * Obtient un utilisateur par son identifiant.
     *
     * @param userId L'identifiant de l'utilisateur à rechercher
     * @return ResponseEntity<Utilisateur> Un Response contenant l'utilisateur trouvé
     *         Si aucun utilisateur correspondant à l'identifiant n'est trouvé, retourne un ResponseEntity avec le code
     *         404 (Non trouvé)
     *         Si un utilisateur est trouvé, retourne un Response avec le code 200 (OK) et l'utilisateur
     *         dans le body
     */
    @GetMapping("/search/id/{userId}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable int userId) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId);
        if (utilisateur == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(utilisateur);
    }


    /**
     * Obtient un utilisateur par son nom.
     *
     * @param nom Le nom de l'utilisateur à rechercher
     * @return ResponseEntity<Utilisateur> Un Response contenant l'utilisateur trouvé
     *         Si aucun utilisateur correspondant au nom n'est trouvé, retourne un ResponseEntity avec le code 404 (Non trouvé)
     *         Si un utilisateur trouvé, retourne un Response avec le code 200 (OK) et
     *         l'utilisateur dans le body
     */
    @GetMapping("/search/name/{nom}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable String nom) {
        List<Utilisateur> userList = utilisateurRepository.findByNom(nom);
        if (!userList.isEmpty()) {
            Utilisateur user = userList.getFirst();
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Obtient la liste des cours auxquels un utilisateur est inscrit
     *
     * @param userId L'identifiant unique de l'utilisateur
     * @return ResponseEntity<List<Cours>> Liste des cours inscrits de l'utilisateur
     *         Si l'utilisateur n'est pas trouvé, retourne un Response avec le code d'état 404 (Non trouvé)
     *         Si l'utilisateur est trouvé, retourne un Response avec le code d'état 200 (OK) et la liste
     *         des inscriptions dans le body
     */
    @GetMapping("/inscrits/{userId}")
    public ResponseEntity<List<Cours>> getCoursByUtilisateur(@PathVariable int userId) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId);
        if (utilisateur == null) {
            return ResponseEntity.notFound().build(); // Utilisateur non trouvé
        }

        List<Inscription> inscriptions = utilisateur.getInscriptions();
        List<Cours> coursList = new ArrayList<>();

        // Récupérer uniquement les informations sur les cours
        for (Inscription inscription : inscriptions) {
            coursList.add(inscription.getCours());
        }

        return ResponseEntity.ok().body(coursList);
    }

    /** PAS EFFICIENT
     * Obtient la liste des cours inscrits pour une semaine spécifiée à partir de la date de début de la semaine
     * d'un utilisateur
     *
     * @param startDate La date de début de la semaine pour laquelle récupérer les cours
     * @param userId    L'Identifiant de l'utilisateur pour réaliser la recherche
     * @return ResponseEntity<List<Cours>> La liste des cours pour la semaine spécifiée
     *         Si aucun utilisateur trouvé, retourne un Response avec le code 404 (NOT FOUND)
     *         Si des cours sont trouvés pour la semaine donnée, retourne un Response avec le code 200 (OK)
     *         et la liste des cours dans le body
     *         Si aucun cours n'est trouvé pour la semaine donnée, retourne un Response
     *         avec le code 204 (Aucun contenu)
     */
    @GetMapping("/search/semaine/{startDate}/{userId}")
    public ResponseEntity<List<Cours>> getCoursByWeek(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                      @PathVariable int userId) {
        // Définir la date de fin de la semaine (7 jours plus tard)
        LocalDate endDate = startDate.plusDays(6);
        // 6 jours de plus pour avoir la fin de la semaine

        // Accéder à l'utilisateur à partir de son ID
        Utilisateur utilisateur = utilisateurRepository.findById(userId);

        if (utilisateur == null) {
            return ResponseEntity.notFound().build(); // Utilisateur non trouvé
        }

        // Filtrer les cours en fonction de la semaine définie et des cours auxquels l'utilisateur est inscrit
        List<Cours> coursOfWeek = new ArrayList<>();
        for (Inscription inscription : utilisateur.getInscriptions()) {
            LocalDate coursDate = inscription.getCours().getHoraire(); // No need for conversion

            if (coursDate.isAfter(startDate.minusDays(1)) && coursDate.isBefore(endDate.plusDays(1))) {
                coursOfWeek.add(inscription.getCours());
            }
        }

        // Vérifier si des cours sont trouvés pour cette semaine
        if (coursOfWeek.isEmpty()) {
            return ResponseEntity.noContent().build(); // Aucun cours trouvé pour cette semaine
        }

        // Retourner les cours filtrés dans la réponse
        return ResponseEntity.ok().body(coursOfWeek);
    }

}
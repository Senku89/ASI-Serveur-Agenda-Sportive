package fr.upjv.agendasportive.controller;

import fr.upjv.agendasportive.models.Cours;
import fr.upjv.agendasportive.models.Utilisateur;
import fr.upjv.agendasportive.repositories.CoursRepository;
import fr.upjv.agendasportive.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/cours")
public class CoursController {

    private final CoursRepository coursRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired // Injection des dépendances de manière transparente dans le constructeur
    public CoursController(CoursRepository coursRepository, UtilisateurRepository utilisateurRepository) {
        this.coursRepository = coursRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    /**
     * Récupère la liste de tous les cours disponibles
     *
     * @return ResponseEntity<List<Cours>> La liste de tous les cours
     *         Si la liste de cours est récupérée avec succès, retourne un Response avec le code 200 (OK)
     *         et la liste de cours dans le body
     *
     *  curl -X GET -i http://localhost:8080/api/cours/all
     */
    @GetMapping("/search/all")
    public ResponseEntity<List<Cours>> getAllCours() {
        List<Cours> listCours = coursRepository.findAll();
        return ResponseEntity.ok().body(listCours);
    }

    /**
     * Récupère un cours spécifié par son ID
     *
     * @param idCours L'ID du cours à récupérer.
     * @return ResponseEntity<Cours> Le cours spécifié
     *         Si le cours est récupéré avec succès, retourne un ResponseEntity avec le code 200 (OK)
     *         et le cours dans le body
     *         Si le cours spécifié n'existe pas, retourne un ResponseEntity avec le code 404 (Non trouvé)
     *  -
     *  Exemple de requête : curl -X GET -i http://localhost:8080/api/cours/{idCours}
     */
    @GetMapping("/search/{idCours}")
    public ResponseEntity<Cours> getCoursById(@PathVariable int idCours) {
        Cours cours = coursRepository.findById(idCours);
        if (cours == null) {
            return ResponseEntity.notFound().build(); // Cours non trouvé
        }
        return ResponseEntity.ok().body(cours);
    }

    /**
     * Récupère la liste des cours pour une semaine spécifiée à partir de la date de début de la semaine
     *
     * @param startDate La date de début de la semaine pour laquelle récupérer les cours
     * @return ResponseEntity<List<Cours>> La liste des cours pour la semaine spécifiée
     *         Si des cours sont trouvés pour la semaine donnée, retourne un Response avec le code 200 (OK)
     *         et la liste des cours dans le body
     *         Si aucun cours n'est trouvé pour la semaine donnée, retourne un Response avec
     *         le code 204 (Aucun contenu)
     */
    @GetMapping("/search/semaine/{startDate}")
    public ResponseEntity<List<Cours>> getCoursByWeek(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        // Définir la date de fin de la semaine (7 jours plus tard)
        LocalDate endDate = startDate.plusDays(6);
        // 6 jours de plus pour avoir la fin de la semaine

        // Filtrer les cours en fonction de la semaine définie
        List<Cours> coursOfWeek = coursRepository.findByHoraireBetween(startDate.atStartOfDay(),
                endDate.plusDays(1).atStartOfDay());
        // Ajouter 1 jour à la date de fin pour inclure tous les cours de la semaine

        // Vérifier si des cours sont trouvés pour cette semaine
        if (coursOfWeek.isEmpty()) {
            return ResponseEntity.noContent().build(); // Aucun cours trouvé pour cette semaine
        }

        // Retourner les cours filtrés dans la réponse
        return ResponseEntity.ok().body(coursOfWeek);
    }
}

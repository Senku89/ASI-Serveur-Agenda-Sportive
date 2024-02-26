package fr.upjv.agendasportive.controller;

import fr.upjv.agendasportive.models.LoginRequest;
import fr.upjv.agendasportive.models.Utilisateur;
import fr.upjv.agendasportive.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    // Field Injection is not recommended so I didn't use Autowired
    //@Autowired // Inject dependency into beans
    //private UtilisateurRepository utilisateurRepository;

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public LoginController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    /**
     * Authentification d'un utilisateur
     *
     * @param loginRequest Les informations de connexion de l'utilisateur (nom d'utilisateur et mot de passe)
     * @return ResponseEntity<?> Un ResponseEntity contenant les résultats de l'authentification
     *         Si l'authentification réussit, retourne un Response avec le code 200 (OK) et
     *         les détails de l'utilisateur dans le body
     *         Si l'authentification échoue en raison d'un nom d'utilisateur ou d'un mot de passe incorrect,
     *         retourne un Response avec le code 401 (Non autorisé) et un message d'erreur dans le body
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUtilisateur(@RequestBody LoginRequest loginRequest) {
        List<Utilisateur> userList = utilisateurRepository.findByNom(loginRequest.getUsername());
        if (!userList.isEmpty()) {
            Utilisateur user = userList.getFirst();
            if (user.getMdp().equals(loginRequest.getPassword())) {
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("success", true); // Ajouter le champ "success"
                responseBody.put("id", user.getId());
                responseBody.put("nom", user.getNom());
                return ResponseEntity.ok().body(responseBody); // OK (200) avec l'utilisateur en cas de succès
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur ou Mot de Passe Invalide");
        // UNAUTHORIZED (401) en cas d'échec
    }
}
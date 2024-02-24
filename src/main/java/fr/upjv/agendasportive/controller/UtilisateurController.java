package fr.upjv.agendasportive.controller;

import fr.upjv.agendasportive.models.Utilisateur;
import fr.upjv.agendasportive.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping("/search/{userId}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable int userId) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId);
        if (utilisateur == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(utilisateur);
    }

    @GetMapping("/search/{nom}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable String nom) {
        List<Utilisateur> userList = utilisateurRepository.findByNom(nom);
        if (!userList.isEmpty()) {
            Utilisateur user = userList.getFirst();
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.notFound().build();
    }
}
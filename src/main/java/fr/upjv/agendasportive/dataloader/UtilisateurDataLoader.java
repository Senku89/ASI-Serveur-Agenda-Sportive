package fr.upjv.agendasportive.dataloader;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import fr.upjv.agendasportive.models.Utilisateur;
import fr.upjv.agendasportive.repositories.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;

@Component
@Order(1)
public class UtilisateurDataLoader implements CommandLineRunner {
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurDataLoader(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public void run(String... args) throws Exception { // String... args represente une parametre varargs

        // Ajouter des utilisateurs
        Utilisateur utilisateur1 = new Utilisateur("Boss", "solidsnake", 32);
        Utilisateur utilisateur2 = new Utilisateur("Otacon", "anime", 32);
        Utilisateur utilisateur3 = new Utilisateur("Raiden", "rulesofnature", 34);

        // Sauvegarde
        utilisateurRepository.save(utilisateur1);
        utilisateurRepository.save(utilisateur2);
        utilisateurRepository.save(utilisateur3);
    }
}

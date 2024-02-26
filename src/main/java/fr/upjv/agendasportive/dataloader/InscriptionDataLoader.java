package fr.upjv.agendasportive.dataloader;

import fr.upjv.agendasportive.models.Cours;
import fr.upjv.agendasportive.models.Inscription;
import fr.upjv.agendasportive.models.Utilisateur;
import fr.upjv.agendasportive.repositories.CoursRepository;
import fr.upjv.agendasportive.repositories.InscriptionRepository;
import fr.upjv.agendasportive.repositories.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class InscriptionDataLoader implements CommandLineRunner {

    private final UtilisateurRepository utilisateurRepository;
    private final CoursRepository coursRepository;
    private final InscriptionRepository inscriptionRepository;

    public InscriptionDataLoader(UtilisateurRepository utilisateurRepository,
                                 CoursRepository coursRepository,
                                 InscriptionRepository inscriptionRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.coursRepository = coursRepository;
        this.inscriptionRepository = inscriptionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Obtenir les utilisateurs et les cours
        Utilisateur utilisateur1 = utilisateurRepository.findById(1);
        Utilisateur utilisateur2 = utilisateurRepository.findById(2);
        Utilisateur utilisateur3 = utilisateurRepository.findById(3);

        Cours cours1 = coursRepository.findById(1);
        Cours cours2 = coursRepository.findById(2);
        Cours cours3 = coursRepository.findById(3);

        // Ajouter des inscriptions
        addInscriptions(utilisateur1, cours1); //id 1
        addInscriptions(utilisateur2, cours1); //id 2
        addInscriptions(utilisateur3, cours2); //id 3
        addInscriptions(utilisateur1, cours3); //id 4
        addInscriptions(utilisateur2, cours3); //id 5

    }

    // Ajouter une inscription
    private void addInscriptions(Utilisateur utilisateur, Cours cours) {
        Inscription inscription = new Inscription();
        inscription.setUtilisateur(utilisateur);
        inscription.setCours(cours);
        inscriptionRepository.save(inscription);

        // Ajouter l'inscription à la liste des inscriptions de l'utilisateur
        utilisateur.getInscriptions().add(inscription);
        utilisateurRepository.save(utilisateur); // Mettre à jour l'utilisateur avec la nouvelle inscription
    }
}


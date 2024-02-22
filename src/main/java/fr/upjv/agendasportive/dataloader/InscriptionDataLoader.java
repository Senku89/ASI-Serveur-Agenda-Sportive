package fr.upjv.agendasportive.dataloader;

import fr.upjv.agendasportive.models.Cours;
import fr.upjv.agendasportive.models.Inscription;
import fr.upjv.agendasportive.models.Utilisateur;
import fr.upjv.agendasportive.repositories.CoursRepository;
import fr.upjv.agendasportive.repositories.InscriptionRepository;
import fr.upjv.agendasportive.repositories.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
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

        // Obtenez des utilisateurs et des cours
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

    private void addInscriptions(Utilisateur utilisateur, Cours cours) {
        Inscription inscription = new Inscription();
        inscription.setUtilisateur(utilisateur);
        inscription.setCours(cours);
        inscriptionRepository.save(inscription);
    }
}

/*
        // Ajouter des inscriptions
        Inscription inscription1 = new Inscription();
        inscription1.setUtilisateur(utilisateur1);
        inscription1.setCours(cours1);
        inscriptionRepository.save(inscription1);

        Inscription inscription2 = new Inscription();
        inscription2.setUtilisateur(utilisateur2);
        inscription2.setCours(cours1);
        inscriptionRepository.save(inscription2);

        Inscription inscription3 = new Inscription();
        inscription3.setUtilisateur(utilisateur3);
        inscription3.setCours(cours2);
        inscriptionRepository.save(inscription3);

        Inscription inscription4 = new Inscription();
        inscription4.setUtilisateur(utilisateur1);
        inscription4.setCours(cours3);
        inscriptionRepository.save(inscription4);

        Inscription inscription5 = new Inscription();
        inscription5.setUtilisateur(utilisateur2);
        inscription5.setCours(cours3);
        inscriptionRepository.save(inscription5);
 */


package fr.upjv.agendasportive;

import fr.upjv.agendasportive.models.Cours;
import fr.upjv.agendasportive.models.Inscription;
import fr.upjv.agendasportive.models.Utilisateur;
import fr.upjv.agendasportive.repositories.CoursRepository;
import fr.upjv.agendasportive.repositories.InscriptionRepository;
import fr.upjv.agendasportive.repositories.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EntityScan({"fr.upjv.applicationsportive", "fr.upjv.agendasportive.models"})
public class AgendaSportiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgendaSportiveApplication.class, args);
    }

    /*
    @Bean // A java object that is instantiated, assembled, and managed by the Spring IoC container (IoC = Inversion of Control)
    public CommandLineRunner demo(UtilisateurRepository utilisateurRepository, CoursRepository coursRepository, InscriptionRepository inscriptionRepository) {
        return args -> {
            // Ajouter des utilisateurs
            Utilisateur utilisateur1 = new Utilisateur("Boss", "solidsnake", 32);
            Utilisateur utilisateur2 = new Utilisateur("Otacon", "anime", 32);
            Utilisateur utilisateur3 = new Utilisateur("Raiden", "rulesofnature", 34);

            utilisateurRepository.save(utilisateur1);
            utilisateurRepository.save(utilisateur2);
            utilisateurRepository.save(utilisateur3);

            // Ajouter des cours
            Cours cours1 = new Cours("Yoga", new Date(), "Salle A", "Session de yoga", "Instructeur1");
            Cours cours2 = new Cours("Pilates", new Date(), "Salle B", "Session de pilates", "Instructeur2");
            Cours cours3 = new Cours("Zumba", new Date(), "Salle C", "Cours de danse", "Instructeur3");

            coursRepository.save(cours1);
            coursRepository.save(cours2);
            coursRepository.save(cours3);


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
        };
    }
    */
}

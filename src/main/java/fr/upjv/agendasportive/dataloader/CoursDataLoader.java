package fr.upjv.agendasportive.dataloader;

import fr.upjv.agendasportive.models.Cours;
import fr.upjv.agendasportive.repositories.CoursRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Order(1)
public class CoursDataLoader implements CommandLineRunner {

    private final CoursRepository coursRepository;


    public CoursDataLoader(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Définir Dates et heures spécifiques pour les cours
        LocalDateTime dateCours1 = LocalDateTime.of(2024, 2, 21, 9, 0); // 21 Février 2024, 09:00
        LocalDateTime dateCours2 = LocalDateTime.of(2024, 2, 22, 10, 30); // 22 Février 2024, 10:30
        LocalDateTime dateCours3 = LocalDateTime.of(2024, 2, 23, 14, 15); // 23 Février 2024, 14:15
        LocalDateTime dateCours4 = LocalDateTime.of(2024, 2, 24, 11, 0); // 24 Février 2024, 11:00
        LocalDateTime dateCours5 = LocalDateTime.of(2024, 2, 25, 16, 45); // 25 Février 2024, 16:45
        LocalDateTime dateCours6 = LocalDateTime.of(2024, 2, 26, 18, 30); // 26 Février 2024, 18:30
        LocalDateTime dateCours7 = LocalDateTime.of(2024, 2, 27, 12, 0); // 27 Février 2024, 12:00
        LocalDateTime dateCours8 = LocalDateTime.of(2024, 2, 28, 17, 0); // 28 Février 2024, 17:00

        // Ajouter des cours
        Cours cours1 = new Cours("Yoga", dateCours1, "Salle A", "Session de yoga", "Senator Armstrong");
        Cours cours2 = new Cours("Pilates", dateCours2, "Salle B", "Session de pilates", "Colonel Campbell");
        Cours cours3 = new Cours("Zumba", dateCours3, "Salle C", "Cours de danse", "Revolver Ocelot");
        Cours cours4 = new Cours("Boxe", dateCours4, "Salle D", "Entraînement de boxe", "Solid Snake");
        Cours cours5 = new Cours("Natation", dateCours5, "Piscine", "Séance de natation", "Sniper Wolf");
        Cours cours6 = new Cours("Musculation", dateCours5, "Salle de musculation", "Entraînement de musculation", "Liquid Snake");
        Cours cours7 = new Cours("Escalade", dateCours6, "Mur d'escalade", "Session d'escalade", "Grey Fox");
        Cours cours8 = new Cours("Taekwondo", dateCours6, "Dojo", "Cours de Taekwondo", "Vamp");
        Cours cours9 = new Cours("Kickboxing", dateCours7, "Salle F", "Entraînement de kickboxing", "Fortune");
        Cours cours10 = new Cours("Judo", dateCours8, "Dojo", "Cours de judo", "Quiet");

        // Enregistrer les cours
        coursRepository.save(cours1);
        coursRepository.save(cours2);
        coursRepository.save(cours3);
        coursRepository.save(cours4);
        coursRepository.save(cours5);
        coursRepository.save(cours6);
        coursRepository.save(cours7);
        coursRepository.save(cours8);
        coursRepository.save(cours9);
        coursRepository.save(cours10);
    }
}
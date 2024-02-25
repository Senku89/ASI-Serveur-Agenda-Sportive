package fr.upjv.agendasportive.dataloader;

import fr.upjv.agendasportive.models.Cours;
import fr.upjv.agendasportive.repositories.CoursRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Component
@Order(1)
public class CoursDataLoader implements CommandLineRunner {

    private final CoursRepository coursRepository;


    public CoursDataLoader(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Définir Dates spécifiques
        LocalDate dateCours1 = LocalDate.parse("2024-02-21");
        LocalDate dateCours2 = LocalDate.parse("2024-02-22");
        LocalDate dateCours3 = LocalDate.parse("2024-02-23");

        // Ajouter des cours
        Cours cours1 = new Cours("Yoga", dateCours1, "Salle A", "Session de yoga", "Senator Armstrong");
        Cours cours2 = new Cours("Pilates", dateCours2, "Salle B", "Session de pilates", "Colonel Campbell");
        Cours cours3 = new Cours("Zumba", dateCours3, "Salle C", "Cours de danse", "Revolver Ocelot");

        coursRepository.save(cours1);
        coursRepository.save(cours2);
        coursRepository.save(cours3);
    }
}
package fr.upjv.agendasportive.dataloader;

import fr.upjv.agendasportive.models.Cours;
import fr.upjv.agendasportive.repositories.CoursRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Order(1)
public class CoursDataLoader implements CommandLineRunner {

    private final CoursRepository coursRepository;


    public CoursDataLoader(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Définir format Date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        // Définir Dates spécifiques
        Date dateCours1 = sdf.parse("21/02/2024 10:00:00");
        Date dateCours2 = sdf.parse("22/02/2024 15:30:00");
        Date dateCours3 = sdf.parse("23/02/2024 18:00:00");

        // Ajouter des cours
        Cours cours1 = new Cours("Yoga", dateCours1, "Salle A", "Session de yoga", "Senator Armstrong");
        Cours cours2 = new Cours("Pilates", dateCours2, "Salle B", "Session de pilates", "Colonel Campbell");
        Cours cours3 = new Cours("Zumba", dateCours3, "Salle C", "Cours de danse", "Revolver Ocelot");

        coursRepository.save(cours1);
        coursRepository.save(cours2);
        coursRepository.save(cours3);
    }
}
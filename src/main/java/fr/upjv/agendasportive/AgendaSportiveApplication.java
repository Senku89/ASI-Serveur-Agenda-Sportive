package fr.upjv.agendasportive;

import fr.upjv.agendasportive.models.Utilisateur;
import fr.upjv.agendasportive.repositories.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan({"fr.upjv.applicationsportive", "fr.upjv.agendasportive.models"})
public class AgendaSportiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgendaSportiveApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UtilisateurRepository repository) {
        return (args) -> {
            // rajouter quelques utilisateurs de la repository
            repository.save(new Utilisateur("Boss", "solidsnake", 32));
            repository.save(new Utilisateur("Otacon", "anime", 32));
            repository.save(new Utilisateur("Raiden", "rulesofnature", 34));
        };
    }

}

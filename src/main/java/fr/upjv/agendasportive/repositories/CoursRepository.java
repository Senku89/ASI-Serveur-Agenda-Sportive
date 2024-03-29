package fr.upjv.agendasportive.repositories;

import fr.upjv.agendasportive.models.Cours;
import fr.upjv.agendasportive.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "cours", path = "listcours")
public interface CoursRepository extends JpaRepository<Cours, Integer> {
    Cours findById(int id);
    List<Cours> findByHoraireBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Cours> findByNomCours(@Param("nomCours") String nomCours);
    List<Cours> findByInstructeur(@Param("instructeur") String instructeur);
    List<Cours> findByLieu(@Param("lieu") String lieu);
}

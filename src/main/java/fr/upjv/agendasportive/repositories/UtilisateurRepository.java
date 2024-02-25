package fr.upjv.agendasportive.repositories;

import fr.upjv.agendasportive.models.Cours;
import fr.upjv.agendasportive.models.Inscription;
import fr.upjv.agendasportive.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "utilisateur", path = "utilisateurs")
public interface UtilisateurRepository extends JpaRepository <Utilisateur,Integer> {
    Utilisateur findById(int id);
    List<Utilisateur> findByNom(@Param("nom") String nom);

    //List<Cours> findByCoursBetween(LocalDateTime startDate, LocalDateTime endDate);
}
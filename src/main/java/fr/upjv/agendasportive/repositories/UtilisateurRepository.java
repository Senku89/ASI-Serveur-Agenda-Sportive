package fr.upjv.agendasportive.repositories;

import fr.upjv.agendasportive.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "utilisateur", path = "utilisateurs")
public interface UtilisateurRepository extends JpaRepository <Utilisateur,Integer> {
    List<Utilisateur> findByNom(@Param("nom") String nom);
}
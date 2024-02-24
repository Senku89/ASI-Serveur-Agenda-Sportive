package fr.upjv.agendasportive.repositories;

import fr.upjv.agendasportive.models.Cours;
import fr.upjv.agendasportive.models.Inscription;
import fr.upjv.agendasportive.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "inscription", path = "inscriptions")
public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {
    Inscription findById(int id);
    List<Inscription> findByUtilisateur(Utilisateur utilisateur);
    List<Inscription> findByCours(Cours cours);
    List<Inscription> findByUtilisateurAndCours(Utilisateur utilisateur, Cours cours);
}


// Client -> utilisateur, cours -> Serveur -> (methode inscription pour ajouter -> tester si il est pas deja inscrit)
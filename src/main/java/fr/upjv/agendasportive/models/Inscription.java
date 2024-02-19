package fr.upjv.agendasportive.models;

import jakarta.persistence.*;

@Entity
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "cours_id")
    private Cours cours;

    // Ptetre ajouter date inscription

    // Getters et Setters
}

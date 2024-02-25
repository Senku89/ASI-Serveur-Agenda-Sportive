package fr.upjv.agendasportive.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String nomCours;
    @Column(nullable = false)
    private LocalDate horaire;
    @Column(nullable = false)
    private String lieu;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String instructeur;

    public Cours() {
    }

    public Cours(String nc, LocalDate h, String li, String desc, String inst) {
        nomCours = nc;
        horaire = h;
        lieu = li;
        description = desc;
        instructeur = inst;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public LocalDate getHoraire() {
        return horaire;
    }

    public void setHoraire(LocalDate horaire) {
        this.horaire = horaire;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructeur() {
        return instructeur;
    }

    public void setInstructeur(String instructeur) {
        this.instructeur = instructeur;
    }
}
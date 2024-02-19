package fr.upjv.agendasportive.models;

import fr.upjv.agendasportive.models.Inscription;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String mdp;
    @Column(nullable = false)
    private int age;

    @OneToMany(mappedBy = "utilisateur")
    private List<Inscription> inscriptions;

    public Utilisateur(){}

    public Utilisateur(String no, String mdp, int ag) {
        this.nom=no;
        this.mdp=mdp;
        this.age=ag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }
}

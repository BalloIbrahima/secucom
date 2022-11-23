package com.securi.Secucom.Service;

import java.util.List;

import com.securi.Secucom.Models.Collaborateur;

public interface CollaborateurService {

    // Création d'un Collaborateur
    Collaborateur createCollaborateur(Collaborateur collaborateur);

    // Mettre à jour l'Collaborateur
    Collaborateur updateCollaborateur(Collaborateur collaborateur);

    // Suprimer un Collaborateur
    void deleteCollaborateur(long id);

    // Recuperer tout les Collaborateurs
    List<Collaborateur> CollaborateursList();

    // login d'un Collaborateur
    Collaborateur login(String pseudo, String password);

}

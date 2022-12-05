package com.securi.Secucom.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.securi.Secucom.Models.Collaborateur;

@Repository
public interface CollaborateurRepos extends JpaRepository<Collaborateur, Long> {

    Collaborateur findByPseudo(String pseudo);

    Collaborateur findByNomAndPrenom(String nom, String prenom);

    Collaborateur findByPseudoAndPassword(String pseudo, String password);
}

package com.securi.Secucom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securi.Secucom.Models.Collaborateur;

public interface CollaborateurRepos extends JpaRepository<Collaborateur, Long> {

    Collaborateur findByPseudo(String pseudo);

    Collaborateur findByPseudoAndPassword(String pseudo, String password);
}

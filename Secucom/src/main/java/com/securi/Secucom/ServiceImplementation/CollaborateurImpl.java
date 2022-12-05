package com.securi.Secucom.ServiceImplementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// import com.securi.Secucom.Configuration.SpringSecurityConfig;
import com.securi.Secucom.Models.Collaborateur;
import com.securi.Secucom.Repository.CollaborateurRepos;
import com.securi.Secucom.Service.CollaborateurService;

// import lombok.AllArgsConstructor;

// @Transactional
// @AllArgsConstructor
@Service
public class CollaborateurImpl implements CollaborateurService {

    @Autowired
    CollaborateurRepos collaborateurRepos;

    @Autowired
    PasswordEncoder encoder;
    // @Autowired
    // SpringSecurityConfig securityConfig;

    @Override
    public Collaborateur createCollaborateur(Collaborateur collaborateur) {
        // TODO Auto-generated method stub

        // if(rol)
        collaborateur.setPassword(encoder.encode(collaborateur.getPassword()));
        return collaborateurRepos.save(collaborateur);
    }

    @Override
    public Collaborateur updateCollaborateur(Collaborateur collaborateur) {
        // TODO Auto-generated method stub
        return collaborateurRepos.save(collaborateur);
    }

    @Override
    public void deleteCollaborateur(long id) {
        // TODO Auto-generated method stub
        collaborateurRepos.deleteById(id);

    }

    @Override
    public List<Collaborateur> CollaborateursList() {
        // TODO Auto-generated method stub
        return collaborateurRepos.findAll();
    }

    @Override
    public Collaborateur login(String pseudo, String password) {
        // TODO Auto-generated method stub
        return collaborateurRepos.findByPseudoAndPassword(pseudo, password);
    }

    @Override
    public Collaborateur findByPseudo(String pseudo) {
        // TODO Auto-generated method stub
        return collaborateurRepos.findByPseudo(pseudo);
    }

    @Override
    public Collaborateur findByNomPrenom(String nom, String prenom) {
        // TODO Auto-generated method stub
        return collaborateurRepos.findByNomAndPrenom(nom, prenom);
    }

}

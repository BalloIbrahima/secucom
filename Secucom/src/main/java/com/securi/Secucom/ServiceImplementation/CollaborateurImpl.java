package com.securi.Secucom.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.securi.Secucom.Models.Collaborateur;
import com.securi.Secucom.Repository.CollaborateurRepos;
import com.securi.Secucom.Service.CollaborateurService;

public class CollaborateurImpl implements CollaborateurService {

    @Autowired
    CollaborateurRepos collaborateurRepos;

    @Override
    public Collaborateur createCollaborateur(Collaborateur collaborateur) {
        // TODO Auto-generated method stub
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

}

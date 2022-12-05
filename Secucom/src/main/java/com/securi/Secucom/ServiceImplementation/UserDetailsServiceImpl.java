package com.securi.Secucom.ServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.securi.Secucom.Models.Collaborateur;
import com.securi.Secucom.Repository.CollaborateurRepos;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    CollaborateurRepos collaborateurRepos;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        try {
            Collaborateur collaborateur = collaborateurRepos.findByPseudo(username);

            return UserDetailsImpl.build(collaborateur);
        } catch (UsernameNotFoundException e) {
            // TODO: handle exception
            return null;
        }

    }

}

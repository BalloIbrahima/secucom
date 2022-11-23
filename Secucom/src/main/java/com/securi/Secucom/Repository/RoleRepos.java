package com.securi.Secucom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securi.Secucom.Models.Role;

public interface RoleRepos extends JpaRepository<Role, Long> {
    
    Role findByLibelle(String libelle);
    
}

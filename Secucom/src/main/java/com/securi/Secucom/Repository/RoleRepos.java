package com.securi.Secucom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.securi.Secucom.Models.MesRoles;
import com.securi.Secucom.Models.Role;

@Repository
public interface RoleRepos extends JpaRepository<Role, Long> {
    
    Role findByNom(MesRoles nom);
    
}

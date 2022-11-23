package com.securi.Secucom.Service;

import java.util.List;

import com.securi.Secucom.Models.Role;

public interface RoleService {

    // Création d'un Role
    Role createRole(Role role);

    // Mettre à jour l'Role
    Role updateRole(Role role);

    // Suprimer un Role
    void deleteRole(long id);

    // Recuperer tout les Roles
    List<Role> RolesList();

}

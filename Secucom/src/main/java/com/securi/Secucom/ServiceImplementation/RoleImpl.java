package com.securi.Secucom.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.securi.Secucom.Models.Role;
import com.securi.Secucom.Repository.RoleRepos;
import com.securi.Secucom.Service.RoleService;

public class RoleImpl implements RoleService {

    @Autowired
    RoleRepos roleRepos;

    @Override
    public Role createRole(Role role) {
        // TODO Auto-generated method stub
        return roleRepos.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        // TODO Auto-generated method stub
        return roleRepos.save(role);
    }

    @Override
    public void deleteRole(long id) {
        // TODO Auto-generated method stub
        roleRepos.deleteById(id);

    }

    @Override
    public List<Role> RolesList() {
        // TODO Auto-generated method stub
        return roleRepos.findAll();
    }

}

package com.securi.Secucom.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Collaborateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @Column(unique = true)
    private String pseudo;
    private String password;

    @ManyToMany
    @JoinTable(name = "RoleCollaborateurs", joinColumns = {
            @JoinColumn(name = "id_collaborateur") }, inverseJoinColumns = {
                    @JoinColumn(name = "id_le") })
    List<Role> roles = new ArrayList<>();

}

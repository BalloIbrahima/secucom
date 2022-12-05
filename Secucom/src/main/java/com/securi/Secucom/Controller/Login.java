package com.securi.Secucom.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.oauth2.jwt.JwtDecoder;
// import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.securi.Secucom.Configuration.JWT.JwtUtils;
import com.securi.Secucom.Models.Collaborateur;
import com.securi.Secucom.Service.CollaborateurService;
import com.securi.Secucom.ServiceImplementation.UserDetailsImpl;
import com.securi.Secucom.message.JwtResponse;
import com.securi.Secucom.message.ResponseMessage;

@RequestMapping("/login")
@RestController
@CrossOrigin
public class Login {

    @Autowired(required = false)
    AuthenticationManager authenticationManager;

    @Autowired
    CollaborateurService collaborateurService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("")
    public ResponseEntity<Object> LoginCollaborateur(@RequestBody Collaborateur colla) {

        System.err.println(colla.getPseudo());
        System.err.println(colla.getPassword());

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(colla.getPseudo(), colla.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseMessage.generateResponse("ok", HttpStatus.OK,
                    new JwtResponse(jwt,
                            userDetails.getId(),
                            userDetails.getPseudo(),
                            // userDetails.getEmail(),
                            roles));

        } catch (Exception e) {
            // TODO: handle exception
            return ResponseMessage.generateResponse("Erreur", HttpStatus.EXPECTATION_FAILED,
                    e.getMessage());
        }
    }

    @PostMapping("/newColl")
    public ResponseEntity<Object> CreateCollaborateur(@RequestBody Collaborateur collaborateur) {

        try {

            if (collaborateurService.findByPseudo(collaborateur.getPseudo()) == null) {
                // if(co)

                if (collaborateur.getRoles() == null || collaborateur.getRoles().size() == 0) {

                    return ResponseMessage.generateResponse("Erreur", HttpStatus.EXPECTATION_FAILED,
                            "Vous devez spécifié au moins un role pour le collaboratur .");

                } else {

                    // if (role != "user" && role != "admin") {
                    // return ResponseMessage.generateResponse("Erreur",
                    // HttpStatus.EXPECTATION_FAILED,
                    // "Cet role n'existe pas !");
                    // } else {
                    return ResponseMessage.generateResponse("ok", HttpStatus.OK,
                            collaborateurService.createCollaborateur(collaborateur));
                    // }

                }

            } else {
                return ResponseMessage.generateResponse("Erreur", HttpStatus.EXPECTATION_FAILED,
                        "Un collaborateur avec le même pseudo existe déja .");

            }
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseMessage.generateResponse("Erreur", HttpStatus.EXPECTATION_FAILED, null);

        }

    }

}

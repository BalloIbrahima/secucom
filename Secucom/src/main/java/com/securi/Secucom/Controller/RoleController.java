package com.securi.Secucom.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.securi.Secucom.Service.RoleService;
import com.securi.Secucom.message.ResponseMessage;

@RequestMapping("/role")
@Controller
@CrossOrigin
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/liste")
    public ResponseEntity<Object> ListeRole() {
        return ResponseMessage.generateResponse("ok", HttpStatus.OK, roleService.RolesList());

    }
}

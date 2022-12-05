package com.securi.Secucom.message;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponse {

  private String token;
  private String type = "Bearer";
  private Long id;
  private String pseudo;
  private List<String> roles;

  public JwtResponse(String accessToken, Long id, String pseudo, List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.pseudo = pseudo;
    this.roles = roles;
  }

}

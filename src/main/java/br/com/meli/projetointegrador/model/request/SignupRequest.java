package br.com.meli.projetointegrador.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.*;

@Getter
@Setter
public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String name;

  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  private String cpf;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;


  private Integer warehouse_id;


}


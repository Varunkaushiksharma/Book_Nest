package com.example.demo.DTO;

public class SignUpDTO {
    

  private String name;

  private String email;

  private String password;

  public SignUpDTO(){
    
  }

  public SignUpDTO( String name, String email, String password) {
   
    this.name = name;
    this.email = email;
    this.password = password;
}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}

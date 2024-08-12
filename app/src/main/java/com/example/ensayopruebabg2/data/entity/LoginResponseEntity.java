package com.example.ensayopruebabg2.data.entity;

import com.google.gson.annotations.Expose;

public class LoginResponseEntity {

  @Expose
  private String token;

  private String access_token;
  private String refresh_token;

  public LoginResponseEntity() {}

  public LoginResponseEntity(String token) {
    this.token = token;
  }

  public LoginResponseEntity(String access_token, String refresh_token) {
    this.access_token = access_token;
    this.refresh_token = refresh_token;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getAccess_token() {
    return access_token;
  }

  public void setAccess_token(String access_token) {
    this.access_token = access_token;
  }

  public String getRefresh_token() {
    return refresh_token;
  }

  public void setRefresh_token(String refresh_token) {
    this.refresh_token = refresh_token;
  }
}

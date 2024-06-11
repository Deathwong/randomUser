package com.jefrido.randomuser.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String gender;
    private Name name;
    private Location location;
    private String email;
    private Dob dob;
    private Picture picture;
    private String phone;
    private Login login;
}

package com.board.inoboard.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @Column(unique = true)
    @Pattern(regexp = "^[a-z0-9]{4,10}$",message = "4~10자리 소문자와숫자만 넣으세요")
    private String username;
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
    private String password;
    @Email
    private String email;


}

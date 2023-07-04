package com.board.inoboard.entity;

import com.board.inoboard.dto.BoardRequestDto;
import java.util.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Component;

//자카르타<-자바x
@Table(name = "user")
@NoArgsConstructor
@Entity
@Getter
@Component
public class User {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @Pattern(regexp = "^[a-z0-9]{4,10}$",message = "4~10자리 소문자와숫자만 넣으세요")
    private String username; // 사용자이름.
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
    private String password; //패스워드
    @Email
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;

    }

}

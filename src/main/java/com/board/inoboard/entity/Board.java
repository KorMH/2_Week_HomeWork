package com.board.inoboard.entity;

import com.board.inoboard.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Table(name = "board")
@NoArgsConstructor
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String username;
    private String contents;
    private String title;


    public Board(BoardRequestDto boardrequestDto){
        this.title = boardrequestDto.getTitle();
        this.contents = boardrequestDto.getContents();
        this.username=boardrequestDto.getUsername();
    }

    public void update(BoardRequestDto boardRequestDto){
        this.contents = boardRequestDto.getContents();
        this.title = boardRequestDto.getTitle();
    }

}


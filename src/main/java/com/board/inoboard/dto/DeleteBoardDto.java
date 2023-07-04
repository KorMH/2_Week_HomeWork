package com.board.inoboard.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteBoardDto {
    private String msg;

    public void setMsg(String msg){
        this.msg = msg;
    }
}

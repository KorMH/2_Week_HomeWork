package com.board.inoboard.controller;

import com.board.inoboard.dto.*;
import com.board.inoboard.service.BoardService;
import com.board.inoboard.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;

    public BoardController(BoardService boardService,UserService userService) {
        this.userService=userService;
        this.boardService = boardService;
    }


    @PostMapping("/board")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto){
        return boardService.createBoard(requestDto);
    }

    @GetMapping("/board")
    public List<BoardResponseDto> getBoard(){
        return boardService.getBoard();
    }



    @PutMapping("/board/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.updateBoard(id,requestDto);
    }

    @DeleteMapping("/board/{id}")
    public String deleteBoard(@PathVariable Long id){
        return boardService.deleteBoard(id);
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }


    @PostMapping("/user/signup")
    public String signup(@RequestBody @Valid SignupRequestDto requestDto){
//        if (bindingResult.hasErrors()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
//        }
        userService.signup(requestDto);
//        if(requestDto.getUsername().matches("[a-z0-9]{4,10}")) {
//            return "가입완료!";
//        }
//        else{
//            return "4~10자리의 소문자와 숫자를 넣어주세요";
//        }

        return "가입완료";
    }

    @PostMapping("/user/login")
    public String login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res){
        userService.login(requestDto,res);
        return "로그인 완료";
    }
}

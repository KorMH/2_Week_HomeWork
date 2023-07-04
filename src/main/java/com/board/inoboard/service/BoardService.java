package com.board.inoboard.service;

import com.board.inoboard.dto.BoardRequestDto;
import com.board.inoboard.dto.BoardResponseDto;
import com.board.inoboard.dto.DeleteBoardDto;
import com.board.inoboard.entity.Board;
import com.board.inoboard.entity.User;
import com.board.inoboard.jwt.JwtUtil;
import com.board.inoboard.repository.BoardRepository;
import com.board.inoboard.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    // 쿠키에서 jwt값을
    private final BoardRepository boardRepository;
    private final JwtUtil jwtUtil;
    private final User user;
    private final UserRepository userRepository;


    public BoardService(BoardRepository boardRepository, JwtUtil jwtUtil,User user,UserRepository userRepository){
        this.boardRepository = boardRepository;
        this.jwtUtil=jwtUtil;
        this.user=user;
        this.userRepository=userRepository;

    }



    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto requestDto){
        // ReuqestDto -> Entity
        Board board = new Board(requestDto);
        board.setUsername(jwtUtil.getUsername());
        // DB 저장
        Board saveBoard = boardRepository.save(board);

        BoardResponseDto boardResponseDto = new BoardResponseDto(board);

        return boardResponseDto;
    }

    public List<BoardResponseDto> getBoard() {
        //DB조회
        return boardRepository.findAllByOrderByModifiedAtDesc().stream().map(BoardResponseDto::new).toList();
    }

    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto) {

        String username = jwtUtil.getUsername();

        // 해당 메모가 DB에 존재하는지 확인
        Board board = findboard(id);

        BoardResponseDto ResponseDto = new BoardResponseDto(board);
        // board 내용 수정
        if(username.equals(ResponseDto.getUsername())){
            board.update(requestDto);
            return ResponseDto;
        } else{
            return ResponseDto;
        }
    }


    public String deleteBoard(Long id) {

        String username = jwtUtil.getUsername();

        Board board = findboard(id);
        DeleteBoardDto deleteBoardDto = new DeleteBoardDto();
        String answer="1";
//         board삭제
//        jwt 검증해서 올거니까 board.getUsername==requestDto.getusername
        if(username.equals(board.getUsername())) {
            boardRepository.delete(board);
            answer="삭제완료";
        }else{
            answer="삭제실패";
        }

        return answer;
    }

    private Board findboard(Long id){
        return  boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 글은 존재하지 않습니다.")
        );
    }


}

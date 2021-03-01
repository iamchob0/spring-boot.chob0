package com.iam.chobo.domain.board;

import javax.validation.Valid;

import com.iam.chobo.domain.board.dto.BoardDto;
import com.iam.chobo.domain.board.exception.NotAllowException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/board", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class BoardController {

    @Autowired
    private BoardService boardService; 

    @GetMapping("/ok")
    public String ok() {
        log.info("ok");
        return "ok";
    }

    @PostMapping("/")
    public String createBoard(@RequestBody @Valid final BoardDto board) {
        String nullStr = null;
    
        // nullStr.equals("");
        throw new NotAllowException();
        // boardService.createBoard(board);
        // return "ok";
    }

    @PutMapping("/")
    public String updateBoard(@RequestBody @Valid final BoardDto board) {
        boardService.updateBoard(board);
        return "ok";
    }

    @DeleteMapping("/")
    public String deleteBoard(@RequestBody @Valid final BoardDto board) {
        String nullStr = null;
    
        nullStr.equals("");
        return "null";
    }

}

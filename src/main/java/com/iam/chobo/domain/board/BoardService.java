package com.iam.chobo.domain.board;

import com.iam.chobo.domain.board.dto.BoardDto;
import com.iam.chobo.domain.board.exception.NotAllowException;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardService {

    public int createBoard(BoardDto board) {
        return 1;
    }

    public int updateBoard(BoardDto board) {
        log.info("run===");
        throw new NotAllowException();
    }

    public int deleteBoard(BoardDto board) {
        return 1;
    }
}

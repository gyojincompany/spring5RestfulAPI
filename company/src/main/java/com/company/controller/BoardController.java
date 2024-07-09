package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.domain.Board;
import com.company.service.BoardService;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Board>> getBoardList() {
        List<Board> boards = boardService.getBoardList();
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<Board> getBoard(@RequestParam("no") int no) {
        Board board = boardService.getBoard(no);
        if (board != null) {
            return new ResponseEntity<>(board, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertBoard(@RequestBody Board board) {
        boardService.insert(board);
        return new ResponseEntity<>("Board inserted successfully", HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateBoard(@RequestBody Board board) {
        boardService.update(board);
        return new ResponseEntity<>("Board updated successfully", HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteBoard(@RequestParam("no") int no) {
        boardService.delete(no);
        return new ResponseEntity<>("Board deleted successfully", HttpStatus.OK);
    }
}
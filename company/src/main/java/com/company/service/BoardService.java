package com.company.service;

import java.util.List;

import com.company.domain.Board;

public interface BoardService {
    List<Board> getBoardList();
    Board getBoard(int no);
    void insert(Board board);
    void update(Board board);
    void delete(int no);
}
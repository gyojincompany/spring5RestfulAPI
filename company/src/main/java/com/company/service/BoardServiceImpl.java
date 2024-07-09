package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.Board;
import com.company.persistence.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public List<Board> getBoardList() {
        return boardMapper.getBoardList();
    }

    @Override
    public Board getBoard(int no) {
        return boardMapper.getBoard(no);
    }

    @Override
    public void insert(Board board) {
        boardMapper.insert(board);
    }

    @Override
    public void update(Board board) {
        boardMapper.update(board);
    }

    @Override
    public void delete(int no) {
        boardMapper.delete(no);
    }
}
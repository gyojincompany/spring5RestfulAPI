package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.Qna;
import com.company.persistence.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService {

    private final QnaMapper qnaMapper;

    @Autowired
    public QnaServiceImpl(QnaMapper qnaMapper) {
        this.qnaMapper = qnaMapper;
    }

    @Override
    public List<Qna> getQnaList() {
        return qnaMapper.getQnaList();
    }

    @Override
    public Qna getQna(int qno) {
        return qnaMapper.getQna(qno);
    }

    @Override
    public void insertQuestion(Qna qna) {
        qnaMapper.insertQuestion(qna);
    }

    @Override
    public void insertAnswer(Qna qna) {
        qnaMapper.insertAnswer(qna);
    }

    @Override
    public void update(Qna qna) {
        qnaMapper.update(qna);
    }

    @Override
    public void deleteQuestion(int qno) {
        qnaMapper.deleteQuestion(qno);
    }

    @Override
    public void deleteAnswer(int qno) {
        qnaMapper.deleteAnswer(qno);
    }
}
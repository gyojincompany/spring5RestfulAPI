package com.company.service;

import java.util.List;

import com.company.domain.Qna;

public interface QnaService {
    List<Qna> getQnaList();
    Qna getQna(int qno);
    void insertQuestion(Qna qna);
    void insertAnswer(Qna qna);
    void update(Qna qna);
    void deleteQuestion(int qno);
    void deleteAnswer(int qno);
}
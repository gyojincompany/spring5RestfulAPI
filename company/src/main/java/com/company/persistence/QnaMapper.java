package com.company.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.company.domain.Qna;

@Mapper
public interface QnaMapper {

    @Select("SELECT * FROM qna")
    public List<Qna> getQnaList();

    @Select("SELECT * FROM qna WHERE qno = #{qno}")
    public Qna getQna(int qno);

    @Insert("INSERT INTO qna (lev, parno, title, content, author, resdate, hits) " +
            "VALUES (#{lev}, #{parno}, #{title}, #{content}, #{author}, #{resdate}, #{hits})")
    @Options(useGeneratedKeys = true, keyProperty = "qno")
    public void insertQuestion(Qna qna);

    @Insert("INSERT INTO qna (lev, parno, title, content, author, resdate, hits) " +
            "VALUES (#{lev}, #{parno}, #{title}, #{content}, #{author}, #{resdate}, #{hits})")
    @Options(useGeneratedKeys = true, keyProperty = "qno")
    public void insertAnswer(Qna qna);

    @Update("UPDATE qna SET title = #{title}, content = #{content}, author = #{author}, " +
            "resdate = #{resdate}, hits = #{hits} WHERE qno = #{qno}")
    public void update(Qna qna);

    @Delete("DELETE FROM qna WHERE qno = #{qno} OR parno = #{qno}")
    public void deleteQuestion(int qno);

    @Delete("DELETE FROM qna WHERE qno = #{qno}")
    public void deleteAnswer(int qno);
}
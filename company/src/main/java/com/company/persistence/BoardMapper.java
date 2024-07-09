package com.company.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.company.domain.Board;

@Mapper
public interface BoardMapper {

    @Select("SELECT * FROM board")
    public List<Board> getBoardList();

    @Select("SELECT * FROM board WHERE no = #{no}")
    public Board getBoard(int no);

    @Insert("INSERT INTO board (title, content, author, resdate, hits) " +
            "VALUES (#{title}, #{content}, #{author}, #{resdate}, #{hits})")
    @Options(useGeneratedKeys = true, keyProperty = "no")
    public void insert(Board board);

    @Update("UPDATE board SET title = #{title}, content = #{content}, author = #{author}, " +
            "resdate = #{resdate}, hits = #{hits} WHERE no = #{no}")
    public void update(Board board);

    @Delete("DELETE FROM board WHERE no = #{no}")
    public void delete(int no);
}
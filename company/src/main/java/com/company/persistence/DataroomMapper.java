package com.company.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.company.domain.Dataroom;

@Mapper
public interface DataroomMapper {

    @Select("SELECT * FROM dataroom")
    public List<Dataroom> getDataroomList();

    @Select("SELECT * FROM dataroom WHERE dno = #{dno}")
    public Dataroom getDataroom(int dno);

    @Insert("INSERT INTO dataroom (title, content, author, datafile, resdate, hits) " +
            "VALUES (#{title}, #{content}, #{author}, #{datafile}, #{resdate}, #{hits})")
    @Options(useGeneratedKeys = true, keyProperty = "dno")
    public void insert(Dataroom dataroom);

    @Update("UPDATE dataroom SET title = #{title}, content = #{content}, author = #{author}, " +
            "datafile = #{datafile}, resdate = #{resdate}, hits = #{hits} WHERE dno = #{dno}")
    public void update(Dataroom dataroom);

    @Delete("DELETE FROM dataroom WHERE dno = #{dno}")
    public void delete(int dno);
}
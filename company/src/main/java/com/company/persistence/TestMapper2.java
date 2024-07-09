package com.company.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.company.domain.Test;

@Mapper
public interface TestMapper2 {
	
    @Select("SELECT * FROM test")
    public List<Test> getTestList3();

    @Select("SELECT * FROM test WHERE num = #{num}")
    public Test getTest3(int id);

    @Insert("INSERT into test (title) VALUES (#{title})")
    @Options(useGeneratedKeys=true, keyProperty="num")
    public void insert3(Test test);

    @Update("UPDATE test SET title = #{title} WHERE num = #{num}")
    public void update3(Test test);

    @Delete("DELETE FROM test WHERE num = #{num}")
    public void delete3(int num);
}

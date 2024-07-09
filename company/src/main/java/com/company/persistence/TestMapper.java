package com.company.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import com.company.domain.Test;
//Mapper 인터페이스 + MyBatis Mapper
//test2Mapper.xml의 안의 namespace 속성에 com.spring1.persistence.TestMapper
//test2Mapper.xml의 안의 명령 tag 의 id와 com.spring1.persistence.TestMapper의 
//메소드명이 일치
//ApplicationConfig에 Bean 등록 안함.
@Mapper
public interface TestMapper {
    public List<Test> getTestList2();
    public Test getTest2(int num);
    public void insert2(Test test);
    public void update2(Test test);
    public void delete2(int num);
    Test test() throws Exception;
}

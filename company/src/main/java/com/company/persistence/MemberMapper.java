package com.company.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.company.domain.Member;

@Mapper
public interface MemberMapper {

    @Select("SELECT * FROM member")
    public List<Member> getMemberList();

    @Select("SELECT * FROM member WHERE id = #{id}")
    public Member getMember(String id);

    @Insert("INSERT INTO member (id, pw, name, birth, email, addr1, addr2, postcode, regdate) " +
            "VALUES (#{id}, #{pw}, #{name}, #{birth}, #{email}, #{addr1}, #{addr2}, #{postcode}, #{regdate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void join(Member member);

    @Select("SELECT COUNT(*) FROM member WHERE id = #{id}")
    public int confirm(String id);

    @Select("SELECT * FROM member WHERE id = #{id}")
    public Member mypage(String id);

    @Update("UPDATE member SET pw = #{pw}, name = #{name}, birth = #{birth}, email = #{email}, " +
            "addr1 = #{addr1}, addr2 = #{addr2}, postcode = #{postcode} WHERE id = #{id}")
    public void update(Member member);

    @Delete("DELETE FROM member WHERE id = #{id}")
    public void delete(String id);

    @Select("SELECT * FROM member WHERE id = #{id} AND pw = #{pw}")
    public Member login(String id, String pw);

    @Select("SELECT * FROM member WHERE id = #{id}")
    public void logout(String id);
}
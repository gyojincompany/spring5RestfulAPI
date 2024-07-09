package com.company.service;

import java.util.List;

import com.company.domain.Member;

public interface MemberService {
    List<Member> getMemberList();
    Member getMember(String id);
    void join(Member member);
    boolean confirm(String id);
    Member mypage(String id);
    void update(Member member);
    void delete(String id);
    Member login(String id, String pw);
    void logout(String id);
}
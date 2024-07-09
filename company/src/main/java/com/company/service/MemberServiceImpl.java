package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.Member;
import com.company.persistence.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public List<Member> getMemberList() {
        return memberMapper.getMemberList();
    }

    @Override
    public Member getMember(String id) {
        return memberMapper.getMember(id);
    }

    @Override
    public void join(Member member) {
        memberMapper.join(member);
    }

    @Override
    public boolean confirm(String id) {
        return memberMapper.confirm(id) > 0;
    }

    @Override
    public Member mypage(String id) {
        return memberMapper.mypage(id);
    }

    @Override
    public void update(Member member) {
        memberMapper.update(member);
    }

    @Override
    public void delete(String id) {
        memberMapper.delete(id);
    }

    @Override
    public Member login(String id, String pw) {
        return memberMapper.login(id, pw);
    }

    @Override
    public void logout(String id) {
        memberMapper.logout(id);
    }
}
package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.domain.Member;
import com.company.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Member>> getMemberList() {
        List<Member> members = memberService.getMemberList();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<Member> getMember(@RequestParam("id") String id) {
        Member member = memberService.getMember(id);
        if (member != null) {
            return new ResponseEntity<>(member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody Member member) {
        if (memberService.confirm(member.getId())) {
            return new ResponseEntity<>("ID already exists", HttpStatus.CONFLICT);
        }
        memberService.join(member);
        return new ResponseEntity<>("Member joined successfully", HttpStatus.CREATED);
    }

    @GetMapping("/confirm")
    public ResponseEntity<Boolean> confirm(@RequestParam("id") String id) {
        boolean exists = memberService.confirm(id);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    @GetMapping("/mypage")
    public ResponseEntity<Member> mypage(@RequestParam("id") String id) {
        Member member = memberService.mypage(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Member member) {
        memberService.update(member);
        return new ResponseEntity<>("Member updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("id") String id) {
        memberService.delete(id);
        return new ResponseEntity<>("Member deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Member> login(@RequestParam("id") String id, @RequestParam("pw") String pw) {
        Member member = memberService.login(id, pw);
        if (member != null) {
            return new ResponseEntity<>(member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam("id") String id) {
        memberService.logout(id);
        return new ResponseEntity<>("Member logged out successfully", HttpStatus.OK);
    }
}
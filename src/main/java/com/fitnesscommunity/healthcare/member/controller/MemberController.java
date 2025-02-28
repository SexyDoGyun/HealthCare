package com.fitnesscommunity.healthcare.member.controller;

import com.fitnesscommunity.healthcare.member.domain.Member;
import com.fitnesscommunity.healthcare.member.dto.MemberLoginRequestDto;
import com.fitnesscommunity.healthcare.member.dto.MemberSignupRequestDto;
import com.fitnesscommunity.healthcare.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberSignupRequestDto requestDto) {
        memberService.signup(requestDto);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberLoginRequestDto requestDto, HttpSession session) {
        Member member = memberService.login(requestDto);
        session.setAttribute("memberId", member.getId());
        return ResponseEntity.ok("로그인 성공!");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // 세션 삭제
        return ResponseEntity.ok("로그아웃 완료!");
    }

    @GetMapping("/me")
    public ResponseEntity<Member> getMyInfo(HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        if (memberId == null) {
            return ResponseEntity.status(401).build();
        }

        Member member = memberService.findById(memberId);
        return ResponseEntity.ok(member);
    }
}

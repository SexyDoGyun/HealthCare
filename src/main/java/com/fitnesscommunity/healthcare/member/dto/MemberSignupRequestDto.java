package com.fitnesscommunity.healthcare.member.dto;

import com.fitnesscommunity.healthcare.member.domain.Member;
import com.fitnesscommunity.healthcare.member.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberSignupRequestDto {
    private String email;
    private String password;
    private String nickname;

    public Member toEntity(String encodedPassword) {
        return Member.builder()
                .email(email)
                .password(encodedPassword)
                .nickname(nickname)
                .role(Role.USER)
                .build();
    }
}

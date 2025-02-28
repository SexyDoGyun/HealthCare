package com.fitnesscommunity.healthcare.workoutroutine.domain;

import com.fitnesscommunity.healthcare.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class WorkoutRoutine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false, length = 100)
    private String routineName;

    @Column(columnDefinition = "JSON", nullable = false)
    private String exercises; // JSON 형식으로 운동 목록 저장
}

package com.fourbooks.hellowebflux.service;

import com.fourbooks.hellowebflux.domain.Member;
import com.fourbooks.hellowebflux.repository.MemberRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Mono<Member> findById(Integer id) {
        return memberRepository.findById(id);
    }
}

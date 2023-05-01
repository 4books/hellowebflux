package com.fourbooks.hellowebflux.repository;

import com.fourbooks.hellowebflux.domain.Member;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MemberRepository extends ReactiveCrudRepository<Member, Integer> {
    Mono<Member> findById(Integer id);
    Mono<Member> save(Member member);
}
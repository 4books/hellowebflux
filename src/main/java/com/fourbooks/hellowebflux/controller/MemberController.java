package com.fourbooks.hellowebflux.controller;

import com.fourbooks.hellowebflux.domain.Member;
import com.fourbooks.hellowebflux.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;

@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/member/{id}")
    public Mono<String> getMember(@PathVariable Integer id, Model model) {
        Mono<Member> memberMono = memberService.findById(id);

        model.addAttribute("member", memberMono);
        return Mono.just("member");
    }

    @PostMapping("/member")
    public Mono<Void> addMember(@RequestParam Integer id, @RequestParam String name, @RequestParam Integer age, ServerHttpResponse response) {
        Member newMember = new Member(id, name, age);
        return memberService.addMember(newMember)
                .flatMap(savedMember -> {
                    response.setStatusCode(HttpStatus.SEE_OTHER);
                    response.getHeaders().setLocation(URI.create("/member/" + savedMember.getId()));
                    return response.setComplete();
                });
    }
}
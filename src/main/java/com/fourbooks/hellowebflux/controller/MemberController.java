package com.fourbooks.hellowebflux.controller;

import com.fourbooks.hellowebflux.domain.Member;
import com.fourbooks.hellowebflux.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

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
}
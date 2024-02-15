package com.example.spring_practice.controller;

import com.example.spring_practice.entity.Member;
import com.example.spring_practice.dto.MemberForm;
import com.example.spring_practice.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class MemberController {

    // 스프링부트가 미리 생성해놓은 객체를 가져다가 자동 연결!
    @Autowired
    private MemberRepository memberRepository;

    // 폼 페이지 보여주기
    @GetMapping("/members/new")
    public String newMemberForm() {
        return "members/new";
    }

    // 폼 데이터 받기
    @GetMapping("/signup")
    public String signUpPage() {
        return "members/new";
    }

    // 폼 데이터 받기
    @PostMapping("/join")
    public String join(MemberForm memberForm) {
        log.info(memberForm.toString());

        // 1. DTO -> (Controller) -> Entity
        Member member = memberForm.toEntity();
        log.info(member.toString());

        // 2. Entity -> (repository) -> DB
        Member saved = memberRepository.save(member);
        log.info(saved.toString());

        // 3. return view page
        return "redirect:/members/" + saved.getId();
    }

    // Read
    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model) {
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", member);
        return "members/show";
    }

    // 인덱스
    @GetMapping("/members")
    public String index(Model model) {
        Iterable<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm form) {
        log.info(form.toString());
        Member memberEntity = form.toEntity();
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        if (target != null) {
            memberRepository.save(memberEntity);
        }
        return "redirect:/members/" + memberEntity.getId();
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제 요청이 들어왔습니다!!");

        // 1. 삭제 대상을 가져옴
        Member target = memberRepository.findById(id).orElse(null);
        log.info(target.toString());

        // 2. 대상 삭제
        if (target != null) {
            memberRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제됐습니다.");
        }
        // 3. 결과 페이지로 리다이렉트
        return "redirect:/members";
    }
}

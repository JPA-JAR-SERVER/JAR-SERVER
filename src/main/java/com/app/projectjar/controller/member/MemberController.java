package com.app.projectjar.controller.member;

import com.app.projectjar.domain.member.MailDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.member.MemberRandomKey;
import com.app.projectjar.service.member.MemberService;
import com.app.projectjar.service.memberRandomKey.MemberRandomKeyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Random;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final MemberRandomKeyService memberRandomKeyService;

    @GetMapping("join")
    public String goToJoinForm(MemberDTO memberDTO){
        return "member/join";
    }

    @PostMapping("join")
    public RedirectView join(MemberDTO memberDTO){
        memberService.join(memberDTO, passwordEncoder);
        return new RedirectView("/member/login");
    }

    @GetMapping("login")
    public void goToLoginForm() {;}

    //   이메일 중복 검사
    @PostMapping("check-email")
    @ResponseBody
    public Long checkEmail(@RequestParam("memberEmail") String memberEmail){
        return memberService.checkEmail(memberEmail);
    }

    //  닉네임 중복 검사
    @PostMapping("check-nickname")
    @ResponseBody
    public Long checkNickname(@RequestParam("memberNickname") String memberNickname){
        return memberService.checkNickName(memberNickname);
    }

    //  휴대폰 번호 중복 검사
    @PostMapping("check-phone")
    @ResponseBody
    public Long checkPhone(@RequestParam("memberPhoneNumber") String memberPhoneNumber){
        return memberService.checkPhoneNumber(memberPhoneNumber);
    }

    @GetMapping("account-confirm")
    public void account(){}

    @GetMapping("logout")
    public void goToLogOut(){;}

    //    인증번호 보내기
    @PostMapping("sendCode")
    @ResponseBody
    public String sendCode(String memberPhone) {
        Random random = new Random();

        String code = "";
        for (int i = 0; i < 6; i++) {
            String number = Integer.toString(random.nextInt(10));
            code += number;
        }

        memberService.checkSMS(memberPhone, code);
        return code;
    }

    @GetMapping("password")
    public void password(){}

    @GetMapping("change-password/{memberEmail}/{randomKey}")
    public String password(@PathVariable("memberEmail") String memberEmail, @PathVariable("randomKey") String randomKey, Model model){

        String latest = memberRandomKeyService.getLatestRandomKey(memberService.getMemberEmail(memberEmail).getId()).getMemberRandomKey();
        if(latest.equals(randomKey)){
            model.addAttribute("memberEmail", memberEmail);
            model.addAttribute("result", true);
            model.addAttribute("errorMessage", "경로 인증에 성공 했습니다.");
        }
        else {
            model.addAttribute("result", false);
            model.addAttribute("errorMessage", "만료된 경로 입니다.");
        }

        return "member/change-password";
    }


    @PostMapping("change-password")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Long changePassword(@RequestParam("memberPassword") String memberPassword, @RequestParam("memberEmail") String memberEmail){
        Member member = memberService.getMemberEmail(memberEmail);
        memberService.updatePassword(member.getId(), memberPassword, passwordEncoder);
        memberRandomKeyService.saveRandomKey(member);

        return 1L;
    }

    /* 이메일 보내기 */
    @PostMapping("sendEmail")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Long sendEmailToFindPassword(@RequestParam("memberEmail") String memberEmail){

        Member member = memberService.getMemberEmail(memberEmail);

        MemberRandomKey memberRandomKey = memberRandomKeyService.getLatestRandomKey(member.getId());

        String randomKeyString = memberRandomKey != null ? memberRandomKey.getMemberRandomKey() : memberRandomKeyService.saveRandomKey(member).getMemberRandomKey();


        MailDTO mailDTO = new MailDTO();
        mailDTO.setAddress(memberEmail);
        mailDTO.setTitle("[Jar]새 비밀 번호 설정 링크 입니다.");

        String message = "비밀 번호 변경 링크 입니다.\n\n" + "링크: http://localhost:10000/member/change-password/" + memberEmail + "/" + randomKeyString;
        mailDTO.setMessage(message);

        memberService.sendMail(mailDTO);

        return 1L;
    }


}

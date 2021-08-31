package com.example.demo.controller.member;

import com.example.demo.config.SessionMember;
import com.example.demo.domain.PageDTO;
import com.example.demo.domain.member.dto.UpdateInfoDTO;
import com.example.demo.domain.Criteria;
import com.example.demo.domain.member.dto.vo.Role;
import com.example.demo.domain.member.dto.MemberJoinDTO;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.Password;
import com.example.demo.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
public class loginController {

    @Autowired
    MemberService memberService;

    @RequestMapping("/")
    public String index(Model model,HttpSession httpSession){
        return login( model, httpSession);
    }


    @GetMapping("/login")
    public String login(Model model, HttpSession httpSession){
        //session 에 소셜 로그인 한 사람은 session 이름이 socialMember , oauth2userService 에서 그렇게 정의했으니까
        //session 에 회원으로 로그인 한 사람은 session 이름이 loginMember
        SessionMember socialMember = (SessionMember) httpSession.getAttribute("socialMember");
        SessionMember loginMember =(SessionMember) httpSession.getAttribute("loginMember");
        //어차피 model 에 보내줘야 할 내용은 공통된 테이블에 저장된 회원정보니 그냥 member 로 이름 통일
        String loginChk = (String) model.getAttribute("loginChk");

        if( socialMember != null){
            model.addAttribute("member", socialMember);
            model.addAttribute("loginChk","1");

            return "redirect:/main";

        }else if(loginMember != null){
            model.addAttribute("member", loginMember);
            model.addAttribute("loginChk","1");

        }else if(loginChk!=null && loginChk.equals("2")){
            model.addAttribute("loginChk","2");
        }else{
            model.addAttribute("loginChk","0");
        }
        return "login";
    }

    @PostMapping("/login")
    public String memberLogin(Model model, HttpSession httpSession,String memberId, String memberPw){
        SessionMember result = memberService.login(memberId,memberPw);
        SessionMember sessionMember = result;

        if(sessionMember == null ){
            model.addAttribute("loginChk","2");
            return index(model,httpSession);
        }
            //추후 회원 정보 수정을 위한 session 에 넣을 데이터
            httpSession.setAttribute("loginMember", sessionMember);

        if(sessionMember.getRole()== Role.ADMIN){
            httpSession.setAttribute("admin", sessionMember);
        }

        //return index(model,httpSession);
        return "redirect:/main";

    }


    @GetMapping("/join")
    public void joinPage(){
    }

    @PostMapping("/join")
    public String join(MemberJoinDTO memberJoinDTO,String memberPw){
        memberJoinDTO.setRole(Role.USER);
        Member member = memberJoinDTO.toEntity();
        Password password =  Password.builder()
                .member(member)
                .pw(memberPw)
                .build();
        int result = memberService.join(member,password);
        if(result == 0){
            return "redirect:login";
        }
        return "join";
    }

    //    @PostMapping(value = "/idCheck",consumes = "application/json")
    @PostMapping("/idCheck")
    @ResponseBody
    public String idCheck(@RequestBody String memberId){
        int length = memberId.length();
        System.out.println("idCheck 호출됨, length 는 " + length);
        System.out.println("memberId는 " + memberId);
        String result = memberService.idCheck(memberId);
        return result;
    }

    @RequestMapping("/memberLogout")
    public String memberLogout(Model model,HttpSession httpSession ){
        httpSession.invalidate();
        return "redirect:login";
    }

    //어짜피 회원 수정은 일반 로그인 회원 전용 기능
    @GetMapping("/myPage")
    public String myPage(Model model, HttpSession httpSession){
        SessionMember loginMember = (SessionMember) httpSession.getAttribute("loginMember");

        if(loginMember != null) {
            Member member = memberService.get(loginMember.getId());

            model.addAttribute("member", member);

        }else if(loginMember == null){
            String message = "로그인 후 이용해 주세요";
            model.addAttribute("needLog", message);

            return "redirect:login";
        }
        return "myPage";
    }

    @GetMapping("/myPageModi")
    public String myPageModi(Model model, HttpSession httpSession){
        SessionMember loginMember = (SessionMember) httpSession.getAttribute("loginMember");

        if(loginMember != null) {
            Member member = memberService.get(loginMember.getId());

            model.addAttribute("member", member);
        }

        return "myPageModi";
    }

    @PostMapping("/memberUpdate")
    public String updateInfo(Model model, HttpSession httpSession, UpdateInfoDTO updateInfoDTO){
        memberService.updateInfo(updateInfoDTO);
        return "redirect:/";
    }

    @GetMapping("/memberOut")
    public String memberOut(HttpSession httpSession){
        SessionMember sessionMember = (SessionMember) httpSession.getAttribute("loginMember");
        memberService.memberOut(sessionMember.getId());
        httpSession.invalidate();
        return "redirect:/";
    }

/*    @PostMapping("/memberCheck")
    @ResponseBody
    public Map<String,Object> memberCheck(@RequestBody Map<String,Object> memberCheck){
        Map<String,Object> member
        return null;
    }*/

    @GetMapping("/adminPage")
    public String adminPage(HttpSession httpSession , Model model, Criteria cri ){

        SessionMember sessionMember = (SessionMember)httpSession.getAttribute("admin");

        if(sessionMember!=null) {
            model.addAttribute("list",memberService.getList(cri));
            System.out.println(memberService.totalCount());
            PageDTO page = new PageDTO(cri, memberService.totalCount());
            System.out.println(page.getStartPage());
            System.out.println(page.getEndPage());
            model.addAttribute("pageMaker", page);
            return "/member/adminPage";
        }
        return "redirect:/";
    }

    public SessionMember adminSessionCheck(HttpSession httpSession){

        SessionMember sessionMember = (SessionMember) httpSession.getAttribute("loginMember");

        return sessionMember;
    }

    @DeleteMapping("/delete/{memberNo}")
    public String deleteMember(HttpSession httpSession, @PathVariable("memberNo")Long memberNo){
        SessionMember sessionMember = adminSessionCheck(httpSession);
        if(sessionMember.getRole().equals("ROLE_ADMIN")) {
            httpSession.setAttribute("admin",sessionMember);
            memberService.deleteMember(memberNo);
            return "redirect:/adminPage";
        }
        return "redirect:/";
    } //post - requestParam

}


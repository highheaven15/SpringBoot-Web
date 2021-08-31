package com.example.demo.controller.account;

import com.example.demo.config.SessionMember;
import com.example.demo.domain.account.AccountDTO;
import com.example.demo.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Controller
@RequestMapping("/account/")
/*@AllArgsConstructor*/
public class AccountController {

    @Autowired
    private AccountService service;


    @GetMapping("register")
    public String register(HttpSession httpSession, Model model) {//model에 담아줄것이므로 model객체 필요

        SessionMember socialMember = (SessionMember) httpSession.getAttribute("socialMember"); //구글, 네이버 로그인
        SessionMember loginMember = (SessionMember) httpSession.getAttribute("loginMember"); //일반 로그인

        if (socialMember != null) {
            model.addAttribute("member", socialMember);
        } else if (loginMember != null) {
            model.addAttribute("member", loginMember);
        }
        return "account/register";
    }

    @PostMapping("register")
    public String register(AccountDTO account) {

        service.register(account);
        return "redirect:get";
    }

    @GetMapping("get")
    public String get(Model model, HttpSession httpSession) {
        SessionMember socialMember = (SessionMember) httpSession.getAttribute("socialMember");
        SessionMember loginMember = (SessionMember) httpSession.getAttribute("loginMember");

        if (socialMember != null) {
            model.addAttribute("account", service.get(socialMember.getId()));
        } else if (loginMember != null) {
            model.addAttribute("account", service.get(loginMember.getId()));
        }

        return "account/get";
    }

    @GetMapping("modify")
    public String modify(Model model, HttpSession httpSession) {

        SessionMember socialMember = (SessionMember) httpSession.getAttribute("socialMember");
        SessionMember loginMember = (SessionMember) httpSession.getAttribute("loginMember");

        if (socialMember != null) {
            model.addAttribute("member", socialMember);
            //socialMember의 정보를 member에 담는다(수정버튼 클릭시 같이 보내주기위해서)
            model.addAttribute("account", service.get(socialMember.getId()));
            //socialMember의 id를 가져올때는 getId 쓴다.(private 속성이므로)
        } else if(loginMember != null){
            model.addAttribute("member", loginMember);
            model.addAttribute("account", service.get(loginMember.getId()));

        }
        return "account/modify";
    }

    @PostMapping("modify")
    public String modify(AccountDTO account){
        service.modify(account);

        return "redirect:get";
    }

    @PostMapping("remove")
    public String remove(String member_id){
        service.remove(member_id);

        return "redirect:register";
    }

    @GetMapping("deposit")
    public String deposit(Model model, HttpSession httpSession){

        SessionMember socialMember = (SessionMember) httpSession.getAttribute("socialMember");
        SessionMember loginMember =(SessionMember) httpSession.getAttribute("loginMember");

        if (socialMember != null){
            model.addAttribute("member", socialMember);
            model.addAttribute("account", service.get(socialMember.getId()));

        }else if(loginMember != null){
            model.addAttribute("member", loginMember);
            model.addAttribute("account", service.get(loginMember.getId()));
        }
        return "account/deposit";
    }

    @PostMapping("deposit")
    public String deposit(AccountDTO account){

        service.deposit(account);

        return "redirect:get";
    }

    @GetMapping("withdrawal")
    public String withdrawal(Model model, HttpSession httpSession){

        SessionMember socialMember = (SessionMember) httpSession.getAttribute("socialMember");
        SessionMember loginMember = (SessionMember) httpSession.getAttribute("loginMember");

        if(socialMember != null){
            model.addAttribute("member", socialMember);
            model.addAttribute("account", service.get(socialMember.getId()));
        }else if(loginMember != null){
            model.addAttribute("member", loginMember);
            model.addAttribute("account", service.get(loginMember.getId()));
        }
        return "account/withdrawal";
    }

    @PostMapping("withdrawal")
    public String withdrawal(AccountDTO account, Model model, HttpSession httpSession){

        if(account.getBalance()-account.getMoney()>=0){

            service.withdrawal(account);
            model.addAttribute("alert", "출금 완료 되었습니다.");

        } else {
            SessionMember socialMember = (SessionMember) httpSession.getAttribute("socialMember");
            SessionMember loginMember = (SessionMember) httpSession.getAttribute("loginMember");

            if(socialMember != null){
                model.addAttribute("member", socialMember);
                model.addAttribute("account", service.get(socialMember.getId()));
            }else if(loginMember != null){
                model.addAttribute("member", loginMember);
                model.addAttribute("account", service.get(loginMember.getId()));
            }

            model.addAttribute("alert", "출금 가능 한도를 넘었습니다.");

            return "account/withdrawal";
        }

        return "redirect:get";
    }

    @GetMapping("transfer")
    public String transfer(Model model, HttpSession httpSession){
        SessionMember socialMember = (SessionMember) httpSession.getAttribute("socialMember");
        SessionMember loginMember = (SessionMember) httpSession.getAttribute("loginMember");

        if(socialMember != null){
            model.addAttribute("member", socialMember);
            model.addAttribute("account", service.get(socialMember.getId()));
        }else if(loginMember != null){
            model.addAttribute("member", loginMember);
            model.addAttribute("account", service.get(loginMember.getId()));
        }
        return "account/transfer";
    }

    @PostMapping("transfer")
    public String transfer(AccountDTO account, Model model, HttpSession httpSession){

        if(account.getBalance()-account.getMoney()>=0){

            service.transfer(account);

            return "redirect:get";

        }  else {
            SessionMember socialMember = (SessionMember) httpSession.getAttribute("socialMember");
            SessionMember loginMember = (SessionMember) httpSession.getAttribute("loginMember");

            if(socialMember != null){
                model.addAttribute("member", socialMember);
                model.addAttribute("account", service.get(socialMember.getId()));
            }else if(loginMember != null){
                model.addAttribute("member", loginMember);
                model.addAttribute("account", service.get(loginMember.getId()));
            }

            model.addAttribute("alert", "이체 가능 한도를 넘었습니다.");

            return "account/transfer";
        }
    }

    @PostMapping("existenceChk")
    @ResponseBody //result.jsp로 안가고 result 값만 보내고 싶을때...@ResponseBody
    public String existenceChk(@RequestBody String account_number){
        //ajax가 postMApping 요청보내면, body에 요청정보를 넣어서 보낸다. 받을때 @RequestBody
        //System.out.println("입구컷?");
        String result = service.existenceChk(account_number);
        //System.out.println(result);
        return result;
    }

    @PostMapping("accountChk")
    @ResponseBody
    public String accountChk(@RequestBody String memberId) {
//        System.out.println("memberId:"+ memberId);
        String result1 = service.accountChk(memberId);
        return result1;
    }


}
package com.example.demo.controller.member;

import com.example.demo.config.SessionMember;
import com.example.demo.domain.Criteria;
import com.example.demo.domain.PageDTO;
import com.example.demo.domain.member.dto.MemberDTO;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.service.member.MemberService;
import com.google.gson.JsonObject;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;


@RequestMapping("/admin/")
@RestController
@AllArgsConstructor
public class AdminController {

    MemberService memberService;


    public SessionMember adminSessionCheck(HttpSession httpSession){
        SessionMember sessionMember = (SessionMember) httpSession.getAttribute("loginMember");

        return sessionMember;
    }


   //get
   @GetMapping(value ="/{member_id}", produces = {"application/json"})
   public String get(@PathVariable("member_id") String id){

       Member member = memberService.get(id);

       JsonObject obj = new JsonObject();
       obj.addProperty("no",member.getMemberNo());
       obj.addProperty("id",member.getMemberId());
       obj.addProperty("name",member.getMemberName());
       obj.addProperty("email",member.getMemberEmail());
       obj.addProperty("age",member.getMemberAge());
       obj.addProperty("gender", member.getMemberGender());

       return obj.toString();
   }


   @PutMapping(value ="/{member_no}",consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
   public  ResponseEntity<String> modify(@RequestBody MemberDTO memberDTO, @PathVariable("member_no") String mno ){


        return memberService.update(memberDTO) == 1
                ?new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
   }


    @DeleteMapping(value = "/delete/{member_no}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> remove(@PathVariable("member_no")Long memberNo){

        return memberService.delete(memberNo) ==1
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/adminChk")
    @ResponseBody
    public String adminChk(@RequestBody String memberId) {

        String check = memberService.adminChk(memberId);

        return check;
    }

}

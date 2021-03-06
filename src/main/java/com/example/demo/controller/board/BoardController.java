package com.example.demo.controller.board;

import com.example.demo.config.SessionMember;
import com.example.demo.domain.board.BoardFileVO;
import com.example.demo.domain.board.BoardVO;
import com.example.demo.domain.Criteria;
import com.example.demo.domain.PageDTO;
import com.example.demo.domain.member.dto.MemberDTO;
import com.example.demo.domain.member.dto.vo.Role;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.service.board.BoardService;
import com.example.demo.service.board.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/board/")
@AllArgsConstructor
public class BoardController {

    @Autowired
    private BoardService service;

    @Autowired
    private ReplyService replyService;

    @GetMapping("register")
    public String register(Model model, HttpSession httpSession){

        SessionMember socialMember = (SessionMember) httpSession.getAttribute("socialMember");
        SessionMember loginMember = (SessionMember) httpSession.getAttribute("loginMember");

        if(socialMember != null){
            model.addAttribute("member", socialMember);
        } else if (loginMember != null){
            model.addAttribute("member", loginMember);
        }
        return "board/register";
    }

    @PostMapping("register")
    public String register(BoardVO board, String fileName, RedirectAttributes rttr){

        if(fileName != "") {
            board.setFileName(fileName);
        }
        service.register(board);
        rttr.addFlashAttribute("result", board.getBno());

        return "redirect:list";
    }

    @GetMapping("list") //???????????? ?????? ????????? ????????? ????????????????????? ??????
    public String list(Criteria cri, Model model, HttpSession httpSession){

        SessionMember socialMember = (SessionMember) httpSession.getAttribute("socialMember");
        SessionMember loginMember =(SessionMember) httpSession.getAttribute("loginMember");

        if(socialMember != null){
            model.addAttribute("list", service.getList(cri));
            int total = service.getTotal(cri);
            model.addAttribute("pageMaker", new PageDTO(cri, total));
            return "board/list";
            //????????? ???????????? ??????
        } else if (loginMember == null){
                return "redirect:/login";
        }

        model.addAttribute("list", service.getList(cri));
        //pageDTO??? ????????? ??? ????????? model??? ????????? ????????? ??????
        // PageDTO ??? ???????????? ????????? ?????? ????????? ?????? ????????????, ?????? ??? ????????? ?????? ??????????????? ????????? ??? 123 ??????

       int total = service.getTotal(cri);
        model.addAttribute("pageMaker", new PageDTO(cri, total));

        return "board/list";
    }

    @GetMapping("get")
    public String get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model, HttpSession httpSession){

        model.addAttribute("board", service.get(bno));

        return "board/get";
    }




    @GetMapping("modify")
    public String modify(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model){
        model.addAttribute("board", service.get(bno));

        return "board/modify";
    }

    @PostMapping("modify")
    public String modify(BoardVO board, String fileName, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){

        if(fileName != ""){
            board.setFileName(fileName);
           // System.out.println("ctr?????? set??? ????????????"+fileName);
        }

        if(service.modify(board)){
            rttr.addFlashAttribute("result", "success");
        }

        return "redirect:list" + cri.getListLink();
    }

    @PostMapping("remove")
    public String remove(@RequestParam("bno") Long bno,Criteria cri, RedirectAttributes rttr ){

        if(service.remove(bno)){

            rttr.addAttribute("result","success");
        }

        return "redirect:list" + cri.getListLink();
    }



}

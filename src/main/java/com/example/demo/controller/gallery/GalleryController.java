package com.example.demo.controller.gallery;


import com.example.demo.config.SessionMember;
import com.example.demo.domain.Criteria;


import com.example.demo.domain.gallery.GalleryS3DTO;

import com.example.demo.service.S3Uploader;
import com.example.demo.service.gallery.GalleryService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/gallery/")
@AllArgsConstructor
public class GalleryController {

    @Autowired
    private GalleryService service;

    @Autowired
    private final S3Uploader s3Uploader;

    @GetMapping("register")
    public String register(Model model, HttpSession httpSession){

        SessionMember socialMember = (SessionMember) httpSession.getAttribute("socialMember");
        SessionMember loginMember = (SessionMember) httpSession.getAttribute("loginMember");

        if(socialMember != null){
            model.addAttribute("member", socialMember);
        } else if (loginMember != null){
            model.addAttribute("member", loginMember);
        }
        return "gallery/register";
    }

    @PostMapping("register")
    public String register(GalleryS3DTO gallery, RedirectAttributes rttr){

        service.register(gallery);

        rttr.addFlashAttribute("result", gallery.getBno());

        return "redirect:list";
    }

    @GetMapping("list") //회원인지 체크 회원이 아니면 로그인페이지로 리턴
    public String list(Criteria cri, Model model, HttpSession httpSession){

        SessionMember socialMember = (SessionMember) httpSession.getAttribute("socialMember");
        SessionMember loginMember =(SessionMember) httpSession.getAttribute("loginMember");

        if (loginMember == null && socialMember == null){
            return "redirect:/login";
        } else {

            List<GalleryS3DTO> listFiles = service.getFiles();
            System.out.println();
            model.addAttribute("gallery", listFiles);

            return "gallery/list";
        }
    }


    @GetMapping("get")
    public String get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model, HttpSession httpSession){

        model.addAttribute("gallery", service.get(bno));
        return "gallery/get";
    }




    @GetMapping("modify")
    public String modify(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model){

        model.addAttribute("gallery", service.get(bno));

        return "gallery/modify";
    }

    @PostMapping("modify")
    public String modify(GalleryS3DTO gallery, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){

        if(service.modify(gallery)){
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

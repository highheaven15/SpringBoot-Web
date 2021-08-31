package com.example.demo.controller.asset;

import com.example.demo.config.SessionMember;
import com.example.demo.domain.asset.AssetVO;
import com.example.demo.service.asset.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@RequestMapping("/asset/")
@Controller
public class AssetController {

    @Autowired
    public AssetService service;

    @GetMapping("register") // localhost:8080/asset/register
    public String register(HttpSession httpSession, Model model) {

        SessionMember socialMember = (SessionMember) httpSession.getAttribute("socialMember");
        SessionMember loginMember = (SessionMember) httpSession.getAttribute("loginMember");

        if (socialMember != null) {
            model.addAttribute("member", socialMember);
        } else if(loginMember != null) {
            model.addAttribute("member", loginMember);
        } else if(loginMember == null && socialMember == null){
            return "redirect:/login";
        }

        return "asset/register";

    }

    @PostMapping("register")
    public String register(String[] stock_name, String[] stock_price, String[] stock_count, String[] member_id, AssetVO assetVO, RedirectAttributes rttr) {

        for (int i = 0; i < stock_name.length; i++) { // a[i] b[i] c[i]
            assetVO.setStock_name(stock_name[i]);
            assetVO.setStock_price(stock_price[i]);
            assetVO.setStock_count(stock_count[i]);
            assetVO.setMember_id(member_id[i]);

            service.register(assetVO);
        }
        String message = "자산이 등록되었습니다.";
        rttr.addFlashAttribute("result", message);

        return "redirect:get";
    }

    @GetMapping("get")
    public String get(Model model, HttpSession httpSession) {
        SessionMember socialMember = (SessionMember) httpSession.getAttribute("socialMember");
        SessionMember loginMember = (SessionMember) httpSession.getAttribute("loginMember");

        if (socialMember != null) {
            model.addAttribute("asset", service.get(socialMember.getId()) );
        } else if(loginMember != null) {
            model.addAttribute("asset", service.get(loginMember.getId()) );
        } else if(loginMember == null && socialMember == null){
            return "redirect:/login";
        }
//        System.out.println("-------------" + service.get(loginMember.getId()).getMember_id());

        return "asset/get";
    }

    @GetMapping("modify")
    public String modify(Model model, HttpSession httpSession) {

        SessionMember socialMember = (SessionMember) httpSession.getAttribute("socialMember");
        SessionMember loginMember = (SessionMember) httpSession.getAttribute("loginMember");

        if (socialMember != null) {
            model.addAttribute("member", socialMember);
            model.addAttribute("asset", service.get(socialMember.getId()) );
        } else if(loginMember != null) {
            model.addAttribute("member", loginMember);
            model.addAttribute("asset", service.get(loginMember.getId()) );
        }

        return "asset/modify";
    }

    @PostMapping("modify")
    public String modify(String[] stock_name, String[] stock_price, String[] stock_count, String[] asset_no,
                         String[] stock_name2, String[] stock_price2, String[] stock_count2, String[] member_id, AssetVO assetVO, RedirectAttributes rttr){

        for (int i = 0; i < stock_name.length; i++) { // a[i] b[i] c[i]
            assetVO.setStock_name(stock_name[i]);
            assetVO.setStock_price(stock_price[i]);
            assetVO.setStock_count(stock_count[i]);

            long[] no = Arrays.stream(asset_no).mapToLong(Long::parseLong).toArray();
            assetVO.setAsset_no(no[i]);  //parse Long

            service.modify(assetVO);
        }

        if(stock_name2 !=null) {
            for (int i = 0; i < stock_name2.length; i++) { // a[i] b[i] c[i]
                assetVO.setStock_name(stock_name2[i]);
                assetVO.setStock_price(stock_price2[i]);
                assetVO.setStock_count(stock_count2[i]);
                assetVO.setMember_id(member_id[i]);

                service.register(assetVO);
            }
        }

        String message = "자산이 수정되었습니다.";
        rttr.addFlashAttribute("result", message);

        return "redirect:get";
        }

    @PostMapping("remove")
    public String delete(Long asset_no, RedirectAttributes rttr){
        System.out.println("삭제된 번호" + asset_no );
        service.remove(asset_no);

        String message = "자산이 삭제되었습니다.";
        rttr.addFlashAttribute("result", message);

        return "redirect:get";
    }


    @PostMapping("assetChk")
    @ResponseBody
    public String assetChk(@RequestBody String memberId){
//        System.out.println("memberId from mustache to controller "+ memberId);
//        System.out.println("from controller:" + service.assetChk(memberId));
        String result = service.assetChk(memberId);
        return result;
    }


}

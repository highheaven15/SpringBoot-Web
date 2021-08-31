package com.example.demo.controller.stock;

import com.example.demo.domain.stock.StockInfoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/stock")
public class StockController {

    @GetMapping("/news")
    public void newPage(){
        //templates/news.mustache
    }

    @GetMapping("/stockPage")
    public void stockPage(){

    }

    @GetMapping("/stockDetail")
    public void stockDetail(@RequestParam("stock_code") String stockCode, @RequestParam("stock_name") String stockName
            ,@RequestParam("stock_price") String stockPrice,Model model){
        System.out.println(stockCode + " " + stockName);
        StockInfoDTO stockInfoDTO = new StockInfoDTO(stockName, stockCode,stockPrice);
        model.addAttribute("stockInfo", stockInfoDTO);
    }

}
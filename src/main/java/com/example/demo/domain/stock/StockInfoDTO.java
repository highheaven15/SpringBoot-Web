package com.example.demo.domain.stock;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockInfoDTO {

    private String stockName;
    private String stockCode;
    private String stockPrice;
}

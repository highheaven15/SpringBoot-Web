package com.example.demo.domain.asset;

import lombok.Data;
import java.util.List;

@Data
public class AssetVO {

    private Long asset_no;
    private Long member_no;
    private String member_id;

    private String stock_name;
    private String stock_code;
    private String stock_count;
    private String stock_price;

}

package com.example.demo.service.asset;

import com.example.demo.domain.asset.AssetVO;

import java.util.List;

public interface AssetService {

    public void register(AssetVO asset);

    public List<AssetVO> get(String member_id);

    public void modify(AssetVO asset);


    public void remove(Long asset_no);

    public List<AssetVO> getList();

    public String assetChk(String memberId);
}

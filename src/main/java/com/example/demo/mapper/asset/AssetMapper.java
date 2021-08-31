package com.example.demo.mapper.asset;

import com.example.demo.domain.asset.AssetVO;

import java.util.List;

public interface AssetMapper {

    public List<AssetVO> getList();

    public void insert(AssetVO asset);

    public List<AssetVO> read(String member_id);

    public void modify(AssetVO assetVO);

    public void delete (Long asset_no);

    public List<AssetVO> findMemberAssetInfo(String memberId);
}

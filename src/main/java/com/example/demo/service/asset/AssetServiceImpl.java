package com.example.demo.service.asset;

import com.example.demo.domain.asset.AssetVO;
import com.example.demo.mapper.asset.AssetMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class AssetServiceImpl implements AssetService{

    @Autowired
    private AssetMapper mapper;

    @Transactional
    @Override
    public void register(AssetVO asset) {
        mapper.insert(asset);
        return;
    }

    @Override
    public List<AssetVO> get(String member_id) {
        System.out.println("from service:" + member_id);

        return mapper.read(member_id);
    }


    @Override
    public void modify(AssetVO asset) {

        mapper.modify(asset);
    }



    @Override
    public void remove(Long asset_no) {
        mapper.delete(asset_no);
    }

    @Override
    public List<AssetVO> getList() {
        return null;
    }

    @Override
    public String assetChk(String memberId) {

//        System.out.println("from asset service:" + memberId);

        List<AssetVO> assetInfo = mapper.findMemberAssetInfo(memberId);
//        System.out.println("assetInfo"+assetInfo);
        if(!assetInfo.isEmpty()){
            return "1";
        }
        return "0";
    }


}

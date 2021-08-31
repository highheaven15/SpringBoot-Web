package com.example.demo.service.board;

import com.example.demo.domain.Criteria;
import com.example.demo.domain.board.ReplyPageDTO;
import com.example.demo.domain.board.ReplyVO;

import java.util.List;

public interface ReplyService {

    public int register(ReplyVO reply);

    public ReplyVO get(Long rno);

    public int modify(ReplyVO reply);

    public int remove(Long rno);

    public List<ReplyVO> getList(Criteria cri, Long bno);

    public ReplyPageDTO getListPage(Criteria cri, Long bno);


}

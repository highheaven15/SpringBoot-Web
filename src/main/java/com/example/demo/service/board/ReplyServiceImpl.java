package com.example.demo.service.board;

import com.example.demo.domain.Criteria;
import com.example.demo.domain.board.ReplyPageDTO;
import com.example.demo.domain.board.ReplyVO;
import com.example.demo.mapper.board.BoardMapper;
import com.example.demo.mapper.board.ReplyMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Setter(onMethod_ = @Autowired)
    private ReplyMapper mapper;

    @Setter(onMethod_ = @Autowired)
    private BoardMapper boardMapper;

    @Transactional
    @Override
    public int register(ReplyVO reply) {

        boardMapper.updateReplyCnt(reply.getBno(), 1);

        return mapper.insert(reply);
    }

    @Override
    public ReplyVO get(Long rno) {

        return mapper.read(rno);
    }

    @Override
    public int modify(ReplyVO reply) {

        return mapper.update(reply);
    }

    @Override
    public int remove(Long rno) {
        return mapper.delete(rno);
    }

    @Override
    public List<ReplyVO> getList(Criteria cri, Long bno) {

        return mapper.getListWithPaging(cri, bno);
    }

    @Override
    public ReplyPageDTO getListPage(Criteria cri, Long bno) {
        return new ReplyPageDTO(mapper.getCountByBno(bno), mapper.getListWithPaging(cri, bno));
    }
}

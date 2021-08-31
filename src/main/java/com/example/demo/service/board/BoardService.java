package com.example.demo.service.board;

import com.example.demo.domain.board.AttachFileDTO;
import com.example.demo.domain.board.BoardFileVO;
import com.example.demo.domain.board.BoardVO;
import com.example.demo.domain.Criteria;
import com.example.demo.domain.member.entity.Member;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface BoardService {

    public void register(BoardVO board);

    public BoardVO get(Long bno);

    public boolean modify(BoardVO board);

    public boolean remove(Long bno);

   // public List<BoardVO> getList();

    public List<BoardVO> getList(Criteria cri);

    public List<BoardFileVO> getFileList(Long bno);

    public int getTotal (Criteria cri);
}







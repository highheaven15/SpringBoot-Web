package com.example.demo.mapper.board;

import com.example.demo.domain.board.BoardFileVO;
import com.example.demo.domain.board.BoardVO;
import com.example.demo.domain.Criteria;
import org.springframework.data.repository.query.Param;
//import org.hibernate.Criteria;

import java.util.List;

public interface BoardMapper {

    public List<BoardVO> getList();

    public void insert(BoardVO board);

    public Integer insertSelectKey(BoardVO board);

    public BoardVO read(Long bno);

    public int delete(Long bno);

    public int update(BoardVO board);

    public int getTotalCount(Criteria cri);

    public List<BoardVO> getListWithPaging(Criteria cri);

    public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);

    public List<BoardFileVO> findByBno(Long bno);

    public void updateHit(@Param("bno") Long bno);
}

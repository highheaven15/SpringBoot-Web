package com.example.demo.mapper.board;

import com.example.demo.domain.board.AttachFileDTO;
import com.example.demo.domain.board.BoardFileVO;

import java.util.List;

public interface BoardFileMapper {

    public void insert(AttachFileDTO file);

    public void delete(Long fno);

    public List<BoardFileVO> findByBno(Long bno);

    public void deleteAll(Long bno);

    public List<BoardFileVO> getOldFiles();

    public String getFileNameByBno(Long bno);
}


package com.example.demo.controller.board;

import com.example.demo.domain.Criteria;
import com.example.demo.domain.board.ReplyPageDTO;
import com.example.demo.domain.board.ReplyVO;
import com.example.demo.service.board.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RequestMapping("/replies/")
@RestController
@AllArgsConstructor
public class ReplyController {

    private ReplyService service;


//register
    @PostMapping(value ="/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> create(@RequestBody  ReplyVO reply){

        int insertCount = service.register(reply);

        return insertCount == 1
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }





    //get
    @GetMapping(value="/{rno}", produces = {"application/json"})
    public ResponseEntity<ReplyVO> get(@PathVariable("rno") String rnos, HttpSession httpSession){

        rnos = rnos.replace(".json", "");
        Long rno = Long.parseLong(rnos);

        httpSession.setAttribute("reply", service.get(rno));

        return new ResponseEntity<>(service.get(rno), HttpStatus.OK);

    }

//modify (modify 전송방식 - put, patch)

    @PutMapping(value = "/{rno}", consumes = "application/json",
                    produces = {MediaType.TEXT_PLAIN_VALUE})
     // @ResponseBody - test중
    public ResponseEntity<String> modify(@RequestBody ReplyVO reply, @PathVariable("rno") Long rno){
        reply.setRno(rno);

        return service.modify(reply) == 1
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

//delete
    @DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno){

        return service.remove(rno) == 1
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

//Paging

    @GetMapping(value = "/pages/{bno}/{page}",
                 produces = {MediaType.APPLICATION_XML_VALUE,
                         MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") String pages, @PathVariable("bno") Long bno){

        pages = pages.replace(".json", "");
        int page = Integer.parseInt(pages);
        Criteria cri = new Criteria(page, 10);

        return new ResponseEntity<>(service.getListPage(cri, bno), HttpStatus.OK);

    }


}

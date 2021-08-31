package com.example.demo.controller.board;

import com.example.demo.domain.board.AttachFileDTO;
import com.example.demo.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UploadController {


    private final S3Uploader s3Uploader;

    @GetMapping("/index")
    public String index() {
        return "index";
    }


    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("data") MultipartFile file) throws IOException {

        log.info("/upload 도착!");
        AttachFileDTO attachFileDTO = new AttachFileDTO();
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        attachFileDTO.setFileName(fileName);
        System.out.println(attachFileDTO.getFileName());
        return s3Uploader.upload(file, "static");

    }

    @PostMapping("/show")
    @ResponseBody
    public String show(@RequestBody String fileName) {

        log.info("/show 도착!");

        return s3Uploader.getS3(fileName, "static");
    }


}




package com.example.demo.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.domain.board.BoardFileVO;
import com.example.demo.mapper.board.BoardFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.Setter;


@Component		//클래스 위에 붙여주는 어노테이션 (객체를 생성해줌)
public class FileCheckTask {

    @Setter(onMethod_ = { @Autowired })
    private BoardFileMapper fileMapper;

    private String getFolderYesterDay() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DATE, -1);
//yesterday
        String str = sdf.format(cal.getTime());

        return str.replace("-", File.separator);
    }
    // @Scheduled - 일정 시간 주기적으로 작업하는 스케줄러
    //오른쪽부터 년 요일 월 시 분 초 * (매일)

    @Scheduled(cron = "0 0 9 * * *")
    public void checkFiles() throws Exception {
//		log.warn("File Check Task run.................");
//		log.warn("====================================");

        // file list in database   //db에서 파일리스트를 불러옴
        List<BoardFileVO> fileList = fileMapper.getOldFiles();

        // stream이란 장치(Device - 하드웨어 장치)로부터 데이터를 읽거나 기록할 때 사용
        //files 경로를  리스트에 담기
        // (db에 있는 데이터와 실제 디렉토리 안에 있는 파일을 비교를 위해)
        // ready for check file in directory with database file list
        //비교를 위해 리스트로 가져옴
        List<Path> fileListPaths = fileList.stream()

                .map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName()))
                .collect(Collectors.toList());

        //fileType이 true면 image file - 썸네일 파일 s_ 을 가져옴.
        // image file has thumnail file 썸네일 파일을 가져옴
        //파일타입이 true- image file
        fileList.stream().filter(vo -> vo.isFileType() == true)
                .map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), "s_" + vo.getUuid() + "_" + vo.getFileName()))
                .forEach(p -> fileListPaths.add(p));


        // files in yesterday directory(targetDir) 20/03/02
        //targetDir - 어제 디렉토리에 있던 파일들
        File targetDir = Paths.get("C:\\upload", getFolderYesterDay()).toFile();

        //false- 파일  removeFiles에 대입
        //어제 디렉토리에 있던 파일들(targetDir)이 fileList에 없는 파일이면 삭제하도록
        File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);

        //테이블에 없는 데이터들을 삭제해준다. for-each문
        for (File file : removeFiles) {
//보드 테이블에 없는 데이터들을 삭제해준다.

            file.delete();

        }
    }
}

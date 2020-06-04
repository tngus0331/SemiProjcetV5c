package suhyun.spring.mvc.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component("imgutil")
public class ImageUploadUtil {

    // 이미지 업로드 경로 설정
    private String IMG_UPLOAD_PATH = "c:/Java/nginx-1.18.0/html/cdn/";

    // 갤러리에 이미지 첨부시 파일 존재 여부 확인
    public boolean checkGalleryFiles(MultipartFile[] img) {
        boolean isFiles = false;

        for(MultipartFile f : img) {
            // 첨부시 파일 이름이 존재한다면
            if(!f.getOriginalFilename().isEmpty()) {
                isFiles = true;
                break;
            }
        }

        return isFiles;
    }

    // 업로드한 이미지들 중 첫번째 이미지에 대한 썸네일 생성
    public void imageCropResize(String fname1, String id) {

    }

    // 겹치지 않는 파일명을 위해 유니크한 임의의 값 생성
    private String makeUUID() {
        String fmt = "yyyyMMddHHmmss";
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);

        return sdf.format(new Date());
    }

    // 이미지 파일 업로드
    public String ImageUplaod(MultipartFile mf) {
        // 업로드시 첨부 파일의 원래 이름
        String ofname = mf.getOriginalFilename();
        // 업로드한 파일을 서버에 저장할 때 사용할 수정된 파일 이름
        String nfname = makeUUID() + "_" + ofname;

        try {
            mf.transferTo(new File(IMG_UPLOAD_PATH + nfname));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return nfname;
    }

};
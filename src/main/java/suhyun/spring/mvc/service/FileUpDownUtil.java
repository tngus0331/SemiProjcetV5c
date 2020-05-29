package suhyun.spring.mvc.service;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

public class FileUpDownUtil {
    private String uploadPath = "c:/Java/pdsupload/";
    // 파일 업로드시 저장된 경로 지정

    // 업로드 처리 메서드
    public Map<String, String> procUpload(HttpServletRequest req) {

        Map<String, String> frmdata = new HashMap<>();

        String fname = "";

        // 요청한 폼데이터가 multipart인지 확인
        RequestContext rctx = new ServletRequestContext(req);
        boolean isMultiPart = FileUpload.isMultipartContent(rctx);

        try {
            if(isMultiPart) { // 클라이언트 요청이 multipart라면
                DiskFileItemFactory df = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(df);

                // 클라이언트의 요청정보를 리스트에 저장
                List items = upload.parseRequest(req);
                Iterator<FileItem> params = items.iterator();

                // 리스트에 저장된 요청정보를 하나씩 꺼내서
                // 폼 데이터의 유형에 따라 각각 처리
                while (params.hasNext()) {
                    try {
                        FileItem item = (FileItem) params.next();

                        if (item.isFormField()) { // 텍스트 데이터라면
                            String name = item.getFieldName();
                            String val = item.getString("utf-8");
                            frmdata.put(name, val);
                        } else { // 파일 데이터라면
                            String ufname = item.getName(); // 파일 경로 추출

                            // ex) ufname => c:/Java/jobs.txt
                            fname = ufname.substring(ufname.lastIndexOf("\\") + 1); // 파일명 추출

                            // ex) fname => jobs.txt
                            // 겹치지 않는 파일명을 위해 유니크한 임의의 값 생성
                            UUID uuid = UUID.randomUUID();
                            String fnames[] = fname.split("[.]");
                            fname = fnames[0] + uuid + "." + fnames[1];

                            // ex) fname => jobs123456789.txt
                            // ex) f => c:/Java/pdsupload/jobs123456789.txt
                            File f = new File(uploadPath + "/" + fname);
                            item.write(f); // 지정한 경로에 파일 저장

                            String name = item.getFieldName();
                            frmdata.put(name, fname);

                            // 파일명 처리 결과
                            System.out.println(ufname + "/" + fname);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } // while

            } // if
        } catch (Exception ex) {
            ex.printStackTrace();
        } // try

        return frmdata;
    }

    // 다운로드 처리 메서드
};
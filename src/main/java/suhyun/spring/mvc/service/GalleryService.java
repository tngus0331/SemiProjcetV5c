package suhyun.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import suhyun.spring.mvc.dao.GalleryDAO;
import suhyun.spring.mvc.vo.GalleryVO;

@Service("gsrv")
public class GalleryService {

    private GalleryDAO gdao;

    @Autowired
    public GalleryService(GalleryDAO gdao) {
        this.gdao = gdao;
    }

    // 새 갤러리 쓰기
    public void newGallery(GalleryVO gvo) {
        gdao.insertGallery(gvo);
    }


}

package suhyun.spring.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import suhyun.spring.mvc.vo.GalleryVO;

@Repository("gdao")
public class GalleryDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GalleryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertGallery(GalleryVO gvo) {

    }

}

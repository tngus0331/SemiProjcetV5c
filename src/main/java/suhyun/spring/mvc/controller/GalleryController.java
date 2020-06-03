package suhyun.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import suhyun.spring.mvc.service.GalleryService;
import suhyun.spring.mvc.vo.GalleryVO;

@Controller
public class GalleryController {
    private GalleryService gsrv;

    @Autowired
    public GalleryController(GalleryService gsrv) {
        this.gsrv = gsrv;
    }

    // 목록보기
    @RequestMapping(value = "/gallery/list")
    public String list() {
        return "redirect:/gallery/list";
    }

    // 새글쓰기
    @PostMapping(value = "/gallery/write")
    public String write(GalleryVO gvo) {
        gsrv.newGallery(gvo);
        return "redirect:/gallery/write";
    }


    @RequestMapping(value = "/gallery/view")
    public ModelAndView view() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("layout/layout"); // 뷰이름 지정
        mv.addObject("action", "../gallery/view.jsp");
        // 뷰로 넘길 데이터를 modelandview 객체에 담음
        return mv;
    }
}


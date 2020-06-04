package suhyun.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
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

    @PostMapping(value = "gallery/list")
    public String list(GalleryVO gvo) {

        return "redirect:/gallery/list";
    }

    // 새글 쓰기
    // 여러개의 이미지를 업로드하는 경우
    // 이미지 폼 이름은 모두 동일하게 설정함
    // MultipartFile img1,2,3,
    // 를 배열로 받음
    @PostMapping(value = "gallery/write")
    public String write(GalleryVO gvo, MultipartFile[] img) {

        gsrv.newGallery(gvo, img);

        return "redirect:/gallery/list";
    }

    @RequestMapping(value = "gallery/write")
    public ModelAndView write() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("layout/layout");
        mv.addObject("action","../gallery/write.jsp");
        return mv;
    }

    @RequestMapping(value = "gallery/list")
    public ModelAndView list() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("layout/layout");
        mv.addObject("action","../gallery/list.jsp");
        return mv;
    }

    @RequestMapping(value = "gallery/view")
    public ModelAndView view() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("layout/layout");
        mv.addObject("action","../gallery/list.jsp");
        return mv;
    }
};
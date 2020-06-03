package suhyun.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import suhyun.spring.mvc.service.BDReplyService;
import suhyun.spring.mvc.service.BoardService;
import suhyun.spring.mvc.vo.BDReplyVO;
import suhyun.spring.mvc.vo.BoardVO;

import java.util.ArrayList;

@Controller
public class BoardController {

    private BoardService bsrv;
    private BDReplyService brsrv;

    @Autowired
    // 2개의 매개변수를 생성자를 통해 DI로 받음
    public BoardController(BoardService bsrv, BDReplyService brsrv) {
        this.bsrv = bsrv;
        this.brsrv = brsrv;
    }

    // 목록보기
    @RequestMapping(value = "/board/list")
    public ModelAndView list(String cp) {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("layout/layout"); // 뷰이름 지정
        mv.addObject("action", "../board/list.jsp");

        // 목록 불러오기
        ArrayList<BoardVO> bdlist = bsrv.showBoard(cp);
        mv.addObject("bdlist", bdlist);

        // 총 게시물 수 불러오기
        int bdcnt = bsrv.countBoard();
        mv.addObject("bdcnt", bdcnt);

        return mv;
    }

    // 새글쓰기
    @RequestMapping(value = "/board/write")
    public ModelAndView write() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("layout/layout"); // 뷰이름 지정

        mv.addObject("action", "../board/write.jsp");

        return mv;
    }

    // 새글쓰기
    @RequestMapping(value = "/board/write", method = RequestMethod.POST)
    public String writeok(BoardVO bd) {

        bsrv.newBoard(bd);

        return "redirect:/board/list";
    }

    // 본문보기
    @RequestMapping(value = "/board/view")
    public ModelAndView view(String bno) {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("layout/layout"); // 뷰이름 지정
        mv.addObject("action", "../board/view.jsp");

        // 본문글
        BoardVO b = bsrv.showOneBoard(bno);
        mv.addObject("b", b);

        // 본문글에 대한 댓글과 대댓글
        ArrayList<BDReplyVO> r = brsrv.showReply(bno);
        mv.addObject("r", r);

        return mv;
    }

    // 수정하기
    @RequestMapping(value = "/board/update")
    public ModelAndView update() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("layout/layout"); // 뷰이름 지정

        mv.addObject("action", "../board/modify.jsp");

        return mv;
    }

    // 삭제하기
    @RequestMapping(value = "/board/delete")
    public String delete(String bno) {
        bsrv.removeBoard(bno);

        return "redirect:/board/list?cp=1";
    }

}

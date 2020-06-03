package suhyun.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import suhyun.spring.mvc.service.BDReplyService;
import suhyun.spring.mvc.vo.BDReplyVO;

@Controller
public class BDReplyController {

    private BDReplyService brsrv;

    @Autowired
    public BDReplyController(BDReplyService brsrv) {
        this.brsrv = brsrv;
    }

    // 댓글쓰기 완료
    @PostMapping(value = "/reply/bdrpywrite")
    public String bdrpywrite(BDReplyVO rvo) {

        brsrv.makeReply(rvo);

        return "redirect:/board/view.do?bno=" + rvo.getBno();
    }

    // 대댓글쓰기 완료
    @PostMapping(value = "/reply/bdcmtwrite")
    public String bdcmtwrite(BDReplyVO rvo) {

        brsrv.makeComment(rvo);

        return "redirect:/board/view.do?bno=" + rvo.getBno();
    }
}

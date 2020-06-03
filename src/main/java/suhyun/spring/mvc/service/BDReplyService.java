package suhyun.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import suhyun.spring.mvc.dao.BDReplyDAO;
import suhyun.spring.mvc.vo.BDReplyVO;

import java.util.ArrayList;
import java.util.List;

@Service("brsrv")
public class BDReplyService {

    private BDReplyDAO brdao;

    @Autowired
    public BDReplyService(BDReplyDAO brdao) {
        this.brdao = brdao;
    }

    // 댓글쓰기
    public void makeReply(BDReplyVO rvo) {
        brdao.insertReply(rvo);
    }


    // 댓글과 대댓글 가져오기
    public ArrayList<BDReplyVO> showReply(String bno) {
        return (ArrayList<BDReplyVO>) brdao.selectReply(bno);
    }

    // 대댓글 쓰기
    public void makeComment(BDReplyVO rvo) {
        brdao.insertComment(rvo);
    }
}

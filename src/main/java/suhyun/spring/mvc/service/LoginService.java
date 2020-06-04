package suhyun.spring.mvc.service;

import org.springframework.stereotype.Service;
import suhyun.spring.mvc.dao.LoginDAO;
import suhyun.spring.mvc.vo.MemberVO;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;

@Service("lsrv")
public class LoginService {

    private LoginDAO ldao;

    public LoginService(LoginDAO ldao) {
        this.ldao = ldao;
    }

    // 로그인 체크
    public boolean checkLogin(MemberVO mvo, HttpSession sess) {
        boolean isLogin = false;

        // 로그인 성공시 회원정보(ID)를 세션에 저장
        if(ldao.selectLogin(mvo) > 0) {
            sess.setAttribute("UID", mvo.getUserid());

            isLogin = true;
        }

        return isLogin;

    }


}

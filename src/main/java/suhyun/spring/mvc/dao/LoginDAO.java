package suhyun.spring.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import suhyun.spring.mvc.vo.MemberVO;

@Repository("ldao")
public class LoginDAO {

    private JdbcTemplate jdbcTemplate;

    @Value("#{jdbc['selectLoginSQL']}") private String selectLoginSQL;

    @Autowired
    public LoginDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 로그인 여부 확인
    public int selectLogin(MemberVO mvo) {
        Object[] params = new Object[] {
            mvo.getUserid(), mvo.getPasswd()
        };

        return jdbcTemplate.queryForObject(
                selectLoginSQL, params, Integer.class
        );

    }


}

package suhyun.spring.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import suhyun.spring.mvc.vo.PdsVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("pdao")
public class PdsDAO {

    private JdbcTemplate jdbcTemplate;

    @Value("#{jdbc['insertPdsSQL']}") private String insertPdsSQL;
    @Value("#{jdbc['selectPdsSQL']}") private String selectPdsSQL;
    @Value("#{jdbc['selectOnePdsSQL']}") private String selectOnePdsSQL;

    @Autowired
    public PdsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 자료실 데이터를 pds테이블에 저장
    public boolean insertPds( PdsVO p ) {

        // 매개변수 정의
        Object[] params = new Object[] {
             p.getTitle(), p.getUserid(), p.getContents(),
             p.getFname(), p.getFsize(), p.getFdown(), p.getFtype()
        };

        // 매개변수 타입 정의 - 생략 ^^;

        // 샐행
        boolean isInsert = false;
        if (jdbcTemplate.update(insertPdsSQL, params) > 0)
            isInsert = true;

        return isInsert;
    }

    // 자료실 목록 출력
    public List<PdsVO> selectPds() {

        RowMapper<PdsVO> mapper = new PdsRowMapper();

        return jdbcTemplate.query(selectPdsSQL, mapper);
    }

    // 글번호로 본문글 조회
    public PdsVO selectOnePds(String pno) {

        Object[] params = new Object[] { pno };

        RowMapper<PdsVO> mapper = new PdsOneMapper();

        PdsVO pvo = jdbcTemplate.queryForObject(
                      selectOnePdsSQL, mapper, params);

        return pvo;
    }

    // selectBoard의 RowMapper 내부 클래스
    private class PdsRowMapper implements RowMapper<PdsVO> {

        @Override
        public PdsVO mapRow(ResultSet rs, int num) throws SQLException {

            PdsVO pvo = new PdsVO(
                 rs.getString("pno"),
                 rs.getString("title"),
                 rs.getString("userid"),
                 rs.getString("regdate"),
                 rs.getString("thumbup"),
                 rs.getString("views"),
                 null, null, null, null, null
            );

            return pvo;
        }
    }

    // selectOnePds의 RowMapper 내부 클래스
    private class PdsOneMapper implements RowMapper<PdsVO> {
        @Override
        public PdsVO mapRow(ResultSet rs, int num) throws SQLException {

            PdsVO pvo = new PdsVO(
                    rs.getString("pno"),
                    rs.getString("title"),
                    rs.getString("userid"),
                    rs.getString("regdate"),
                    rs.getString("thumbup"),
                    rs.getString("views"),
                    rs.getString("contents"),
                    rs.getString("fname"),
                    rs.getString("fsize"),
                    rs.getString("fdown"),
                    rs.getString("ftype")
            );

            return pvo;
        }
    }

}

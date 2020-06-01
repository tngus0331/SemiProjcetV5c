<%@ page  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%
    // 게시판 리스트 처리 - 페이징
    // 1. 전체 게시물 수 처리 (bdcnt)
    // 2. 페이지당 보여줄 게시판 수 지정 (=10)
    // 3. 총 페이지 수 계산
    // 4. 현재 페이지 번호 (cp)
%>

<%
    // 게시판 리스트 처리 - 네비게이션
    // 현재 페이지에 따라서 보여줄 페이지 블록을 지정
    // ex) cp = 1 : 1 2 3 .. 10 다음
    // ex) cp = 3 : 1 2 3 .. 10 다음
    // ex) cp = 9 : 1 2 3 .. 10 다음
    // ex) cp = 11 : 이전 11 12 13 .. 20 다음
    // ex) cp = 15 : 이전 11 12 13 .. 20 다음
    // ex) cp = 23 : 이전 21 22 23 .. 30 다음
    // ex) cp = 마지막 페이지 : 이전 51 52 53 54 55

    // startPage = ((cp-1) / 10) * 10 + 1
    // endPage = startPage + 10 - 1
%>


    <%--<c:set var="cp" value="${param.cp}"/>--%>
    <fmt:parseNumber var="cp" value="${param.cp}"/>
    <fmt:parseNumber var="perPage" value="10"/>
    <fmt:parseNumber var="bdcnt" value="${bdcnt}"/>

    <%--<c:set var="totalPage" value="${bdcnt/10}"/>--%>
    <c:set var="totalPage" value="${bdcnt/perPage}"/>
    <c:if test="${(bdcnt % perPage) > 0}">
        <c:set var="totalPage" value="${totalPage + 1}"/>
    </c:if> <%-- 올림 처리 --%>

    <fmt:parseNumber var="totalPage" value="${totalPage}" integerOnly="true"/>

    <fmt:parseNumber var="startPage" integerOnly="true" value="${((cp-1)/perPage)}"/>
    <fmt:parseNumber var="startPage" value="${startPage*10+1}"/>
    <c:set var="endPage" value="${startPage + 10 - 1}"/>


    <!-- 메인영역 시작 -->
    <div id="main">
        <div class="margin30">
            <i class="fa fa-comments fa-2x"> 자유게시판
            ${bdcnt} ${totalPage} ${endPage} </i>
            <hr>
        </div> <!-- 타이틀 -->

        <div class="row margin1050">
            <div class="col-12 text-right">
                <button type="button" id="newbd"
                        class="btn btn-light">
                    <i class="fa fa-plus-circle"> </i>
                    새글쓰기
                </button>
            </div>
        </div><!-- 버튼들 -->

        <div class="row margin1050">
            <div class="col-12">
                <table class="table table-striped"
                    style="border-bottom: 3px solid black;
                           border-top: 3px solid black">
                    <thead style="background: #dff0d8">
                        <tr><th style="width: 7%">번호</th>
                            <th>제목</th>
                            <th style="width: 12%">작성자</th>
                            <th style="width: 10%">작성일</th>
                            <th style="width: 7%">추천</th>
                            <th style="width: 7%">조회</th></tr>
                    </thead>
                    <tbody>
                        <tr><th>공지</th>
                            <th><span class="badge badge-danger">Hot</span>
                            '다크나이트 라이지즈' 예매권 증정이벤트 실시!!</th>
                            <th>운영자</th>
                            <th>2012.07.15</th>
                            <th>10</th>
                            <th>128</th></tr>

                        <c:forEach var="b" items="${bdlist}">
                            <tr><td>${b.bno}</td>
                            <td><a href="board/view.do?bno=${b.bno}">
                                                ${b.title}</a></td>
                            <td>${b.userid}</td>
                            <td>${ fn:substring(b.regdate,0,10) }</td>
                            <td>${b.thumbup}</td>
                            <td>${b.views}</td></tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </div><!-- 게시판 목록 -->

        <div class="row margin1050">
            <div class="col-12">
                <nav>
                    <ul class="pagination justify-content-center">
                        <%-- 현재 페이지가 10보다 크면 '이전'을 표시하지 않음 --%>
                        <c:if test="${cp > 10}">
                        <li class="page-item">
                            <a href="/board/list.do?cp=${cp-10}" class="page-link">이전</a></li>
                        </c:if>

                        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">

                            <%--현재 페이지가 총 페이지 수보다 같거나 작으면 출력--%>
                            <c:if test="${i le totalPage}">

                                <c:if test="${i ne cp}">
                                    <li class="page-item">
                                        <a href="/board/list.do?cp=${i}" class="page-link">${i}</a></li>
                                </c:if>

                                <c:if test="${i eq cp}">
                                    <li class="page-item active">
                                        <a href="/board/list.do?cp=${i}" class="page-link">${i}</a></li>
                                </c:if>

                            </c:if>


                        </c:forEach>


                        <%--  --%>
                        <c:if test="${endPage < totalPage}">
                        <li class="page-item">
                            <a href="/board/list.do?cp=${cp+10}" class="page-link">다음</a></li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div><!-- 페이지 네비게이션 -->

    </div>
    <!-- 메인영역 끝 -->


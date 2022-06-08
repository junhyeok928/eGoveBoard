<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div id="header">
            <h1 class="tts">제목</h1>
            <div class="util">
                <ul>
                    <li><a href="/ktl/main.do">HOME</a></li>
                    
                    <!-- <li><a href="javascript:;">ADMIN</a></li> -->
                </ul>
            </div>
            <c:if test="${!empty loginVO&&!empty loginVO.id}">
            <div class="util_2">
                <ul>
                    <li><a href="/uat/uia/actionLogout.do">Logout</a></li>
                    <li><a href="/user/changePwView.do">Change Password</a></li>
                </ul>
            </div>
            </c:if>
            <h2 class="tts">주메뉴</h2>
            <nav id="nav">
                <div class="gnb-bg"></div>
                <div class="inner">
                    <ul class="gnb">
                        <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000012&menuCl=INFO">안내</a>
                            <div class="depth02">
                                <ul>
                                    <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000012&menuCl=INFO">공지사항</a></li>
                                    <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000041&menuCl=DTADL">신청자료 다운로드</a></li>
                                    <li><a href="/user/Infomation/Info_KOLASfiled.do">KOLAS 인정분야</a></li>
                                    <li><a href="/user/Infomation/Info_location.do">찾아오시는 길</a></li>
                                </ul>
                            </div>
                        </li>
                        <li><a href="/user/GS_Authentication/GS_Info.do">GS인증</a>
                            <div class="depth02">
                                <ul>
                                    <li><a href="/user/GS_Authentication/GS_Info.do">평가인증안내</a></li>
                                    <li><a href="/user/GS_Authentication/GS_Info_Eng.do">Information</a></li>
                                    <li><a href="/user/GS_Authentication/GS_productCurrent.do">인증제품현황</a></li>
                                    <li><a href="/user/GS_Authentication/GS_productCurrent_Eng.do">인증제품현황(ENG)</a></li>
                                    <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000041&menuCl=GS">자료실</a></li>
                                </ul>
                            </div>
                        </li>
                        <li><a href="/user/KTL_Mark/isoList.do">KTL마크인증</a>
                            <div class="depth02">
                                <ul>
                                    <li><a href="/user/KTL_Mark/isoList.do">ISO/IEC 25023</a></li>
                                    <li><a href="/user/KTL_Mark/privacyList.do">PRIVACY</a></li>
                                    <li><a href="/user/KTL_Mark/spiceList.do">SPICE (ISO/IEC 15504-5)</a></li>
                                    <li><a href="/user/KTL_Mark/dataQualityList.do">Data Quality (ISO/IEC 25024)</a></li>
                                    <li><a href="/user/KTL_Mark/vseList.do">VSE (ISO/IEC 29110)</a></li>
                                </ul>
                            </div>
                        </li>
                        <li><a href="/user/SW_Test/SW_Test_Info.do">SW 시험</a>
                            <div class="depth02">
                                <ul>
                                    <li><a href="/user/SW_Test/SW_Test_Info.do">시험평가안내</a></li>
                                    <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000041&menuCl=SWEXPR">자료실</a></li>
                                </ul>
                            </div>
                        </li>
                        <li><a href="/user/V&V/V&V_Info.do">소프트웨어V&amp;V</a>
                            <div class="depth02">
                                <ul>
                                    <li><a href="/user/V&V/V&V_Info.do">사업안내</a></li>
                                    <li><a href="/user/V&V/V&V_Info_Eng.do">Information</a></li>
                                    <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000041&menuCl=VV">자료실</a></li>
                                </ul>
                            </div>
                        </li>
                        <li><a href="/user/Technology/Tech_Info.do">기술이전/교육/컨설팅</a>
                            <div class="depth02">
                                <ul>
                                    <li><a href="/user/Technology/Tech_Info.do">안내</a></li>
                                    <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000041&menuCl=TCNTRN">자료실</a></li>
                                </ul>
                            </div>
                        </li>
                        <li><a href="/user/IT&SW/IT&SW_Info.do">IT&amp;SW 교육</a>
                            <div class="depth02">
                                <ul>
                                    <li><a href="/user/IT&SW/IT&SW_Info.do">사업안내</a></li>
                                    <li><a href="/user/IT&SW/swCurriculumList.do">교육과정</a></li>
                                    <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000041&menuCl=ITEDC">자료실</a></li>
                                </ul>
                            </div>
                        </li>
                        <li><a href="/user/SWforum/SWforumJoinList.do">소프트웨어품질포럼</a>
                            <div class="depth02">
                                <ul>
                                	<li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000012&menuCl=SWFOR">공지사항</a></li>
                                    <li><a href="/user/SWforum/SWforumJoinList.do">포럼 참여기관정보</a></li>
                                    <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000031">KOLAS 정보</a></li>
                                    <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000021&menuCl=25023">기술지원</a></li>
                                    <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000051">자유게시판</a></li>
                                    <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000041&menuCl=SWFOR">자료실</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="lnb">
                <a href="javascript:;"><img src="/images/user/common/all-menu.png" alt="전체메뉴"></a>
            </div>
        </div>
        <div class="all-menu">
            <div class="inner">
                <div class="menu">
                    <strong>안내</strong>
                    <ul>
                        <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000012&menuCl=INFO">공지사항</a></li>
                        <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000041&menuCl=DTADL">신청자료 다운로드</a></li>
                        <li><a href="/user/Infomation/Info_KOLASfiled.do">KOLAS 인정분야</a></li>
                        <li><a href="/user/Infomation/Info_location.do">찾아오시는 길</a></li>
                    </ul>
                </div>
                <div class="menu">
                    <strong>GS인증</strong>
                    <ul>
                        <li><a href="/user/GS_Authentication/GS_Info.do">평가인증안내</a></li>
                        <li><a href="/user/GS_Authentication/GS_Info_Eng.do">Information</a></li>
                        <li><a href="/user/GS_Authentication/GS_productCurrent.do">인증제품현황</a></li>
                        <li><a href="/user/GS_Authentication/GS_productCurrent_Eng.do">인증제품현황(ENG)</a></li>
                        <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000041&menuCl=GS">자료실</a></li>
                    </ul>
                </div>
                <div class="menu">
                    <strong>KTL마크인증</strong>
                    <ul>
                        <li><a href="/user/KTL_Mark/isoList.do">ISO/IEC 25023</a></li>
                        <li><a href="/user/KTL_Mark/privacyList.do">PRIVACY</a></li>
                        <li><a href="/user/KTL_Mark/spiceList.do">SPICE (ISO/IEC 15504-5)</a></li>
                        <li><a href="/user/KTL_Mark/dataQualityList.do">Data Quality (ISO/IEC 25024)</a></li>
                        <li><a href="/user/KTL_Mark/vseList.do">VSE (ISO/IEC 29110)</a></li>
                    </ul>
                </div>
                <div class="menu">
                    <strong>SW 시험</strong>
                    <ul>
                        <li><a href="/user/SW_Test/SW_Test_Info.do">시험평가안내</a></li>
                        <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000041&menuCl=SWEXPR">자료실</a></li>
                    </ul>
                </div>
                <div class="menu">
                    <strong>소프트웨어V&amp;V</strong>
                    <ul>
                        <li><a href="/user/V&V/V&V_Info.do">사업안내</a></li>
                        <li><a href="/user/V&V/V&V_Info_Eng.do">Information</a></li>
                        <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000041&menuCl=VV">자료실</a></li>
                    </ul>
                </div>
                <div class="menu">
                    <strong>기술이전/교육/컨설팅</strong>
                    <ul>
                        <li><a href="/user/Technology/Tech_Info.do">안내</a></li>
                        <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000041&menuCl=TCNTRN">자료실</a></li>
                    </ul>
                </div>
                <div class="menu">
                    <strong>IT&amp;SW 교육</strong>
                    <ul>
                        <li><a href="/user/IT&SW/IT&SW_Info.do">사업안내</a></li>
                        <li><a href="/user/IT&SW/swCurriculumList.do">교육과정</a></li>
                        <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000041&menuCl=ITEDC">자료실</a></li>
                    </ul>
                </div>
                <div class="menu">
                    <strong>소프트웨어품질포럼</strong>
                    <ul>
                    	<li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000012&menuCl=SWFOR">공지사항</a></li>
                        <li><a href="/user/SWforum/SWforumJoinList.do">포럼 참여기관정보</a></li>
                        <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000031">KOLAS 정보</a></li>
                        <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000021&menuCl=25023">기술지원</a></li>
                        <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000051">자유게시판</a></li>
                        <li><a href="/user/bbs/selectArticleList.do?bbsId=BBSMSTR_000000000041&menuCl=SWFOR">자료실</a></li>
                    </ul>
                </div>
                <a href="javascript:;" class="close"><img src="/images/user/common/close.png" alt="메뉴닫기"></a>
            </div>
        </div>
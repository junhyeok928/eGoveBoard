<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<div class="poplayer" id="layer1" style="display: none">
	<div class="top">dlwlrma@ktl.re.kr<a href="javascript:;" onclick="close_layer('layer1');" class="close">X</a></div>
             <p>요청하신 이메일 주소는 다음과 같습니다.</p>
             <span id="emad"></span>
             <div class="exit"><button>닫기</button></div>
</div>
<div class="footer-wrap">
     <footer id="footer">
         <div class="footer-top">
             <div class="inner">
                 <ul>
<!--                      <li class="blue"><a href="javascript:;">개인정보처리방침</a></li> -->
<!--                      <li><a href="javascript:;">이메일무단수집거부</a></li> -->
                     <li><a href="/user/Infomation/Info_location.do">찾아오시는 길</a></li>
                 </ul>
             </div>
         </div>
         <div class="footer-con">
             <div class="inner">
                 <div class="relate-site">
                     <a href="https://www.ktl.re.kr/main.do" target="_blank" class="cur">KTL 한국산업기술시험원 홈페이지 <img src="/images/user/common/direct.png" alt="관련사이트보기"></a>
                 </div>
                 <div class="footer-logo"><img src="/images/user/common/footer-logo.png" alt="KTL 한국산업기술시험원 소프트웨어기술센터"></div>
                 <aliress>
                     <ul id="footerCenterData">
                     <!-- <li>한국산업기술시험원 소프트웨어기술센터 Tel. 02-860-1566</li>
                         <li>[ 서울 ] (08389) 서울특별시 구로구 디지털로26길 87(구로동)</li>
                         <li>[ 대전 ] (34025) 대전광역시 유성구 테크노2로 199 (용산동) 미건테크노월드 1차 313호</li>
                         <li>Copyright 2021 &copy; Korea Testing Laboratory All Right Reserved.</li> -->
                     </ul>
                 </aliress>
             </div>
         </div>
     </footer>
 </div>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>
body {
	text-align: center;
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous"
>
</head>
<body>
	<div style="justify-content: center; display: flex;">
		<div style="width: 22%; min-width: 333px;">
			<div>
				<h2>마이페이지</h2>
			</div>
			<form name="form">
				아이디
				<input type="text" value="${userId.get('userid')}" name="userid" readonly class="form-control">
				<br /> 패스워드
				<input type="text" value="${userId.get('userpassword')}" name="userpassword" class="form-control">
				<br /> 가입일자
				<input type="date" value="${userId.get('userregdate')}" name="userregdate" class="form-control">
				<br /> 포인트
				<input type="number" value="${userId.get('userpoint')}" name="userpoint" class="form-control">
				<br />
				<input type="submit" value="수정" onclick="javascript: form.action='/user/main/updateInfo.do'" class="form-control">
				<input type="submit" value="삭제" onclick="javascript: form.action='/user/main/deleteInfo.do'" class="form-control">
				<br />
				<input type="submit" value="로그아웃" onclick="javascript: form.action='/user/main/logout.do'" class="form-control">
			</form>
		</div>
	</div>
</body>
</html>
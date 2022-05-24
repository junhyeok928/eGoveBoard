<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous"
>
<style>
body {
	text-align: center;
}
</style>
</head>
<body>
	<div style="justify-content: center; display: flex;">
		<div style="width: 22%; min-width: 333px;">
			<div>
				<h2>회원가입</h2>
			</div>
			<form action="/user/main/RegAction.do">
				<input type="text" name="userId" placeholder="아이디 입력" class="form-control">
				<br />
				<input type="password" name="userPassword" placeholder="비밀번호 입력" class="form-control">
				<br />
				<input type="submit" value="회원가입" class="form-control">
			</form>
		</div>
	</div>
</body>
</html>
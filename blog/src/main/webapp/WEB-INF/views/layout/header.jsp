<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Bowling Community</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

</head>

<style>
html, body {
	height: 100%;
	padding: 0px;
	margin: 0px;
}

#wrap {
	min-height: calc(100% - 120px);
}

footer {
	background-color: #e9eaed;
	margin: 0;
}

.jumbotron {
	padding: 32px;
}
</style>
<body>

	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/">๐ณHome</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">

			<c:choose>
				<c:when test="${ empty principal }">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="/auth/loginForm">๋ก๊ทธ์ธ</a></li>
						<li class="nav-item"><a class="nav-link" href="/auth/joinForm">ํ์๊ฐ์</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="/board/saveForm">๊ธ์ฐ๊ธฐ</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/updateForm">ํ์์?๋ณด</a></li>			
						<c:if test="${ principal.user.username eq 'admin'}">
							<li class="nav-item"><a class="nav-link" href="/user/userList">ํ์๋ชฉ๋ก</a></li>
						</c:if>
						<li class="nav-item"><a class="nav-link" href="/logout">๋ก๊ทธ์์</a></li>
					</ul>
				</c:otherwise>

			</c:choose>

			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="/galleryBoard/galleryMain">๊ฐค๋ฌ๋ฆฌ</a></li>
				<li class="nav-item"><a class="nav-link" href="/joinBoard/joinMain">์กฐ์ธ๊ฒ์ํ</a></li>
				<li class="nav-item"><a class="nav-link" href="/freeBoard/freeMain">์์?๊ฒ์ํ</a></li>
			</ul>

		</div>
	</nav>
	<br>
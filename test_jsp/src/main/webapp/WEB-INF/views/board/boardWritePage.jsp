<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardWritePage</title>
	<style>
		table, th, td {
			border: 1px solid #ddd;
		}

		table {
			width:600px;
			border-collapse: collapse;
		}

		th, td {
			padding: 8px;
			text-align: center;
			border-bottom: 1px solid #ddd;
		}

		th {
			background-color: #f2f2f2;
		}
	</style>
</head>
<body>

<c:import url="/menubar" />
<hr>

	<h2>board : 게시글 등록 페이지</h2>
	<form action="/boards" method="post">
		<table>
			<tr><td style="width:100px;">글제목</td>
				<td style="text-align:left;"><input type="text" name="boardTitle" size="50"></td>
			</tr>
			<tr><td>작성자</td>
				<td style="text-align:left;"><input type="text" name="boardWriter" size="15"></td>
			</tr>
			<tr><td colspan="2">
				<textarea name="boardContent" cols="70" rows="5"></textarea></td>
			</tr>
			<tr><td colspan="2">
				<input type="submit" value="등록">
				<input type="reset" value="작성내용 지우기"></td>
			</tr>
		</table>
	</form>

<hr style="clear:both;">
<c:import url="/footer" />

</body>
</html>
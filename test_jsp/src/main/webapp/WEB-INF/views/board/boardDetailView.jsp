<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detailView</title>
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
<script type="text/javascript" src="resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
$(function(){
	// 삭제
	$('#deleteBtn').on('click', function(){

		var rlt = confirm('삭제하시겠습니까?');

		if(rlt) {
			var bno = '${ board.boardNum}';

			$.ajax({
				type: "DELETE",
				url: "/boards/" + bno,
				contentType: "application/json; charset=UTF-8",
				success: function(){
					alert("삭제 처리되었습다.");
					location.href = "/board";
				},
				error: function(xhr, status, error){
					alert("code : " + xhr.status + "\n"
							+ "message : " + xhr.responseText + "\n"
							+ "error : " + error);
				}
			});
		} else {
			return;
		}
	});

	// 수정 Form
	$('#modifyBtn').on('click', function(){
		location.href = "/boards/${ board.boardNum}";
	});
});
</script>
</head>
<body>

<c:import url="/menubar" />
<hr>

	<h2>${ board.boardNum }번 게시글 내용 보기</h2>
	<table>
		<tr><td style="width:100px;">글제목</td>
			<td style="text-align:left;">${ board.boardTitle }</td>
		</tr>
		<tr><td>작성자</td>
			<td style="text-align:left;">${ board.boardWriter }</td>
		</tr>
		<tr><td style="text-align:left;" colspan="2">${ board.boardContent }</td>
		</tr>
		<tr><td colspan="2">
			<button id="modifyBtn">수정하기</button>
			<button id="deleteBtn">삭제하기</button>
			<button onclick="javascript:location.href='/board';">목록</button></td>
		</tr>
	</table>

<hr style="clear:both;">
<c:import url="/footer" />

</body>
</html>
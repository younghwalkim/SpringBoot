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
<script type="text/javascript" src="/resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
$(function(){
	// 수정
	$('#modifyBtn').on('click', function(){
		var boardObject = {
				boardNum: ${ board.boardNum },
				boardTitle: $('input[name=boardTitle]').val(),
				boardContent: $('textarea').text()
		}
		
		var bno = '${ board.boardNum }';
		
		$.ajax({
			type: "PUT",
			url: "/boards/" + bno,
			data: JSON.stringify(boardObject),
			contentType: "application/json; charset=UTF-8",
			success: function(){
				alert("수정완료");
				location.href = "/board";
			},
			error: function(xhr, status, error){
				alert("code : " + xhr.status + "\n"
						+ "message : " + xhr.responseText + "\n"
						+ "error : " + error);
			}
		});
	});
});
</script>
</head>
<body>

<c:import url="/menubar" />
<hr>

	<h2>${ board.boardNum } 번 게시글 내용 보기 및 수정 페이지</h2>
	  <form> <!-- form 의 전송방식은 GET | POST 만 가능함 -->        
		<input type="hidden" name="boardNum" value="${ board.boardNum }">
		<table>
			<tr><td style="width:100px;">글제목</td>
				<td style="text-align:left;"><input type="text" name="boardTitle" size="50" value="${ board.boardTitle }"></td>
			</tr>
			<tr><td>작성자</td>
				<td style="text-align:left;"><input type="text" name="boardWriter" size="15" readonly value="${ board.boardWriter }"></td>
			</tr>
			<tr><td colspan="2">
				<textarea name="boardContent" cols="70" rows="5">${ board.boardContent }</textarea></td>
			</tr>
			<tr><td colspan="2">
				<input type="button" id="modifyBtn" value="게시글수정">
				<input type="reset" value="작성내용 지우기"></td>
			</tr>
		</table>
	</form>


<hr style="clear:both;">
<c:import url="/footer" />

</body>
</html>
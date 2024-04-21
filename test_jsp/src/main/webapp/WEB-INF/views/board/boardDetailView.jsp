<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detailView</title>
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

	<h2>${ board.boardNum } 번 게시글 내용 보기 및 수정 페이지</h2>
		글제목 : ${ board.boardTitle } <br>
		작성자 : ${ board.boardWriter } <br>
		내 용 :  ${ board.boardContent } <br>
	<br>
	<button id="modifyBtn">수정하기</button>
	<button id="deleteBtn">삭제하기</button>



<hr style="clear:both;">
<c:import url="/footer" />

</body>
</html>
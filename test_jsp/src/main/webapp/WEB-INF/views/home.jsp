<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test_jsp</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
$(function(){
	//조회수 많은 인기 게시글 상위 3개 조회 출력 처리
	$.ajax({
		url: "btop3",
		type: "get",
		dataType: "json",
		success: function(data){

			console.log("success : " + data);

			//object --> string
			var str = JSON.stringify(data);

			//string --> json
			var json = JSON.parse(str);

			values = "";
			for(var i in json.list){
				values += "<tr><td>" + json.list[i].bnum
						+ "</td><td><a href='/boards?bno="
						+ json.list[i].bnum + "'>"
						+ decodeURIComponent(json.list[i].btitle).replace(/\+/gi, " ")
						+ "</a></td><td>"
						+ json.list[i].rcount + "</td></tr>";
			}

			$('#toplist').html($('#toplist').html() + values);
			//$('#toplist').append(values);
		},
		error: function(jqXHR, textStatus, errorThrown){
			console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
		}
	});  //ajax

});  //document ready

</script>

</head>
<body>

<c:import url="/menubar" />
<hr>

	<h1>Test Spring Boot with JSP</h1>
	<div>
		<button onclick="javascript:location.href='${ pageContext.servletContext.contextPath }/movepage';">게시글 등록 페이지로 이동</button>
	</div>
	<h2>${ name }</h2>

	<%-- 조회수 많은 인기게시글 3개 출력 : ajax --%>
	<div style="float:left;border:1px solid navy;padding:5px;margin:5px;margin-left:50px;">
		<h4>인기 게시글</h4>
		<table id="toplist" border="1" cellspacing="0" width="350">
			<tr><th>번호</th><th>제목</th><th>조회수</th></tr>
		</table>
	</div>

<hr style="clear:both;">
<c:import url="/footer" />

</body>
</html>
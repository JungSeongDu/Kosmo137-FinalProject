<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	alert("자바스크립트 진입 >>> : ");
	
	$(document).ready(function(){
		alert("jQuery ready() 함수 블럭 진입 >>> : ");
		
		//아이디 중복 체크
		$(document).on("click","#idCheck",function(){
			alert("idCheck >>> : ");
			
			let idCheckURL = "idCheck.h";
			let reqType = "POST";
			let dataParam = { mid: $("#mid").val(), };
			
			$.ajax({
				url: idCheckURL,
				type: reqType,
				data: dataParam,
				success: whenSuccess,
				error: whenError
			});
			
			function whenSuccess(resData){
				alert("resData >>> : " + resData);
				if("ID_YES" == resData){
					alert("이 아이디는 사용 가능한 아이디 입니다" );
					
					$("#mid").css('background-color','yellow');
					$("#mid").attr("readonly",true);
					$("#mpw").focus();		
			}else if ("ID_NO" == resData){
				alert("이 아이디는 사용중인 아이디 입니다");
				$("#mid").val('');
				$("#mid").focus();
			};
		}
			function whenError(e){
				alert("e >>> : " + e.responseText);
			}
		});
		
		//비밀번호 체크
		$("#pwCheck").click(function(){
			console.log("pwCheck 함수 진입");
			alert("pwCheck 함수 진입");
			
			var pw = $("#mpw").val();
			var pw_r = $("#mpw_r").val();
			if(pw == pw_r){
				alert("비밀번호가 같습니다.");
				$("#mpw_r").val('');
				$("#mpw").css('background-color','yellow');
				$("#mpw_r").css('background-color','gray');
				$("#mpw").attr("readonly",true);
				$("#mpw_r").attr("readonly",true);
				$("#mname").focus();
				return true;
			} else {
				alert("비밀번호가 다릅니다. 다시 입력 해주세요");
				$("#mpw_r").val('');
				$("#mpw_r").focus();
				return false;
			}
		});
		
		//회원가입 버튼
		$(document).on("click", "#inBtn", function(){ 
				alert("inBtn 버튼 블럭 진입 >>> :");
				console.log("inBtn 버튼 블럭 진입 >>> :");
				
			$('#fpMemInsert').attr({
				'action':'fpMemInsert.h',
				'method':'GET',
				'enctype':'multipart/form-data'
			}).submit(); 
		});
	});
</script>
</head>
<body>
<form name="fpMemInsert" id="fpMemInsert">
<table border="1">
	<tr>
		<td colspan="2"><h3>회원가입</h3></td>
	</tr>
	<tr>
		<td>아이디</td>
		<td><input type="text" name="mid" id="mid" size="20">
		<input type="button" id="idCheck" value="중복확인"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="text" name="mpw" id="mpw" size="20"></td>
	</tr>
	<tr>
		<td>비밀번호확인</td>
		<td><input type="text" name="mpw_r" id="mpw_r" size="20">
		<input type="button" id="pwCheck" value="비밀번호확인"></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" name="mname" id="mname" size="20"></td>
	</tr>
	<tr>
		<td colspan="2">							
		<input type="button" value="회원가입" id="inBtn">
		<input type="button" value="취소" id="">
		</td>
	</tr>
</table>
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import = "com.kos.finalproject.fp.vo.FpMemVO" %>
<%@ page import = "java.util.List" %>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/osProject/include/css/Mypageform.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	alert("자바스크립트 진입 >>> : ");
	
	$(document).ready(function(){
		alert("jQuery ready() 함수 블럭 진입 >>> : ");
		
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
		
		//수정하기 버튼
		$(document).on("click", "#upBtn", function(){ 
				alert("upBtn 버튼 블럭 진입 >>> :");
				console.log("upBtn 버튼 블럭 진입 >>> :");
				
			$('#fpmemSelect').attr({
				'action':'memUpdate.h',
				'method':'GET',
				'enctype':'multipart/form-data'
			}).submit(); 
			
		
		});
		
		//회원탈퇴
		$(document).on("click", "#dlBtn", function(){ 
			alert("dlBtn 버튼 블럭 진입 >>> :");
			console.log("upBtn 버튼 블럭 진입 >>> :");
			
		$('#fpmemSelect').attr({
			'action':'memDelete.h',
			'method':'GET',
			'enctype':'multipart/form-data'
		}).submit(); 
		
		
	});
	});
</script>
<title>수정 페이지</title>
</head>
<body>
    <form name="fpmemSelect" id="fpmemSelect">
        <div class="form-table">
            
            <div class="mpmdf">
                <div>
                    <h2>내 정보 수정</h2>
                </div>
                <div>
                    <label for="mid">아이디</label>
                    <input type="text" name="mid" id="mid" size="20" >
                    
                </div>
                <div>
                    <label for="mpw">비밀번호</label>
                    <input type="text" name="mpw" id="mpw" size="20" >
                </div>
                <div>
                    <label for="mpw_r">비밀번호확인</label>
                    <input type="text" name="mpw_r" id="mpw_r" size="20">
                    <input type="button" id="pwCheck" value="비밀번호확인">
                </div>
                <div>
                    <label for="mname">이름</label>
                    <input type="text" name="mname" id="mname" size="20" >
                </div>
        
                <div>
                    <input type="button" value="수정하기" id="upBtn">
                    <input type="button" value="회원탈퇴" id="dlBtn">
                    
                </div>
            </div>
           
        </div>
    </form>
</body>
</html>


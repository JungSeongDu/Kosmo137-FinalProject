<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인테리어 컨셉 추천</title>
<style>
    body {
        font-family: 'Arial', sans-serif;
        color: #ffffff;
        background-color: #2a3d66;
        background: linear-gradient(45deg, #2a3d66, #4a688c);
        background-size: 400% 400%;
        animation: gradientBG 15s ease infinite;
    }

    @keyframes gradientBG {
        0% {background-position: 0% 50%;}
        50% {background-position: 100% 50%;}
        100% {background-position: 0% 50%;}
    }

    .container {
        max-width: 800px;
        margin: 20px auto;
        padding: 20px;
        background-color: #3b4f77;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.2);
    }

    h2 {
        color: #aad1e6;
        text-align: center;
    }

    p {
        color: #d0e2f2;
        line-height: 1.6;
        margin-bottom: 20px;
    }

    img {
        display: block;
        max-width: 100%;
        height: auto;
        margin: 0 auto; /* 이미지를 가운데 정렬 */
    }
</style>
</head>
<body>
<div class="container">
    <h2>자신의 방 인테리어를 추천받아보세요</h2>
    <p>1인 가구의 증가와 함께 개인 맞춤형 공간의 중요성이 더욱 부각됩니다. 우리의 AI 기반 웹사이트는 여러분의 방 사진을 분석하여 각 공간에 가장 적합한 인테리어를 제안해 드립니다. 여러분이 꿈꾸는 공간을 실현할 수 있도록 도와드립니다. 자신만의 특별한 공간을 만들어보세요.</p>
    <h2>인테리어의 컨셉에 대해 알아보세요</h2>
    <p>우리의 웹사이트에서는 AI를 활용해 다양한 인테리어 컨셉을 제공합니다. 여러분의 방의 구조 그리고 공간의 특성을 고려하여 인테리어 아이디어를 제시해 드립니다. 모던 인테리어부터 빈티지 , 유니크 스타일까지! 여러분이 원하는 분위기를 창출할 수 있는 컨셉을 찾아보세요. 여러분의 공간을 새롭게 탈바꿈시킬 인테리어의 영감을 여기서 얻어가세요.</p>
    <!-- 이미지 추가 -->
    <img src="resources/include/img/introduce.PNG" alt="인테리어 소개">
</div>
	<a href="fpLoginForm">로그인 폼</a>
</body>
</html>

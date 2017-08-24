<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>회원가입 html5</title>
	
	<script type="text/javascript">
	
		/*
			1. loginCheck 함수를 아이디, 비밀번호를 넣어서 실행 
			2. loginCheck 함수에서, 로그인이 널인지, 자릿수는 맞는지 체크
			3. 비밀번호가 널인지, 자릿수는 맞는지 체크
			4. 
			5. 아이디가 올바르지 않으면 경고창 출력 후, 해당 입력양식에 포커스
			
			6. 숫자는 자릿수 비교를 하기가 어렵다.
			
		*/
	
		/* 로그인 입력 */
		function 
	
	</script>
	
	<style>
	a{
		text-decoration: none;
	}
	</style>
	
	</head>
	<body>
	
	<!-- container -->
	<div style="max-width: 1887px; max-height: 885px; text-align: center;">
	
		<!-- header start-->
		<div style="padding:10px auto; width: 100%;">
			<div style="float:left; margin:0 auto; display:inline-block; background-color: #40B4E5; width: 100%; height: 60px;">
				<a href="#">
					<div style="display:inline-block; width: 300px; height : 60px; background-size:100% 60px; background-image:url('images/Korea_Baseball_Organization.png');"></div>
				</a>
				<ul style="float:right; margin:20px; overflow:hidden; list-style: none;">
					<li style="width:110px; float:left;"><a href="#">로그인</a></li>
					<li style="width:110px; float:left;"><a href="#">회원가입</a></li>
				</ul>
			</div>
		</div>
		<!-- header end -->
	
		<!-- nav start-->
		<div style="background-color: green; clear:both; padding: 10px 15%; text-align: center;">
			<ul style="display:block; overflow : hidden;">
				<li style="background-color: white; width: 110px; float:left; list-style: none;">카테고리1</li>
				<li style="background-color: gray;; width: 110px; float:left; list-style: none;">카테고리2</li>
				<li style="background-color: white; width: 110px; float:left; list-style: none;">카테고리3</li>
				<li style="background-color: gray; width: 110px; float:left; list-style: none;">카테고리4</li>
				<li style="background-color: white; width: 110px; float:left; list-style: none;">카테고리5</li>
				<li style="background-color: gray; width: 110px; float:left; list-style: none;">카테고리6</li>
				<li style="background-color: white; width: 110px; float:left; list-style: none;">카테고리7</li>
				<li style="background-color: gray; width: 110px; float:left; list-style: none;">카테고리6</li>
				<li style="background-color: white; width: 110px; float:left; list-style: none;">카테고리7</li>
				<li style="background-color: gray; width: 110px; float:left; list-style: none;">카테고리6</li>
				<li style="background-color: white; width: 110px; float:left; list-style: none;">카테고리7</li>
			</ul>
		</div>
		<!-- nav end -->
	
		<!-- left -->
		<div></div>
	
		<!-- contents start-->
<!-- 		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.mobile = mobile;
		this.email = email;	 -->	
		
		<table border="1" style="margin:0 auto; margin-top:200px;">
			<tr height="50px">
				<td colspan="2" width="337px" align="center" background="1px"><img src="images/title.png"></td>
				<td width="337px" align="center"><img src="images/title.png"></td>
			</tr>
			<tr height="">
				<form action="#" method="post">
					<table style="margin:0 auto;">
						<tr>
							<td width="220px;" align="center"><font size="6">아이디</font></td>
							<td><input type="text" name="memberId" size="38" tabindex="1"/></td>
							<td rowspan="2" align="center">&nbsp;&nbsp;&nbsp;
								<button type="button" style="height:55px; width: 150px;" tabindex="3"><font size="6">로그인</font></button>
							</td>
						</tr>
						<tr>
							<td height="30px" align="center"><font size="6">비밀번호</font></td>
							<td><input type="password" size="38" tabindex="2"/></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<button type="button">학번(아이디)/비밀번호 찾기</button>
								<button type="button">비밀번호 변경</button>
							</td>
							<td></td>
						</tr>
						<tr height="30px">
							<td></td>
							<td align="center">
								<input type="radio"/>Korea
								&nbsp;&nbsp;&nbsp;
								<input type="radio"/>English
							</td>
							<td></td>
							
						</tr>
					</table>
				</form>
			</tr>
			
			<tr>
				<form action="#" method="post">
					<table style="margin:0 auto;">
						<tr>
							<td width="220px;" align="center"><font size="6">주민등록번호</font></td>
							<td>
								<input type="text" name="jumin1" size="16"/>
								<input type="text" name="jumin2" size="16"/>
							</td>
							<td rowspan="2">&nbsp;&nbsp;&nbsp;
								<button type="button" style="width: 150px;"><font size="6">로그인</font></button>
							</td>
						</tr>
					</table>
				</form>
			</tr>
			
			<h4 align="center"><font size="5" color="#7FA747">로그인시 유의사항</font></h4>
			<ul style="list-style-type : none; margin-bottom :200px;">
				<li>원하는 서비스를 이용하신 후 자리를 비우게 될 경우에는 반드시 로그아웃 하시기 바랍니다.</li>
				<li>비밀번호는 주기적으로 변경 관리하시고 타인에게 노출되지 않도록 주의하시기 바랍니다/</li>
			</ul>
		</table>
		<!-- contents end -->
	
		<!-- footer start-->
		<div style="max-height: 400px; padding: 20px; background-color: black;">
			<div style="max-width: 100%; margin:0 auto; text-align: center; padding: 0 20%;">
				<ul style="display:block; overflow: hidden; list-style:none; color: white;">
					<li style="float:left; width: 110px;">약괸</li>
					<li style="float:left; width: 110px;">약괸</li>
					<li style="float:left; width: 110px;">약괸</li>
					<li style="float:left; width: 110px;">약괸</li>
					<li style="float:left; width: 110px;">약괸</li>
					<li style="float:left; width: 110px;">약괸</li>
					<li style="float:left; width: 110px;">약괸</li>
					<li style="float:left; width: 110px;">약괸</li>
					<li style="float:left; width: 110px;">약괸</li>
				</ul>
				<div></div>
				<div></div>
			</div>
		</div>
		<!-- footer end -->
		
	</div>
	<!-- container end -->
	
	</body>
</html>
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
	<div style="max-width: 1887px; max-height: 885px;">
	
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
		
		<form id="ss"  action="addMember.do" method="POST" style="margin:150px auto;">
		<div style="width: 100%; margin-top: 100px;">
			<div style="display:table; width:100%; height:100%;">
				<div style="display:table-cell; vertical-align:middle; text-align:center;">
					
					<div style="margin:0 auto; margin-bottom:20px; height:110px; background-image:url('images/Korea_Baseball_Organization.png'); width: 30%; background-size:611px 110px;" class="col-md-12 col-md-offset-s1">
					</div>
					
					<div style="margin-bottom: 10px;" class="">
						<input name="memberId" type="text" title="아이디" placeholder="아이디를 입력하세요" style="width:30%; padding: 20px 20px;"/>
						<input type="text" style="display: none"/>
					</div>
					<div style="margin-bottom: 10px;" class="col-md-12 col-md-offset-s1">
						<input name="memberPw" type="text" title="비밀번호" placeholder="비밀번호를 입력하세요" style="width:30%; padding: 20px 20px;"/>
						<input type="text" style="display: none"/>
					</div>
					<div style="margin-bottom: 10px;" class="col-md-12 col-md-offset-s1">
						<input name="memberName" type="text" title="이름" placeholder="이름을 입력하세요(숫자만 입력하세요.)" style="width:30%; padding: 20px 20px;"/>
						<input type="text" style="display: none"/>
					</div>
					<div style="margin-bottom: 10px;" class="col-md-12 col-md-offset-s1">
						<input name="mobile" type="text" title="휴대폰" placeholder="휴대폰번호를 입력하세요(숫자만 입력하세요.)" style="width:30%; padding: 20px 20px;"/>
						<input type="text" style="display: none"/>
					</div>
					<div style="margin-bottom: 10px;" class="col-md-12 col-md-offset-s1">
						<input name="email" type="text" title="이메일" placeholder="이메일을 입력하세요 ex) 1994년 1월 1일 => 19940101" style="width:30%; padding: 20px 20px;"/>
						<input type="text" style="display: none"/>
					</div>
					<div style="margin-bottom: 10px;" class="col-md-12 col-md-offset-s1">
						<button style="width: 32.4%; height : 50px; background-color: #2196F3; border: none;" type="submit" class="btn btn-fill btn-neutral btn-wd" onclick="javascript:fnSbscrb()">버튼</button>
					</div>
				</div>
			</div>
		</div>
		</form>
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
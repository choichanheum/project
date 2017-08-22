/* 회원테이블 CRUD 파일 : members_dml.sql 
	회원가입
	아이디중복
	로그인
	로그아웃
	내정보조회
	내정보변경
	비밀번호변경
	회원탈퇴
	아이디찾기
	비밀번호찾기
	마일리지 적립
	회원등급변경
	-- 우수회원 등업
	-- 일반회원
	-- 관리자
	전체회원조회
	다중조건검색 조회
	-- 등급별
	-- 아이디
	-- 휴대폰번호뒷4자리
	-- 이름
	-- 기타
	회원상세조회
	회원정보변경
	회원탈퇴
	회원등록
	회원로그 history 조회
*/

/*
user01	password01	홍길동	010-1234-1111	user01@work.com	2017.05.05	G	7500	
user02	password02	강감찬	010-1234-1112	user02@work.com	2017.05.06	G	9500	
user03	password03	이순신	010-1234-1113	user03@work.com	2017.05.07	G	3000	
user04	password04	김유신	010-1234-1114	user04@work.com	2017.05.08	S		송중기
user05	password05	유관순	010-1234-1115	user05@work.com	2017.05.09	A		
*/

-- 회원초기화데이터 등록
insert into members
values('user01','password01','홍길동','010-1234-1111','user01@work.com','2017.05.05','G',7500,null);

insert into members
values('user02','password02','강감찬','010-1234-1112','user02@work.com','2017.05.06','G',9500,null);

insert into members
values('user03','password03','이순신','010-1234-1113','user03@work.com','2017.05.07','G',3000,null);

insert into members
values('user04','password04','김유신','010-1234-1114','user04@work.com','2017.05.08','S',null,'송중기');

insert into members
values('user05','password05','유관순','010-1234-1115','user05@work.com','2017.05.09','A',null,null);
commit;

-- 회원등록
-- jdbc dao : insert into members values(?,?,?,?,?,?,?,?,?)

-- 아이디 중복 조회
select member_name from members where member_id='user01';
--select member_name from members where member_id=?

-- 로그인
select grade from members where member_id='user01' and member_pw='password01';

-- 내정보조회
select * from members where member_id='user01';

-- 내정보변경
update members set member_pw='변경암호', member_name='변경이름', mobile='변경연락처', email='변경이메일'
where member_id='user01';

-- update members set member_pw=?, member_name=?, mobile=?, email=?
-- where member_id=?

-- 암호변경
update members set member_pw='변경암호'
where member_id='user01' and member_pw='password01';

-- update members set member_pw=?
-- where member_id=? and member_pw=?


-- 회원상세조회
select * from members where member_id='user01';
-- select * from members where member_id=?

-- 전체회원조회
select * from members;

-- 등급별 전체회원조회
select * from members where grade='G';
-- select * from members where grade=?

-- 회원탈퇴
delete from members where member_id='user01';
-- delete from members where member_id=?

-- 전체회원 삭제
delete from members;

-- 아이디찾기 : 이름 연락처 / 이름 이메일
select member_id from members
where member_name='강감찬' and mobile='010-1234-1111';

select member_id from members
where member_name='강감찬' and email='user01@work.com';

-- select member_id from members
-- where member_name=? and mobile=?

-- select member_id from members
-- where member_name=? and email=?


-- 비밀번호찾기 : 아이디 이름 연락처 / 아이디 이름 이메일
select member_pw from members
where member_id='user01' and member_name='강감찬' and mobile='010-1234-1111';

select member_pw from members
where member_id='user01' and member_name='강감찬' and mobile='010-1234-1111';

--select member_pw from members
--where member_id=? and member_name=? and mobile=?
--
--select member_pw from members
--where member_id=? and member_name=? and mobile=?











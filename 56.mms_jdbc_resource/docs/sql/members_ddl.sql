/* 회원테이블 스키마 파일 : members_ddl.sql 
 * 
 * ## 회원 property(멤버변수)
 * 	1.아이디 문자열 : memberId 
 * 		=> member_id varchar2(20) primary key
 * 	2.비밀번호 문자열 : memberPw
 * 		=> member_pw varchar2(20) not null
 * 	3.이름 문자열 : memberName
 * 		=> member_name varchar2(20)  not null
 * 	4.연락처 문자열(기본형식: 010-1234-1234) : mobile
 * 		=> mobile varchar2(13)  not null
 * 	5.이메일 문자열 : email
 * 		=> email varchar2(30)  not null
 * 	6.가입일 문자열(기본형식: 년도4자리.월2자리.일2자리 2017.08.08) : entryDate
 * 		=> entry_date varchar2(10)  not null
 * 	7.등급 문자열(회원종류 : 일반(G), 우수(S), 관리자(A)) : grade
 * 		=> grade varchar2(1)  not null
 * 	8.마일리지 숫자 : 일반회원 : mileage
 * 		=> mileage number(6) 
 * 	9.담당자 문자열 : 우수회원 : manager
 * 		=> manager varchar2(20) 
 * */

-- 테이블 삭제
drop table members cascade constraint purge;

-- 테이블 생성
create table members (
	member_id varchar2(20),
	member_pw varchar2(20) not null,
	member_name varchar2(20) not null,
	mobile varchar2(13) not null,
	email varchar2(30) not null,
	entry_date varchar2(10) not null,
	grade varchar2(1) not null,
	mileage number(6),
	manager varchar2(20)
);

-- 제약 추가 : member_id 식별키
alter table members 
add(constraint pk_members_memberid primary key(member_id));

-- 제약 추가 : mobile 중복불가
alter table members 
add(constraint uk_members_mobile unique(mobile));

-- 제약 추가 : email 중복불가
alter table members 
add(constraint uk_members_email unique(email));

-- 제약관련 data dictionary
-- user_constraints, user_cons_columns

-- 테이블 주석 추가
comment on table members is 'members table';

-- 컬럼 주석 추가
comment on column members.member_id is 'member id min 6, max 20';
comment on column members.member_pw is 'member id min 8, max 20';
comment on column members.grade is 'general(G), special(S), admin(A)';

-- 주석관련 data dictionary
-- user_tab_comments
-- user_col_comments
select table_name, comments from user_tab_comments;

select column_name, comments from user_col_comments
where table_name in ('MEMBERS');






/* ȸ�����̺� ��Ű�� ���� : members_ddl.sql 
 * 
 * ## ȸ�� property(�������)
 * 	1.���̵� ���ڿ� : memberId 
 * 		=> member_id varchar2(20) primary key
 * 	2.��й�ȣ ���ڿ� : memberPw
 * 		=> member_pw varchar2(20) not null
 * 	3.�̸� ���ڿ� : memberName
 * 		=> member_name varchar2(20)  not null
 * 	4.����ó ���ڿ�(�⺻����: 010-1234-1234) : mobile
 * 		=> mobile varchar2(13)  not null
 * 	5.�̸��� ���ڿ� : email
 * 		=> email varchar2(30)  not null
 * 	6.������ ���ڿ�(�⺻����: �⵵4�ڸ�.��2�ڸ�.��2�ڸ� 2017.08.08) : entryDate
 * 		=> entry_date varchar2(10)  not null
 * 	7.��� ���ڿ�(ȸ������ : �Ϲ�(G), ���(S), ������(A)) : grade
 * 		=> grade varchar2(1)  not null
 * 	8.���ϸ��� ���� : �Ϲ�ȸ�� : mileage
 * 		=> mileage number(6) 
 * 	9.����� ���ڿ� : ���ȸ�� : manager
 * 		=> manager varchar2(20) 
 * */

-- ���̺� ����
drop table members cascade constraint purge;

-- ���̺� ����
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

-- ���� �߰� : member_id �ĺ�Ű
alter table members 
add(constraint pk_members_memberid primary key(member_id));

-- ���� �߰� : mobile �ߺ��Ұ�
alter table members 
add(constraint uk_members_mobile unique(mobile));

-- ���� �߰� : email �ߺ��Ұ�
alter table members 
add(constraint uk_members_email unique(email));

-- ������� data dictionary
-- user_constraints, user_cons_columns

-- ���̺� �ּ� �߰�
comment on table members is 'members table';

-- �÷� �ּ� �߰�
comment on column members.member_id is 'member id min 6, max 20';
comment on column members.member_pw is 'member id min 8, max 20';
comment on column members.grade is 'general(G), special(S), admin(A)';

-- �ּ����� data dictionary
-- user_tab_comments
-- user_col_comments
select table_name, comments from user_tab_comments;

select column_name, comments from user_col_comments
where table_name in ('MEMBERS');






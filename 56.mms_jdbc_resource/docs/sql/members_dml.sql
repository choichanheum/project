/* ȸ�����̺� CRUD ���� : members_dml.sql 
	ȸ������
	���̵��ߺ�
	�α���
	�α׾ƿ�
	��������ȸ
	����������
	��й�ȣ����
	ȸ��Ż��
	���̵�ã��
	��й�ȣã��
	���ϸ��� ����
	ȸ����޺���
	-- ���ȸ�� ���
	-- �Ϲ�ȸ��
	-- ������
	��üȸ����ȸ
	�������ǰ˻� ��ȸ
	-- ��޺�
	-- ���̵�
	-- �޴�����ȣ��4�ڸ�
	-- �̸�
	-- ��Ÿ
	ȸ������ȸ
	ȸ����������
	ȸ��Ż��
	ȸ�����
	ȸ���α� history ��ȸ
*/

/*
user01	password01	ȫ�浿	010-1234-1111	user01@work.com	2017.05.05	G	7500	
user02	password02	������	010-1234-1112	user02@work.com	2017.05.06	G	9500	
user03	password03	�̼���	010-1234-1113	user03@work.com	2017.05.07	G	3000	
user04	password04	������	010-1234-1114	user04@work.com	2017.05.08	S		���߱�
user05	password05	������	010-1234-1115	user05@work.com	2017.05.09	A		
*/

-- ȸ���ʱ�ȭ������ ���
insert into members
values('user01','password01','ȫ�浿','010-1234-1111','user01@work.com','2017.05.05','G',7500,null);

insert into members
values('user02','password02','������','010-1234-1112','user02@work.com','2017.05.06','G',9500,null);

insert into members
values('user03','password03','�̼���','010-1234-1113','user03@work.com','2017.05.07','G',3000,null);

insert into members
values('user04','password04','������','010-1234-1114','user04@work.com','2017.05.08','S',null,'���߱�');

insert into members
values('user05','password05','������','010-1234-1115','user05@work.com','2017.05.09','A',null,null);
commit;

-- ȸ�����
-- jdbc dao : insert into members values(?,?,?,?,?,?,?,?,?)

-- ���̵� �ߺ� ��ȸ
select member_name from members where member_id='user01';
--select member_name from members where member_id=?

-- �α���
select grade from members where member_id='user01' and member_pw='password01';

-- ��������ȸ
select * from members where member_id='user01';

-- ����������
update members set member_pw='�����ȣ', member_name='�����̸�', mobile='���濬��ó', email='�����̸���'
where member_id='user01';

-- update members set member_pw=?, member_name=?, mobile=?, email=?
-- where member_id=?

-- ��ȣ����
update members set member_pw='�����ȣ'
where member_id='user01' and member_pw='password01';

-- update members set member_pw=?
-- where member_id=? and member_pw=?


-- ȸ������ȸ
select * from members where member_id='user01';
-- select * from members where member_id=?

-- ��üȸ����ȸ
select * from members;

-- ��޺� ��üȸ����ȸ
select * from members where grade='G';
-- select * from members where grade=?

-- ȸ��Ż��
delete from members where member_id='user01';
-- delete from members where member_id=?

-- ��üȸ�� ����
delete from members;

-- ���̵�ã�� : �̸� ����ó / �̸� �̸���
select member_id from members
where member_name='������' and mobile='010-1234-1111';

select member_id from members
where member_name='������' and email='user01@work.com';

-- select member_id from members
-- where member_name=? and mobile=?

-- select member_id from members
-- where member_name=? and email=?


-- ��й�ȣã�� : ���̵� �̸� ����ó / ���̵� �̸� �̸���
select member_pw from members
where member_id='user01' and member_name='������' and mobile='010-1234-1111';

select member_pw from members
where member_id='user01' and member_name='������' and mobile='010-1234-1111';

--select member_pw from members
--where member_id=? and member_name=? and mobile=?
--
--select member_pw from members
--where member_id=? and member_name=? and mobile=?











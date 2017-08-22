package work.view;

import java.util.ArrayList;

import work.controller.MemberController;
import work.model.dto.Member;

public class MemberTest {

	public static void main(String[] args) {
		// ������ �ʱ�ȭ ȸ�� ��ü ����
		System.out.println("aaa");
		Member dto1 = new Member("test01","password01","ȫ�浿","010-1111-1111","test01@work.com","2017.05.05","G",7500,null);
		Member dto2 = new Member("test02","password02","������","010-1111-1112","test02@work.com","2017.05.06","G",9500,null);
		Member dto3 = new Member("test03","password03","�̼���","010-1111-1113","test03@work.com","2017.05.07","G",3000,null);
		Member dto4 = new Member("test04","password04","������","010-1111-1114","test04@work.com","2017.05.08","S",0,"���߱�");
		Member dto5 = new Member("test05","password05","������","010-1111-1115","test05@work.com","2017.05.09","A",0,null);
		
		// ȸ������ ��û���ü ���� : controller 
		MemberController controller = new MemberController();
		
		// �׽�Ʈ�ÿ� ������� ���� ����
		ArrayList<String> message;
		Member dto = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String grade = null;
		String memberId = null;
		String memberPw = null;
		
		print("�׽�Ʈ�� ���� ȸ����ü���� �ʱ�ȭ");
		int rows = controller.removeAllMember();
		print("ȸ���� ����ó���Ǿ����ϴ�. : " + rows + "��");
		
		print("[������] ȸ�� ���");
		controller.addMemberByAdmin(dto1);

		print("[������] ���� ȸ�� ��ġ ����");
		list.add(dto2);
		list.add(dto3);
		list.add(dto4);
		list.add(dto5);
		controller.addListMemberByAdmin(list);
		
		// ȸ�� ���� : ����� ���� �Է�(�⺻ �Ϲ�ȸ��)
		Member dto6 = new Member("test20","password20","������","010-2222-1113","test20@work.com");
		Member dto7 = new Member("test21","password21","�����","010-2222-1114","test21@work.com");
		Member dto8 = new Member("test22","password22","������","010-2222-1115","test22@work.com");
		
		print("ȸ����� : �⺻ �Ϲ�ȸ��");
		print(controller.addMember(dto6));
		print(controller.addMember(dto7));
		print(controller.addMember(dto8));
		
		print("��üȸ����ȸ");
		list = controller.getAllMember();
		printAllMember(list);
		
		print("��޺� ȸ�� ��üȸ����ȸ : �Ϲ�ȸ��");
		list = controller.getAllMemberByGrade("g");
		printAllMember(list);
		
		print("[������] ȸ�� ���� : test03");
		dto3 = new Member("test03","goodluck03","�̼���","010-1111-1113","goodluck03@work.com","2017.05.07","S",0,"������");
		controller.setMember(dto3);
		
		print("��޺� ȸ�� ��üȸ����ȸ : ���ȸ��");
		list = controller.getAllMemberByGrade("S");
		printAllMember(list);

		print("��޺� ȸ�� ��üȸ����ȸ : ������");
		list = controller.getAllMemberByGrade("A");
		printAllMember(list);

		print("������ ��ȸ");
		dto = controller.getMyInfo("test01"); 
		printMember(dto);

		print("�α���");
		grade = controller.login("test01", "password01");
		print("[�α��ε�� : " + grade + "]");

		grade = controller.login("test04", "password04");
		print("[�α��ε�� : " + grade + "]");

		grade = controller.login("test05", "password05");
		print("[�α��ε�� : " + grade + "]");

		print("ȸ��Ż�� : test01, test02, test03, test20, test21");
		String[] removes = {"test01", "test02", "test03", "test20", "test21"};
		for (String removeMemberId: removes) {
			print(controller.removeMember(removeMemberId));
		}
		
		print("��üȸ����ȸ");
		printAllMember(controller.getAllMember());
		
		print("��ȣ ���� ��: ");
		printMember(controller.getMember("test22"));
		print(controller.setPassword("test22", "password22", "bluesky22"));
		
		print("��ȣ ���� ��: ");
		printMember(controller.getMember("test22"));
		
		print("������ ���� �� : test04");
		printMember(controller.getMember("test04"));
		
		dto = new Member("test04","happyday","�����","010-1111-2773","happyday04@korea.com");
		controller.setMyInfo(dto);
		print("������ ���� �� : test04");
		printMember(controller.getMember("test04"));
		
		// test04, happyday, �����, 010-1111-2773, happyday04@korea.com
		print("���̵� ã�� : �����");
		memberId = controller.findMemberIdByMobile("�����", "010-1111-2773");
		print("��������� ���̵��Դϴ�. : " + memberId);
		
		print("���̵� ã�� : �̸���");
		memberId = controller.findMemberIdByEmail("�����", "happyday04@korea.com");
		print("��������� ���̵��Դϴ�. : " + memberId);
		
		print("��й�ȣ ã�� : ");
		memberPw = controller.findMemberPw("test04", "�����", "mobile", "010-1111-2773");
		print("��������� �ӽù߱޾�ȣ�Դϴ�. : " + memberPw);
		
		memberPw = controller.findMemberPw("test04", "�����", "email", "happyday04@korea.com");
		print("��������� �ӽù߱޾�ȣ�Դϴ�. : " + memberPw);
		
		print("��й�ȣ ã�� �� ������ ��ȸ : test04");
		printMember(controller.getMember("test04"));
		
		print("��üȸ����ȸ");
		printAllMember(controller.getAllMember());

		print("�α׾ƿ�");
		controller.logout();
	}

	/**
	 * �׽�Ʈ�� ���� ��ü �������� �ƱԸ�Ʈ�� ���޹��� ���ڿ��� ���
	 * @param message �޼���
	 */
	public static void print(String message) {
		if (message != null) {
			System.out.println("\n### " + message + " ###");
			System.out.println("\n### " + message + " ###");
		}
	}

	/**
	 * �׽�Ʈ�� ���� ��ü �������� �ƱԸ�Ʈ�� ���޹��� �޼��� ����Ʈ�� ���
	 * @param message
	 */
	public static void print(ArrayList<String> message) {
		System.out.println();
		for (String data : message) {
			System.out.println(data);
		}
	}
	
	/**
	 * �������� ���� ȸ������ ����� ���
	 * @param list ȸ�����
	 */
	public static void printAllMember(ArrayList<Member> list) {
		System.out.println();
		
		if (list == null || list.isEmpty()) {
			System.out.println("Error : �ش� ȸ������ ������ �������� �ʽ��ϴ�.");
			return;
		}
		
		for (Member dto : list) {
			System.out.println(dto);
		}
	}

	/**
	 * �������� ���� ȸ�� ���
	 * @param dto ȸ��
	 */
	public static void printMember(Member dto) {
		if (dto != null) {
			System.out.println();
			System.out.println(dto);
		} else {
			System.out.println("Error : " + dto.getMemberId() + "������ �������� �ʽ��ϴ�.");
		}
	}
	
}

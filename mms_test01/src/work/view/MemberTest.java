package work.view;

import java.util.ArrayList;

import work.controller.MemberController;
import work.model.dto.Member;

public class MemberTest {

	public static void main(String[] args) {
		// 占쏙옙占쏙옙占쏙옙 占십깍옙화 회占쏙옙 占쏙옙체 占쏙옙占쏙옙
		System.out.println("bbb");
		System.out.println("ccc");
		Member dto1 = new Member("test01","password01","홍占썸동","010-1111-1111","test01@work.com","2017.05.05","G",7500,null);
		Member dto2 = new Member("test02","password02","占쏙옙占쏙옙占쏙옙","010-1111-1112","test02@work.com","2017.05.06","G",9500,null);
		Member dto3 = new Member("test03","password03","占싱쇽옙占쏙옙","010-1111-1113","test03@work.com","2017.05.07","G",3000,null);
		Member dto4 = new Member("test04","password04","占쏙옙占쏙옙占쏙옙","010-1111-1114","test04@work.com","2017.05.08","S",0,"占쏙옙占쌩깍옙");
		Member dto5 = new Member("test05","password05","占쏙옙占쏙옙占쏙옙","010-1111-1115","test05@work.com","2017.05.09","A",0,null);
		
		// 회占쏙옙占쏙옙占쏙옙 占쏙옙청占쏙옙占쏘객체 占쏙옙占쏙옙 : controller 
		MemberController controller = new MemberController();
		
		// 占쌓쏙옙트占시울옙 占쏙옙占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙
		ArrayList<String> message;
		Member dto = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String grade = null;
		String memberId = null;
		String memberPw = null;
		
		print("占쌓쏙옙트占쏙옙 占쏙옙占쏙옙 회占쏙옙占쏙옙체占쏙옙占쏙옙 占십깍옙화");
		int rows = controller.removeAllMember();
		print("회占쏙옙占쏙옙 占쏙옙占쏙옙처占쏙옙占실억옙占쏙옙占싹댐옙. : " + rows + "占쏙옙");
		
		print("[占쏙옙占쏙옙占쏙옙] 회占쏙옙 占쏙옙占�");
		controller.addMemberByAdmin(dto1);

		print("[占쏙옙占쏙옙占쏙옙] 占쏙옙占쏙옙 회占쏙옙 占쏙옙치 占쏙옙占쏙옙");
		list.add(dto2);
		list.add(dto3);
		list.add(dto4);
		list.add(dto5);
		controller.addListMemberByAdmin(list);
		
		// 회占쏙옙 占쏙옙占쏙옙 : 占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쌉뤄옙(占썩본 占싹뱄옙회占쏙옙)
		Member dto6 = new Member("test20","password20","占쏙옙占쏙옙占쏙옙","010-2222-1113","test20@work.com");
		Member dto7 = new Member("test21","password21","占쏙옙占쏙옙占�","010-2222-1114","test21@work.com");
		Member dto8 = new Member("test22","password22","占쏙옙占쏙옙占쏙옙","010-2222-1115","test22@work.com");
		
		print("회占쏙옙占쏙옙占� : 占썩본 占싹뱄옙회占쏙옙");
		print(controller.addMember(dto6));
		print(controller.addMember(dto7));
		print(controller.addMember(dto8));
		
		print("占쏙옙체회占쏙옙占쏙옙회");
		list = controller.getAllMember();
		printAllMember(list);
		
		print("占쏙옙頻占� 회占쏙옙 占쏙옙체회占쏙옙占쏙옙회 : 占싹뱄옙회占쏙옙");
		list = controller.getAllMemberByGrade("g");
		printAllMember(list);
		
		print("[占쏙옙占쏙옙占쏙옙] 회占쏙옙 占쏙옙占쏙옙 : test03");
		dto3 = new Member("test03","goodluck03","占싱쇽옙占쏙옙","010-1111-1113","goodluck03@work.com","2017.05.07","S",0,"占쏙옙占쏙옙占쏙옙");
		controller.setMember(dto3);
		
		print("占쏙옙頻占� 회占쏙옙 占쏙옙체회占쏙옙占쏙옙회 : 占쏙옙占싫몌옙占�");
		list = controller.getAllMemberByGrade("S");
		printAllMember(list);

		print("占쏙옙頻占� 회占쏙옙 占쏙옙체회占쏙옙占쏙옙회 : 占쏙옙占쏙옙占쏙옙");
		list = controller.getAllMemberByGrade("A");
		printAllMember(list);

		print("占쏙옙占쏙옙占쏙옙 占쏙옙회");
		dto = controller.getMyInfo("test01"); 
		printMember(dto);

		print("占싸깍옙占쏙옙");
		grade = controller.login("test01", "password01");
		print("[占싸깍옙占싸듸옙占� : " + grade + "]");

		grade = controller.login("test04", "password04");
		print("[占싸깍옙占싸듸옙占� : " + grade + "]");

		grade = controller.login("test05", "password05");
		print("[占싸깍옙占싸듸옙占� : " + grade + "]");

		print("회占쏙옙탈占쏙옙 : test01, test02, test03, test20, test21");
		String[] removes = {"test01", "test02", "test03", "test20", "test21"};
		for (String removeMemberId: removes) {
			print(controller.removeMember(removeMemberId));
		}
		
		print("占쏙옙체회占쏙옙占쏙옙회");
		printAllMember(controller.getAllMember());
		
		print("占쏙옙호 占쏙옙占쏙옙 占쏙옙: ");
		printMember(controller.getMember("test22"));
		print(controller.setPassword("test22", "password22", "bluesky22"));
		
		print("占쏙옙호 占쏙옙占쏙옙 占쏙옙: ");
		printMember(controller.getMember("test22"));
		
		print("占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙 : test04");
		printMember(controller.getMember("test04"));
		
		dto = new Member("test04","happyday","占쏙옙占쏙옙占�","010-1111-2773","happyday04@korea.com");
		controller.setMyInfo(dto);
		print("占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙 : test04");
		printMember(controller.getMember("test04"));
		
		// test04, happyday, 占쏙옙占쏙옙占�, 010-1111-2773, happyday04@korea.com
		print("占쏙옙占싱듸옙 찾占쏙옙 : 占쏙옙占쏙옙占�");
		memberId = controller.findMemberIdByMobile("占쏙옙占쏙옙占�", "010-1111-2773");
		print("占쏙옙占쏙옙占쏙옙占쏙옙占� 占쏙옙占싱듸옙占쌉니댐옙. : " + memberId);
		
		print("占쏙옙占싱듸옙 찾占쏙옙 : 占싱몌옙占쏙옙");
		memberId = controller.findMemberIdByEmail("占쏙옙占쏙옙占�", "happyday04@korea.com");
		print("占쏙옙占쏙옙占쏙옙占쏙옙占� 占쏙옙占싱듸옙占쌉니댐옙. : " + memberId);
		
		print("占쏙옙橘占싫� 찾占쏙옙 : ");
		memberPw = controller.findMemberPw("test04", "占쏙옙占쏙옙占�", "mobile", "010-1111-2773");
		print("占쏙옙占쏙옙占쏙옙占쏙옙占� 占쌈시발급억옙호占쌉니댐옙. : " + memberPw);
		
		memberPw = controller.findMemberPw("test04", "占쏙옙占쏙옙占�", "email", "happyday04@korea.com");
		print("占쏙옙占쏙옙占쏙옙占쏙옙占� 占쌈시발급억옙호占쌉니댐옙. : " + memberPw);
		
		print("占쏙옙橘占싫� 찾占쏙옙 占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙회 : test04");
		printMember(controller.getMember("test04"));
		
		print("占쏙옙체회占쏙옙占쏙옙회");
		printAllMember(controller.getAllMember());

		print("占싸그아울옙");
		controller.logout();
	}

	/**
	 * 占쌓쏙옙트占쏙옙 占쏙옙占쏙옙 占쏙옙체 占쏙옙占쏙옙占쏙옙占쏙옙 占싣규몌옙트占쏙옙 占쏙옙占쌨뱄옙占쏙옙 占쏙옙占쌘울옙占쏙옙 占쏙옙占�
	 * @param message 占쌨쇽옙占쏙옙
	 */
	public static void print(String message) {
		if (message != null) {
			System.out.println("\n### " + message + " ###");
			System.out.println("\n### " + message + " ###");
		}
	}

	/**
	 * 占쌓쏙옙트占쏙옙 占쏙옙占쏙옙 占쏙옙체 占쏙옙占쏙옙占쏙옙占쏙옙 占싣규몌옙트占쏙옙 占쏙옙占쌨뱄옙占쏙옙 占쌨쇽옙占쏙옙 占쏙옙占쏙옙트占쏙옙 占쏙옙占�
	 * @param message
	 */
	public static void print(ArrayList<String> message) {
		System.out.println();
		for (String data : message) {
			System.out.println(data);
		}
	}
	
	/**
	 * 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 회占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占� 占쏙옙占�
	 * @param list 회占쏙옙占쏙옙占�
	 */
	public static void printAllMember(ArrayList<Member> list) {
		System.out.println();
		
		if (list == null || list.isEmpty()) {
			System.out.println("Error : 占쌔댐옙 회占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占십쏙옙占싹댐옙.");
			return;
		}
		
		for (Member dto : list) {
			System.out.println(dto);
		}
	}

	/**
	 * 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 회占쏙옙 占쏙옙占�
	 * @param dto 회占쏙옙
	 */
	public static void printMember(Member dto) {
		if (dto != null) {
			System.out.println();
			System.out.println(dto);
		} else {
			System.out.println("Error : " + dto.getMemberId() + "占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占십쏙옙占싹댐옙.");
		}
	}
	
}

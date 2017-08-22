package work.view;

import java.util.ArrayList;

import work.controller.MemberController;
import work.model.dto.Member;

public class MemberTest {

	public static void main(String[] args) {
		// 관리자 초기화 회원 객체 생성
		Member dto1 = new Member("test01","password01","홍길동","010-1111-1111","test01@work.com","2017.05.05","G",7500,null);
		Member dto2 = new Member("test02","password02","강감찬","010-1111-1112","test02@work.com","2017.05.06","G",9500,null);
		Member dto3 = new Member("test03","password03","이순신","010-1111-1113","test03@work.com","2017.05.07","G",3000,null);
		Member dto4 = new Member("test04","password04","김유신","010-1111-1114","test04@work.com","2017.05.08","S",0,"송중기");
		Member dto5 = new Member("test05","password05","유관순","010-1111-1115","test05@work.com","2017.05.09","A",0,null);
		
		// 회원관리 요청제어객체 생성 : controller 
		MemberController controller = new MemberController();
		
		// 테스트시에 사용위한 변수 선언
		ArrayList<String> message;
		Member dto = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String grade = null;
		String memberId = null;
		String memberPw = null;
		
		print("테스트를 위한 회원전체삭제 초기화");
		int rows = controller.removeAllMember();
		print("회원이 삭제처리되었습니다. : " + rows + "명");
		
		print("[관리자] 회원 등록");
		controller.addMemberByAdmin(dto1);

		print("[관리자] 다중 회원 배치 가입");
		list.add(dto2);
		list.add(dto3);
		list.add(dto4);
		list.add(dto5);
		controller.addListMemberByAdmin(list);
		
		// 회원 가입 : 사용자 직접 입력(기본 일반회원)
		Member dto6 = new Member("test20","password20","강경한","010-2222-1113","test20@work.com");
		Member dto7 = new Member("test21","password21","김시은","010-2222-1114","test21@work.com");
		Member dto8 = new Member("test22","password22","최찬흠","010-2222-1115","test22@work.com");
		
		print("회원등록 : 기본 일반회원");
		print(controller.addMember(dto6));
		print(controller.addMember(dto7));
		print(controller.addMember(dto8));
		
		print("전체회원조회");
		list = controller.getAllMember();
		printAllMember(list);
		
		print("등급별 회원 전체회원조회 : 일반회원");
		list = controller.getAllMemberByGrade("g");
		printAllMember(list);
		
		print("[관리자] 회원 변경 : test03");
		dto3 = new Member("test03","goodluck03","이성훈","010-1111-1113","goodluck03@work.com","2017.05.07","S",0,"송혜교");
		controller.setMember(dto3);
		
		print("등급별 회원 전체회원조회 : 우수회원");
		list = controller.getAllMemberByGrade("S");
		printAllMember(list);

		print("등급별 회원 전체회원조회 : 관리자");
		list = controller.getAllMemberByGrade("A");
		printAllMember(list);

		print("내정보 조회");
		dto = controller.getMyInfo("test01"); 
		printMember(dto);

		print("로그인");
		grade = controller.login("test01", "password01");
		print("[로그인등급 : " + grade + "]");

		grade = controller.login("test04", "password04");
		print("[로그인등급 : " + grade + "]");

		grade = controller.login("test05", "password05");
		print("[로그인등급 : " + grade + "]");

		print("회원탈퇴 : test01, test02, test03, test20, test21");
		String[] removes = {"test01", "test02", "test03", "test20", "test21"};
		for (String removeMemberId: removes) {
			print(controller.removeMember(removeMemberId));
		}
		
		print("전체회원조회");
		printAllMember(controller.getAllMember());
		
		print("암호 변경 전: ");
		printMember(controller.getMember("test22"));
		print(controller.setPassword("test22", "password22", "bluesky22"));
		
		print("암호 변경 후: ");
		printMember(controller.getMember("test22"));
		
		print("내정보 변경 전 : test04");
		printMember(controller.getMember("test04"));
		
		dto = new Member("test04","happyday","김수현","010-1111-2773","happyday04@korea.com");
		controller.setMyInfo(dto);
		print("내정보 변경 후 : test04");
		printMember(controller.getMember("test04"));
		
		// test04, happyday, 김수현, 010-1111-2773, happyday04@korea.com
		print("아이디 찾기 : 모바일");
		memberId = controller.findMemberIdByMobile("김수현", "010-1111-2773");
		print("김수현님의 아이디입니다. : " + memberId);
		
		print("아이디 찾기 : 이메일");
		memberId = controller.findMemberIdByEmail("김수현", "happyday04@korea.com");
		print("김수현님의 아이디입니다. : " + memberId);
		
		print("비밀번호 찾기 : ");
		memberPw = controller.findMemberPw("test04", "김수현", "mobile", "010-1111-2773");
		print("김수현님의 임시발급암호입니다. : " + memberPw);
		
		memberPw = controller.findMemberPw("test04", "김수현", "email", "happyday04@korea.com");
		print("김수현님의 임시발급암호입니다. : " + memberPw);
		
		print("비밀번호 찾기 후 내정보 조회 : test04");
		printMember(controller.getMember("test04"));
		
		print("전체회원조회");
		printAllMember(controller.getAllMember());

		print("로그아웃");
		controller.logout();
	}

	/**
	 * 테스트를 위한 객체 생성없이 아규먼트로 전달받은 문자열을 출력
	 * @param message 메세지
	 */
	public static void print(String message) {
		if (message != null) {
			System.out.println("\n### " + message + " ###");
			System.out.println("\n### " + message + " ###");
		}
	}

	/**
	 * 테스트를 위한 객체 생성없이 아규먼트로 전달받은 메세지 리스트를 출력
	 * @param message
	 */
	public static void print(ArrayList<String> message) {
		System.out.println();
		for (String data : message) {
			System.out.println(data);
		}
	}
	
	/**
	 * 응답결과로 받은 회원들의 목록을 출력
	 * @param list 회원목록
	 */
	public static void printAllMember(ArrayList<Member> list) {
		System.out.println();
		
		if (list == null || list.isEmpty()) {
			System.out.println("Error : 해당 회원들의 정보가 존재하지 않습니다.");
			return;
		}
		
		for (Member dto : list) {
			System.out.println(dto);
		}
	}

	/**
	 * 응답결과로 받은 회원 출력
	 * @param dto 회원
	 */
	public static void printMember(Member dto) {
		if (dto != null) {
			System.out.println();
			System.out.println(dto);
		} else {
			System.out.println("Error : " + dto.getMemberId() + "정보가 존재하지 않습니다.");
		}
	}
	
}

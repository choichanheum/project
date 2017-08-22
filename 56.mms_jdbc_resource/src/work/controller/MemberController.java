package work.controller;

import java.util.ArrayList;

import work.model.dto.Member;
import work.model.service.MemberService;

/**
 * 회원관리 요청 제어 Controller 클래스
 * 
 * ## Controller 클래스 역할
 * 1. 요청파악
 * 2. 요청데이터 추출 : 매개변수 전달
 * 3. 요청데이터 검증
 * 4. Model 요청처리 의뢰
 * 5. Model 요청결과받아서 응답위한 설정
 * 6. 결과 응답 : 화면 이동(웹) 
 * 
 * @author 임경혜
 *
 */
public class MemberController {
	/** 회원관리 시스템 서비스를 사용하기위한 객체 */
	private MemberService service = new MemberService();
	
	/**
	 * 회원가입 요청데이터 검증
	 * -- 필수항목 : 아이디, 비밀번호, 이름, 연락처, 이메일
	 * -- 아이디: 6~20자리 길이제한
	 * -- 비밀번호 : 8~20자리 길이제한
	 * 
	 * 1. 아규먼트가 null이 아니면
	 * 2. 회원의 아이디, 암호, 이름, 연락처, 이메일을 가져와서
	 * 3. 아이디가 null이 아니면서 공백을 제거한 아이디의 길이가 6자리 ~ 20자리 이내이면
	 * 4. 아이디가 중복아이디가 아니면
	 * 5. 암호가 null 아니면서 공백을 제거한 암호의 길이가 8자리 ~ 20자리 이내이면
	 * 6. 이름이 null 아니면서 공백을 제거한 이름의 길이가 0보다 큰지 비교해서
	 * 7. 연락처가 null 아니면서 공백을 제거한 연락처가 길이가 0보다 큰지 비교해서
	 * 8. 이메일이 null 아니면서 공백을 제거한 이메일이 길이가 0보다 큰지 비교해서
	 * 9. 1~8번 사항이 올바르면 올바른 데이터이므로 Model에게 회원가입 요청 의뢰
	 * 10. Model 회원가입요청 결과받아서 회원가입요청 응답처리
	 * 		(10-1) 가입 성공
	 * 		(10-2) 가입 실패
	 * 
	 * 11. 1~8번 사항이 올바르지 않으면 회원가입 오류 응답처리
	 * 
	 * @param dto 회원객체
	 * @return 결과메세지 성공: 가입축하메세지, 실패: 오류항목메세지
	 */
	public ArrayList<String> addMember(String memberId, String memberPw, String memberName, String mobile, String email) {
		return addMember(new Member(memberId, memberPw, memberName, mobile, email));
	}
	
	public ArrayList<String> addMember(Member dto) {
		ArrayList<String> messages = new ArrayList<String>();
		
		if (dto != null) {
			String memberId = dto.getMemberId();
			String memberPw = dto.getMemberPw();
			String memberName = dto.getMemberName();
			String mobile = dto.getMobile();
			String email = dto.getEmail();
			
			// 아이디 검증
			if (memberId != null) {
				memberId = memberId.trim();
				if (memberId.length() >= 6 &&
						memberId.length() <= 20) {
					if (isExist(memberId)) {
						messages.add("아이디가 중복되었습니다. 새로이 입력하시기 바랍니다.");
						return messages;
					}
				} else {
					messages.add("아이디는 6자리 ~ 20자리 이내로 입력하시기 바랍니다.");
				}
			} else {
				messages.add("아이디는 필수입력 항목입니다.");
			}
			
			// 비밀번호 검증 : 필수입력항목, 길이 8자리 ~ 20자리 이내
			if (memberPw == null || 
					memberPw.trim().length() < 8 ||
					memberPw.trim().length() > 20) {
				messages.add("비밀번호는 8자리 ~ 20자리 이내로 입력하시기 바랍니다.");
			}
			
			// 이름 검증 : 필수입력항목
			if (memberName == null || memberName.trim().length() == 0) {
				messages.add("이름은 필수입력항목입니다.");
			}
			
			// 연락처 검증 : 필수입력항목
			if (mobile == null || mobile.trim().length() == 0) {
				messages.add("연락처는 필수입력항목입니다.");
			}
			
			// 이메일 검증 : 필수입력항목
			if (email == null || email.trim().length() == 0) {
				messages.add("이메일은 필수입력항목입니다.");
			}
			
		} else {
			messages.add("회원가입 등록정보가 존재하지 않습니다.");
		}
		
		// 가입 정보 검증 성공시
		if (messages.isEmpty()) {
			int rows = service.addMember(dto);
			if (rows > 0) {
				messages.add(dto.getMemberId() + "님 회원가입이 정상처리되었습니다. 로그인후 회원서비스를 이용하시기 바랍니다.");
			} else {
				messages.add("회원가입이 정상적으로 처리되지 않았습니다. 다시 확인하시기 바랍니다.");
			}
		}
		
		// 가입 정보 검증 실패시 : Model에게 요청 처리 의뢰
		return messages;
	}
	
	/**
	 * 관리자 회원 등록
	 * @param dto
	 */
	public void addMemberByAdmin(Member dto) {
		service.addMemberByAdmin(dto);
	}
	
	/**
	 * 관리자 회원등록 배치 처리
	 * @param list 등록회원 명단
	 * @return 등록회원수, 오류시 0
	 */
	public void addListMemberByAdmin(ArrayList<Member> list) {
		service.addListMemberByAdmin(list);
	}
	
	/**
	 * 아이디 중복 검색
	 * @param memberId 아이디
	 * @return 존재하면 true, 미존재하면 false
	 */
	public boolean isExist(String memberId) {
		return service.isExist(memberId);
	}
	
	/**
	 * 로그인
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @return 회원등급, 오류발생시(아이디미존재, 암호틀림) null 반환
	 */
	public String login(String memberId, String memberPw) {
		// 데이터 검증 : 로그인 아이디, 비밀번호 필수입력
		if (memberId != null && memberId.trim().length() > 0 &&
				memberPw != null && memberPw.trim().length() > 0) {
			String grade = service.login(memberId, memberPw);
			if (grade != null) {
				// 로그인 성공
				System.out.println(memberId + "님 로그인 성공하였습니다.");
				return grade;
			} else {
				// 로그인 실패 : 아이디 미존재 또는 비밀번호 틀림
				System.out.println("Error: 아이디, 비밀번호를 다시확인하시기 바랍니다.");
				return null;
			}
		} else {
			// 로그인 실패 : 필수항목 미입력 오류 처리
			System.out.println("Error: 아이디, 비밀번호 미입력 하셨습니다.");
			return null;
		}
	}
	
	/**
	 * 로그아웃
	 */
	public void logout() {
		System.out.println("[로그아웃] 프로그램을 종료합니다.");
		System.out.println("[로그아웃] 즐거운 주말을 보내시기 바랍니다. 자바씨, SQL양, JDBC친구와 함께 ^^");
		System.exit(1);
	}
	
	/**
	 * 내정보조회
	 * @param memberId 아이디
	 * @return 검색회원객체
	 */
	public Member getMyInfo(String memberId) {
		if (memberId != null) {
			return service.getMember(memberId);
		}
		
		return null;
	}
	
	/**
	 * 내정보변경
	 * @param dto 변경 객체
	 * @return 변경결과 메세지
	 */
	public String setMyInfo(Member dto) {
		String message = null;
		String memberId = dto.getMemberId();
		if (memberId != null && memberId.trim().length() > 0) {
			int rows = service.setMyInfo(dto);
			if (rows > 0) {
				message = memberId + "님 내정보변경 처리가 정상 완료되었습니다.";
			} else {
				message = memberId + "님 내정보변경 처리시 문제가 발생되었습니다. 다시 확인하시기 바랍니다.";
			}
		} else {
			message = "변경 회원 정보를 입력하시기 바랍니다.";
		}
		
		return message;
	}
	
	/**
	 * 암호변경
	 * @param memberId 아이디
	 * @param memberPw 기존암호
	 * @param modifyMemberPw 변경암호
	 * @return 결과메세지
	 */
	public String setPassword(String memberId, String memberPw, String modifyMemberPw) {
		String message = null;
		int rows = service.setPassword(memberId, memberPw, modifyMemberPw);
		if (rows > 0) {
			message = memberId + "님 암호변경이 완료되었습니다. 보안을 위해서 자주 변경하시기 바랍니다.";
		} else {
			message = memberId + "님 암호변경중 문제가 발생되었습니다. 다시 확인하시기 바랍니다.";
		}
		
		return message;
	}
	
	/**
	 * 회원탈퇴
	 * @param memberId 아이디
	 * @return 결과메세지
	 */
	public String removeMember(String memberId) {
		String message = null;
		if (memberId != null && memberId.trim().length() > 0) {
			int rows = service.removeMember(memberId);
			if (rows > 0) {
				message = memberId + "님 회원탈퇴 처리가 정상 완료되었습니다.";
			} else {
				message = "회원탈퇴시 문제가 발생되었습니다. 다시 확인하시기 바랍니다.";
			}
		} else {
			message = "탈퇴 회원 아이디를 입력하시기 바랍니다.";
		}
		
		return message;
	}
	
	/**
	 * 전체회원 삭제 : 테스트 초기화
	 * @return
	 */
	public int removeAllMember() {
		return service.removeAllMember();
	}
	
	/**
	 * 전체회원 조회
	 * @return
	 */
	public ArrayList<Member> getAllMember() {
		return service.getAllMember();
	}
	
	/**
	 * 등급별 전체회원 조회
	 * @param grade
	 * @return 해당등급 전체회원
	 */
	public ArrayList<Member> getAllMemberByGrade(String grade) {
		return service.getAllMemberByGrade(grade.toUpperCase());
	}
	
	/**
	 * 회원상세조회
	 * @param memberId
	 * @return
	 */
	public Member getMember(String memberId) {
		return service.getMember(memberId);
	}
	
	/**
	 * 아이디 찾기
	 * @param memberName 이름
	 * @param mobile 연락처
	 * @return 아이디, 오류시 null
	 */
	public String findMemberIdByMobile(String memberName, String mobile) {
		return service.findMemberIdByMobile(memberName, mobile);
	}

	/**
	 * 아이디 찾기
	 * @param memberName 이름
	 * @param email 이메일
	 * @return 아이디, 오류시 null
	 */
	public String findMemberIdByEmail(String memberName, String email) {
		return service.findMemberIdByEmail(memberName, email);
	}

	/**
	 * 암호 찾기
	 * @param memberId 아이디
	 * @param memberName 이름
	 * @param keywordType 검색타입 (mobile, email)
	 * @param keyword 검색어
	 * @return 임시발급 변경암호, 오류시 오류메세지
	 */
	public String findMemberPw(String memberId, String memberName, String keywordType, String keyword) {
		String tmpMemberPw = service.findMemberPw(memberId, memberName, keywordType, keyword);
		if (tmpMemberPw != null) {
			return tmpMemberPw;
		} else {
			return "Error : 개인정보를 다시 확인하시기 바랍니다.";
		}
	}
	
	/**
	 * 관리자 회원 변경
	 * @param dto 변경회원
	 * @return 결과 메세지
	 */
	public String setMember(Member dto) {
		String message = null;
		String memberId = dto.getMemberId();
		if (memberId != null && memberId.trim().length() > 0) {
			int rows = service.setMember(dto);
			if (rows > 0) {
				message = memberId + "님 내정보변경 처리가 정상 완료되었습니다.";
			} else {
				message = memberId + "님 내정보변경 처리시 문제가 발생되었습니다. 다시 확인하시기 바랍니다.";
			}
		} else {
			message = "변경 회원 정보를 입력하시기 바랍니다.";
		}
		
		return message;
	}
	
}














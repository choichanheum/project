package work.model.service;

import java.util.ArrayList;
import java.util.Iterator;

import work.model.dao.MemberDao;
import work.model.dto.Member;
import work.util.Utility;

/**
 * 회원관리 서비스(업무프로세스) 클래스
 * @author 임경혜
 *
 */
public class MemberService {
	/** 회원테이블에 대해서 crud 위한 dao 객체 */ 
	private MemberDao dao = MemberDao.getInstance();
	
	/**
	 * 회원등록 서비스
	 * -- 가입일 : 현재날짜 
	 * -- 등급   :G
	 * -- 마일리지: 1000
	 * 
	 * @param dto 회원객체(아이디, 암호, 이름, 연락처, 이메일)
	 * @return 등록행수, 오류시 0
	 */
	public int addMember(Member dto) {
		// 가입일, 등급, 마일리지 정보 추가 설정
		dto.setEntryDate(Utility.getCurrentDate());
		dto.setGrade("G");
		dto.setMileage(1000);
		
		// DAO 클래스에게 레코드 추가 요청
		boolean result = isExist(dto.getMemberId());
		if (!result) {
			return dao.insertMember(dto);
		} else {
			return 0;
		}
	}

	/**
	 * 관리자 회원 등록
	 * @param dto 등록회원
	 * @return 등록행수, 오류시 0
	 */
	public int addMemberByAdmin(Member dto) {
		boolean result = isExist(dto.getMemberId());
		if (!result) {
			return dao.insertMember(dto);
		} else {
			return 0;
		}
	}
	
	/**
	 * 관리자 회원등록 배치 처리
	 * @see PrepareStatement#addBatch()
	 * @see PreparedStatement#executeBatch()
	 * @param list 등록회원 명단
	 * @return 등록회원수, 오류시 0
	 */
	public int[] addListMemberByAdmin(ArrayList<Member> list) {
		Iterator<Member> iterator = list.iterator();
		Member dto = null;
		boolean result = false;
		while (iterator.hasNext()) {
			dto = iterator.next();
			result = isExist(dto.getMemberId());
			if (result) {
				iterator.remove();
			} 
		}
		
		return dao.insertListMember(list);
	}
	
	/**
	 * 로그인
	 * @param memberId 아이디
	 * @param memberPw 암호
	 * @return 등급, 실패시 null
	 */
	public String login(String memberId, String memberPw) {
		return dao.login(memberId, memberPw);
	}
	
	/**
	 * 내정보 조회
	 * @param memberId 아이디
	 * @return 회원객체, 오류시 null
	 */
	public Member getMember(String memberId) {
		return dao.selectOneMember(memberId);
	}
	
	/**
	 * 회원탈퇴
	 * @param memberId 아이디
	 * @return 삭제행수, 오류시 0
	 */
	public int removeMember(String memberId) {
		return dao.deleteMember(memberId);
	}

	/**
	 * 회원전체삭제 : 테스트 초기화
	 * @return 삭제행수
	 */
	public int removeAllMember() {
		return dao.deleteAllMember();
	}
	
	/**
	 * 내정보 변경
	 * @param dto 회원객체
	 * @return 변경행수, 오류시 0
	 */
	public int setMyInfo(Member dto) {
		return dao.updateMyInfo(dto);
	}
	
	/**
	 * 관리자 회원정보 변경
	 * @param dto 회원객체
	 * @return 변경행수, 오류시 0
	 */
	public int setMember(Member dto) {
		return dao.updateMember(dto);
	}
	
	/**
	 * 암호변경
	 * @param memberId 아이디
	 * @param memberPw 암호
	 * @param modifyMemberPw 변경암호
	 * @return 변경행수, 오류시 0
	 */
	public int setPassword(String memberId, String memberPw, String modifyMemberPw) {
		return dao.updatePassword(memberId, memberPw, modifyMemberPw);
	}
	
	/**
	 * 아이디 찾기
	 * @param memberName 이름
	 * @param mobile 연락처
	 * @return 아이디, 오류시 null
	 */
	public String findMemberIdByMobile(String memberName, String mobile) {
		return dao.selectMemberIdByMobile(memberName, mobile);
	}
	
	/**
	 * 아이디 찾기
	 * @param memberName 이름
	 * @param email 이메일
	 * @return 아이디, 오류시 null
	 */
	public String findMemberIdByEmail(String memberName, String email) {
		return dao.selectMemberIdByEmail(memberName, email);
	}

	/**
	 * 암호 찾기
	 * @param memberId 아이디
	 * @param memberName 이름
	 * @param keywordType 검색 타입 : mobile, email
	 * @param keyword 검색데이터
	 * @return 임시발급 변경암호, 오류시 null
	 */
	public String findMemberPw(String memberId, String memberName, String keywordType, String keyword) {
		boolean result = dao.selectMemberPw(memberId, memberName, keywordType, keyword);
		if (result) {
			String tmpMemberPw = Utility.getSecureCodeString(8, true);
			int rows = dao.updatePassword(memberId, tmpMemberPw);
			if (rows == 1) {
				return tmpMemberPw;
			}
		}
		
		return null;
	}
	
	/**
	 * 아이디 중복여부 조회
	 * @param memberId 아이디
	 * @return 존재 true, 미존재 false
	 */
	public boolean isExist(String memberId) {
		if (dao.selectIsExist(memberId) != null) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 전체회원조회
	 * @return 전체회원
	 */
	public ArrayList<Member> getAllMember() {
		return dao.selectListMember();
	}
	
 	/**
 	 * 등급별 회원조회 : 
 	 * @param grade 대문자 변환
 	 * @return 해당등급 전체회원, 미존재시 null
 	 */
	public ArrayList<Member> getAllMemberByGrade(String grade) {
		return dao.selectListMemberByGrade(grade);
	}
	
}


















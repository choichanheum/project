package work.model.service;

import java.util.ArrayList;
import java.util.Iterator;

import work.model.dao.MemberDao;
import work.model.dto.Member;
import work.util.Utility;

/**
 * ȸ������ ����(�������μ���) Ŭ����
 * @author �Ӱ���
 *
 */
public class MemberService {
	/** ȸ�����̺� ���ؼ� crud ���� dao ��ü */ 
	private MemberDao dao = MemberDao.getInstance();
	
	/**
	 * ȸ����� ����
	 * -- ������ : ���糯¥ 
	 * -- ���   :G
	 * -- ���ϸ���: 1000
	 * 
	 * @param dto ȸ����ü(���̵�, ��ȣ, �̸�, ����ó, �̸���)
	 * @return ������, ������ 0
	 */
	public int addMember(Member dto) {
		// ������, ���, ���ϸ��� ���� �߰� ����
		dto.setEntryDate(Utility.getCurrentDate());
		dto.setGrade("G");
		dto.setMileage(1000);
		
		// DAO Ŭ�������� ���ڵ� �߰� ��û
		boolean result = isExist(dto.getMemberId());
		System.out.println("서비스 result값 : "+result);
		if (!result) {
			return dao.insertMember(dto);
		} else {
			return 0;
		}
	}

	/**
	 * ������ ȸ�� ���
	 * @param dto ���ȸ��
	 * @return ������, ������ 0
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
	 * ������ ȸ����� ��ġ ó��
	 * @see PrepareStatement#addBatch()
	 * @see PreparedStatement#executeBatch()
	 * @param list ���ȸ�� ���
	 * @return ���ȸ����, ������ 0
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
	 * �α���
	 * @param memberId ���̵�
	 * @param memberPw ��ȣ
	 * @return ���, ���н� null
	 */
	public String login(String memberId, String memberPw) {
		return dao.login(memberId, memberPw);
	}
	
	/**
	 * ������ ��ȸ
	 * @param memberId ���̵�
	 * @return ȸ����ü, ������ null
	 */
	public Member getMember(String memberId) {
		return dao.selectOneMember(memberId);
	}
	
	/**
	 * ȸ��Ż��
	 * @param memberId ���̵�
	 * @return �������, ������ 0
	 */
	public int removeMember(String memberId) {
		return dao.deleteMember(memberId);
	}

	/**
	 * ȸ����ü���� : �׽�Ʈ �ʱ�ȭ
	 * @return �������
	 */
	public int removeAllMember() {
		return dao.deleteAllMember();
	}
	
	/**
	 * ������ ����
	 * @param dto ȸ����ü
	 * @return �������, ������ 0
	 */
	public int setMyInfo(Member dto) {
		return dao.updateMyInfo(dto);
	}
	
	/**
	 * ������ ȸ������ ����
	 * @param dto ȸ����ü
	 * @return �������, ������ 0
	 */
	public int setMember(Member dto) {
		return dao.updateMember(dto);
	}
	
	/**
	 * ��ȣ����
	 * @param memberId ���̵�
	 * @param memberPw ��ȣ
	 * @param modifyMemberPw �����ȣ
	 * @return �������, ������ 0
	 */
	public int setPassword(String memberId, String memberPw, String modifyMemberPw) {
		return dao.updatePassword(memberId, memberPw, modifyMemberPw);
	}
	
	/**
	 * ���̵� ã��
	 * @param memberName �̸�
	 * @param mobile ����ó
	 * @return ���̵�, ������ null
	 */
	public String findMemberIdByMobile(String memberName, String mobile) {
		return dao.selectMemberIdByMobile(memberName, mobile);
	}
	
	/**
	 * ���̵� ã��
	 * @param memberName �̸�
	 * @param email �̸���
	 * @return ���̵�, ������ null
	 */
	public String findMemberIdByEmail(String memberName, String email) {
		return dao.selectMemberIdByEmail(memberName, email);
	}

	/**
	 * ��ȣ ã��
	 * @param memberId ���̵�
	 * @param memberName �̸�
	 * @param keywordType �˻� Ÿ�� : mobile, email
	 * @param keyword �˻�������
	 * @return �ӽù߱� �����ȣ, ������ null
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
	 * ���̵� �ߺ����� ��ȸ
	 * @param memberId ���̵�
	 * @return ���� true, ������ false
	 */
	public boolean isExist(String memberId) {
		if (dao.selectIsExist(memberId) != null) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * ��üȸ����ȸ
	 * @return ��üȸ��
	 */
	public ArrayList<Member> getAllMember() {
		return dao.selectListMember();
	}
	
 	/**
 	 * ��޺� ȸ����ȸ : 
 	 * @param grade �빮�� ��ȯ
 	 * @return �ش��� ��üȸ��, ������� null
 	 */
	public ArrayList<Member> getAllMemberByGrade(String grade) {
		return dao.selectListMemberByGrade(grade);
	}
	
}


















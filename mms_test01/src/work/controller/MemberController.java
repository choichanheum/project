package work.controller;

import java.util.ArrayList;

import work.model.dto.Member;
import work.model.service.MemberService;

public class MemberController {
	/** ȸ������ �ý��� ���񽺸� ����ϱ����� ��ü */
	private MemberService service = new MemberService();
	
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
			
			// ���̵� ����
			if (memberId != null) {
				memberId = memberId.trim();
				if (memberId.length() >= 6 &&
						memberId.length() <= 20) {
					if (isExist(memberId)) {
						messages.add("���̵� �ߺ��Ǿ����ϴ�. ������ �Է��Ͻñ� �ٶ��ϴ�.");
						return messages;
					}
				} else {
					messages.add("���̵�� 6�ڸ� ~ 20�ڸ� �̳��� �Է��Ͻñ� �ٶ��ϴ�.");
				}
			} else {
				messages.add("���̵�� �ʼ��Է� �׸��Դϴ�.");
			}
			
			// ��й�ȣ ���� : �ʼ��Է��׸�, ���� 8�ڸ� ~ 20�ڸ� �̳�
			if (memberPw == null || 
					memberPw.trim().length() < 8 ||
					memberPw.trim().length() > 20) {
				messages.add("��й�ȣ�� 8�ڸ� ~ 20�ڸ� �̳��� �Է��Ͻñ� �ٶ��ϴ�.");
			}
			
			// �̸� ���� : �ʼ��Է��׸�
			if (memberName == null || memberName.trim().length() == 0) {
				messages.add("�̸��� �ʼ��Է��׸��Դϴ�.");
			}
			
			// ����ó ���� : �ʼ��Է��׸�
			if (mobile == null || mobile.trim().length() == 0) {
				messages.add("����ó�� �ʼ��Է��׸��Դϴ�.");
			}
			
			// �̸��� ���� : �ʼ��Է��׸�
			if (email == null || email.trim().length() == 0) {
				messages.add("�̸����� �ʼ��Է��׸��Դϴ�.");
			}
			
		} else {
			messages.add("ȸ������ ��������� �������� �ʽ��ϴ�.");
		}
		
		// ���� ���� ���� ������
		if (messages.isEmpty()) {
			int rows = service.addMember(dto);
			if (rows > 0) {
				messages.add(dto.getMemberId() + "�� ȸ�������� ����ó���Ǿ����ϴ�. �α����� ȸ�����񽺸� �̿��Ͻñ� �ٶ��ϴ�.");
			} else {
				messages.add("ȸ�������� ���������� ó������ �ʾҽ��ϴ�. �ٽ� Ȯ���Ͻñ� �ٶ��ϴ�.");
			}
		}
		
		// ���� ���� ���� ���н� : Model���� ��û ó�� �Ƿ�
		return messages;
	}
	
	/**
	 * ������ ȸ�� ���
	 * @param dto
	 */
	public void addMemberByAdmin(Member dto) {
		service.addMemberByAdmin(dto);
	}
	
	/**
	 * ������ ȸ����� ��ġ ó��
	 * @param list ���ȸ�� ���
	 * @return ���ȸ����, ������ 0
	 */
	public void addListMemberByAdmin(ArrayList<Member> list) {
		service.addListMemberByAdmin(list);
	}
	
	/**
	 * ���̵� �ߺ� �˻�
	 * @param memberId ���̵�
	 * @return �����ϸ� true, �������ϸ� false
	 */
	public boolean isExist(String memberId) {
		return service.isExist(memberId);
	}
	
	/**
	 * �α���
	 * @param memberId ���̵�
	 * @param memberPw ��й�ȣ
	 * @return ȸ�����, �����߻���(���̵������, ��ȣƲ��) null ��ȯ
	 */
	public String login(String memberId, String memberPw) {
		// ������ ���� : �α��� ���̵�, ��й�ȣ �ʼ��Է�
		if (memberId != null && memberId.trim().length() > 0 &&
				memberPw != null && memberPw.trim().length() > 0) {
			String grade = service.login(memberId, memberPw);
			if (grade != null) {
				// �α��� ����
				System.out.println(memberId + "�� �α��� �����Ͽ����ϴ�.");
				return grade;
			} else {
				// �α��� ���� : ���̵� ������ �Ǵ� ��й�ȣ Ʋ��
				System.out.println("Error: ���̵�, ��й�ȣ�� �ٽ�Ȯ���Ͻñ� �ٶ��ϴ�.");
				return null;
			}
		} else {
			// �α��� ���� : �ʼ��׸� ���Է� ���� ó��
			System.out.println("Error: ���̵�, ��й�ȣ ���Է� �ϼ̽��ϴ�.");
			return null;
		}
	}
	
	/**
	 * �α׾ƿ�
	 */
	public void logout() {
		System.out.println("[�α׾ƿ�] ���α׷��� �����մϴ�.");
		System.out.println("[�α׾ƿ�] ��ſ� �ָ��� �����ñ� �ٶ��ϴ�. �ڹپ�, SQL��, JDBCģ���� �Բ� ^^");
		System.exit(1);
	}
	
	/**
	 * ��������ȸ
	 * @param memberId ���̵�
	 * @return �˻�ȸ����ü
	 */
	public Member getMyInfo(String memberId) {
		if (memberId != null) {
			return service.getMember(memberId);
		}
		
		return null;
	}
	
	/**
	 * ����������
	 * @param dto ���� ��ü
	 * @return ������ �޼���
	 */
	public String setMyInfo(Member dto) {
		String message = null;
		String memberId = dto.getMemberId();
		if (memberId != null && memberId.trim().length() > 0) {
			int rows = service.setMyInfo(dto);
			if (rows > 0) {
				message = memberId + "�� ���������� ó���� ���� �Ϸ�Ǿ����ϴ�.";
			} else {
				message = memberId + "�� ���������� ó���� ������ �߻��Ǿ����ϴ�. �ٽ� Ȯ���Ͻñ� �ٶ��ϴ�.";
			}
		} else {
			message = "���� ȸ�� ������ �Է��Ͻñ� �ٶ��ϴ�.";
		}
		
		return message;
	}
	
	/**
	 * ��ȣ����
	 * @param memberId ���̵�
	 * @param memberPw ������ȣ
	 * @param modifyMemberPw �����ȣ
	 * @return ����޼���
	 */
	public String setPassword(String memberId, String memberPw, String modifyMemberPw) {
		String message = null;
		int rows = service.setPassword(memberId, memberPw, modifyMemberPw);
		if (rows > 0) {
			message = memberId + "�� ��ȣ������ �Ϸ�Ǿ����ϴ�. ������ ���ؼ� ���� �����Ͻñ� �ٶ��ϴ�.";
		} else {
			message = memberId + "�� ��ȣ������ ������ �߻��Ǿ����ϴ�. �ٽ� Ȯ���Ͻñ� �ٶ��ϴ�.";
		}
		
		return message;
	}
	
	/**
	 * ȸ��Ż��
	 * @param memberId ���̵�
	 * @return ����޼���
	 */
	public String removeMember(String memberId) {
		String message = null;
		if (memberId != null && memberId.trim().length() > 0) {
			int rows = service.removeMember(memberId);
			if (rows > 0) {
				message = memberId + "�� ȸ��Ż�� ó���� ���� �Ϸ�Ǿ����ϴ�.";
			} else {
				message = "ȸ��Ż��� ������ �߻��Ǿ����ϴ�. �ٽ� Ȯ���Ͻñ� �ٶ��ϴ�.";
			}
		} else {
			message = "Ż�� ȸ�� ���̵� �Է��Ͻñ� �ٶ��ϴ�.";
		}
		
		return message;
	}
	
	/**
	 * ��üȸ�� ���� : �׽�Ʈ �ʱ�ȭ
	 * @return
	 */
	public int removeAllMember() {
		return service.removeAllMember();
	}
	
	/**
	 * ��üȸ�� ��ȸ
	 * @return
	 */
	public ArrayList<Member> getAllMember() {
		return service.getAllMember();
	}
	
	/**
	 * ��޺� ��üȸ�� ��ȸ
	 * @param grade
	 * @return �ش��� ��üȸ��
	 */
	public ArrayList<Member> getAllMemberByGrade(String grade) {
		return service.getAllMemberByGrade(grade.toUpperCase());
	}
	
	/**
	 * ȸ������ȸ
	 * @param memberId
	 * @return
	 */
	public Member getMember(String memberId) {
		return service.getMember(memberId);
	}
	
	/**
	 * ���̵� ã��
	 * @param memberName �̸�
	 * @param mobile ����ó
	 * @return ���̵�, ������ null
	 */
	public String findMemberIdByMobile(String memberName, String mobile) {
		return service.findMemberIdByMobile(memberName, mobile);
	}

	/**
	 * ���̵� ã��
	 * @param memberName �̸�
	 * @param email �̸���
	 * @return ���̵�, ������ null
	 */
	public String findMemberIdByEmail(String memberName, String email) {
		return service.findMemberIdByEmail(memberName, email);
	}

	/**
	 * ��ȣ ã��
	 * @param memberId ���̵�
	 * @param memberName �̸�
	 * @param keywordType �˻�Ÿ�� (mobile, email)
	 * @param keyword �˻���
	 * @return �ӽù߱� �����ȣ, ������ �����޼���
	 */
	public String findMemberPw(String memberId, String memberName, String keywordType, String keyword) {
		String tmpMemberPw = service.findMemberPw(memberId, memberName, keywordType, keyword);
		if (tmpMemberPw != null) {
			return tmpMemberPw;
		} else {
			return "Error : ���������� �ٽ� Ȯ���Ͻñ� �ٶ��ϴ�.";
		}
	}
	
	/**
	 * ������ ȸ�� ����
	 * @param dto ����ȸ��
	 * @return ��� �޼���
	 */
	public String setMember(Member dto) {
		String message = null;
		String memberId = dto.getMemberId();
		if (memberId != null && memberId.trim().length() > 0) {
			int rows = service.setMember(dto);
			if (rows > 0) {
				message = memberId + "�� ���������� ó���� ���� �Ϸ�Ǿ����ϴ�.";
			} else {
				message = memberId + "�� ���������� ó���� ������ �߻��Ǿ����ϴ�. �ٽ� Ȯ���Ͻñ� �ٶ��ϴ�.";
			}
		} else {
			message = "���� ȸ�� ������ �Է��Ͻñ� �ٶ��ϴ�.";
		}
		
		return message;
	}
	
}














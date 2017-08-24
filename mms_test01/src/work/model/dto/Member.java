package work.model.dto;


/**
 * <pre>
 * 회원 도메인 클래스 모델링
 * 
 * ## 자바 적용 기술
 * -- 클래스선언, 멤버변수, 메서드, 생성자
 * -- 데이터타입 : 기본형, 객체형
 * -- Encapsulation : 은닉성(데이터, 알고리즘 information hiding)
 * -- 접근권한(access modifier) : public, protected, 생략(default), private
 * -- 직렬화객체
 * 
 * ## 회원 property(멤버변수)
 * 1.아이디 문자열 : memberId
 * 2.비밀번호 문자열 : memberPw
 * 3.이름 문자열 : memberName
 * 4.연락처 문자열(기본형식: 010-1234-1234) : mobile
 * 5.이메일 문자열 : email
 * 6.가입일 문자열(기본형식: 년도4자리.월2자리.일2자리) : entryDate
 * 7.등급 문자열(회원종류 : 일반(G), 우수(S), 관리자(A)) : grade
 * 8.마일리지 숫자 : 일반회원 : mileage
 * 9.담당자 문자열 : 우수회원 : manager
 * 
 * </pre>
 * 
 * @author 임경혜
 * @version ver.1.0
 * @since	jdk1.4
 */
public class Member {
	/** 회원 아이디 정보 */
	private String memberId = "Guest";
	/** 회원 비밀번호 정보 */
	private String memberPw;
	/** 회원이름 정보 */
	private String memberName;
	/** 회원 휴대폰 정보 (형식:010-1234-1234) */
	private String mobile;
	/** 회원 이메일 정보 */
	private String email;
	/** 회원 가입일 정보 (형식: 년도4자리.월2자리.일2자리) */
	private String entryDate;
	/** 회원 등급 정보 (일반:G, 우수:S, 관리자:A) */
	private String grade;
	/** 회원(일반) 마일리지 정보 */
	private int mileage;
	/** 회원(우수) 담당자 정보 */
	private String manager;
	
	/**
	 * 기본생성자
	 */
	public Member() {
	}
	
	/**
	 * 필수데이터 초기화 생성자
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @param memberName 이름
	 * @param mobile 휴대폰
	 * @param email 이메일
	 */
	public Member(String memberId, String memberPw, String memberName,
			String mobile, String email) {
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.mobile = mobile;
		this.email = email;
	}
	
	/**
	 * 
	 * @param memberId
	 * @param memberPw
	 * @param memberName
	 * @param mobile
	 * @param email
	 * @param entryDate
	 * @param grade
	 * @param mileage
	 */
	public Member(String memberId, String memberPw, String memberName,
			String mobile, String email, String entryDate, String grade,
			int mileage) {
		this(memberId, memberPw, memberName, mobile, email);
		this.entryDate = entryDate;
		this.grade = grade;
		this.manager = manager;
	}

	
	/**
	 * 전체데이터 초기화 생성자
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @param memberName 이름
	 * @param mobile 휴대폰
	 * @param email 이메일
	 * @param entryDate 가입일
	 * @param grade 등급
	 * @param mileage 마일리지
	 * @param manager 담당자 이름
	 */
	public Member(String memberId, String memberPw, String memberName,
			String mobile, String email, String entryDate, String grade,
			int mileage, String manager) {
		this(memberId, memberPw, memberName, mobile, email);
		this.entryDate = entryDate;
		this.grade = grade;
		this.mileage = mileage;
		this.manager = manager;
	}
	
	public void setMemberId(String memberId) {
		if (isMemberId(memberId)) {
			this.memberId = memberId;
		} else {
			System.out.println("Error : 아이디는 6자리 ~ 20자리 이내입니다.");
		}
	}
	
	public String getMemberId() {
		return memberId;
	}
	
	/**
	 * 아이디 검증 메서드
	 * 검증규칙 : 아이디는 최소6자리 ~ 20자리 이내
	 * 
	 * @see java.lang.String#length()
	 * @param memberId
	 * @return 
	 */
	private boolean isMemberId(String memberId) {
		if (memberId != null) {
			int length = memberId.trim().length();
			if (length >= 6 && length <= 20) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the memberPw
	 */
	public String getMemberPw() {
		return memberPw;
	}

	/**
	 * @param memberPw the memberPw to set
	 */
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	/**
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the entryDate
	 */
	public String getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * @return the mileage
	 */
	public int getMileage() {
		return mileage;
	}

	/**
	 * @param mileage the mileage to set
	 */
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	/**
	 * @return the manager
	 */
	public String getManager() {
		return manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(memberId);
		builder.append(", ");
		builder.append(memberPw);
		builder.append(", ");
		builder.append(memberName);
		builder.append(", ");
		builder.append(mobile);
		builder.append(", ");
		builder.append(email);
		builder.append(", ");
		builder.append(entryDate);
		builder.append(", ");
		builder.append(grade);
		builder.append(", ");
		builder.append(mileage);
		builder.append(", ");
		builder.append(manager);
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
//	@Override
//	public String toString() {
//		return memberId + ", " + memberPw + ", " + memberName + ", " + mobile + ", " + email + ", " + entryDate + ", "
//				+ grade + ", " + mileage + ", " + manager;
//	}
	
	
}





















package work.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import work.model.dto.Member;

/**
 * ## JDBC 프로그래밍 절차 : 
 * 
 * 1. 드라이버 로딩 : 생성자 
 * 2. db 서버 연결 : factory.getConnection() : Connection
 * 
 * 3. sql 통로 개설
 * 4. sql 수행
 * 5. 결과처리
 * 
 * 6. 자원해제 : factory.close(rs, stmt, conn), factory.close(stmt, conn)
 *
 * ## JDBC Property : 환경설정
 * 1. driver = ""
 * 2. url = ""
 * 3. user = "hr"
 * 4. password = "tiger"
 * 
 * ## JDBC Exception Handling
 * 1. ClassNotFoundException
 * 2. SQLException
 * 
 * ## JDBC Driver 위치 
 * -- Oracle : ojdbc6.jar => jdk6.0 jdbc 구현 driver
 * 1. 컴퓨터시스템(공통) 사용 : jdk\jre\lib\ext> 폴더에 복사
 * 2. 프로젝트 단위 사용 : 별도의 classpath 추가 설정
 * 
 * ## javac / java 사용한 클래스를 찾아가는 검색 경로 : classpath
 * 1. rt.jar : Java SE (표준 api)
 * 2. jdk\jre\lib\ext> 폴더에 있는 *.jar
 * 3. set classpath=환경설정폴더지정한 파일
 * 4. classpath 환경변수 미설정시에는 현재 폴더(working directory)
 * 
 * 	## Singleton Pattern
 * 	-- 하나의 클래스에 대해서 하나의 인스턴스(객체) 설계
 * 	-- DAO 클래스에 적용 설계
 * 	-- 규칙:
 * 		1. private 생성자
 * 		2. public static 클래스이름 getInstance() { return instance; }
 * 		3. private static 클래스이름 instance = new 클래스이름();
 * 	
 * 	-- 클래스 사용
 * 		클래스이름 참조변수명 = 클래스이름.getInstance();
 */
 
/**
 * 회원 테이블 dao 클래스 : db access
 */
public class MemberDao {
	/** FactoryDao 공자에게 Connection, factory.close() 사용하기 위한 객체 */ 
	private static FactoryDao factory = FactoryDao.getInstance();
	
	/** Singleton Pattern : MemberDao 객체 */
	private static MemberDao instance = new MemberDao();
	
	/** Singleton Pattern : 기본생성자 */
	private MemberDao() {}

	/**  
	 * Singleton Pattern :
	 * @return MemberDao 인스턴스
	 */
	public static MemberDao getInstance() { 
		return instance; 
	}
	
	/**
	 * 로그인
	 * @param memberId 아이디
	 * @param memberPw 암호
	 * @return 회원 등급
	 */
	public String login(String memberId, String memberPw) {
		// sql 구문 주의사항 : 
		// 맨뒤에 ; 세미콜론 표기해서는 안됨, 
		// sql '문자열' 변환 작업, 
		// sql injection 보안이슈 발생야기
		String sql = "select grade from members where member_id='" + memberId + "' and member_pw='" + memberPw + "'";  
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 2. db 서버 연결 
			conn = factory.getConnection();
			//conn = DriverManager.factory.getConnection(url, user, password);
			
			// 3. sql 통로 개설
			stmt = conn.createStatement();
			
			// 4. sql 수행
			rs = stmt.executeQuery(sql);
			
			// 5. 결과처리
			if (rs.next()) {
				return rs.getString("grade");
			}
		} catch(SQLException e) {
			System.out.println("Error : 로그인 오류");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, stmt, conn);
		}
		return null;
	}
	
	/**
	 * 회원등록
	 * @param dto 등록 회원
	 * @return 등록 행수, 오류시 0
	 */
	public int insertMember(Member dto) {
		String sql = "insert into members values(?,?,?,?,?,?,?,?,?)";  
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getMemberId());
			pstmt.setString(2, dto.getMemberPw());
			pstmt.setString(3, dto.getMemberName());
			pstmt.setString(4, dto.getMobile());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getEntryDate());
			pstmt.setString(7, dto.getGrade());
			pstmt.setInt(8, dto.getMileage());
			pstmt.setString(9, dto.getManager());
		
//			int rows = pstmt.executeUpdate();
//			return rows;
			
			return pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("Error : 회원가입 오류");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return 0;
	}

	/**
	 * [관리자] 회원 다중 배치 등록
	 * @param list 등록 회원들 명단
	 * @return 다중배치등록 결과배열, 오류시 null
	 */
	public int[] insertListMember(ArrayList<Member> list) {
		String sql = "insert into members values(?,?,?,?,?,?,?,?,?)";  
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			for (Member dto : list) {
				pstmt.setString(1, dto.getMemberId());
				pstmt.setString(2, dto.getMemberPw());
				pstmt.setString(3, dto.getMemberName());
				pstmt.setString(4, dto.getMobile());
				pstmt.setString(5, dto.getEmail());
				pstmt.setString(6, dto.getEntryDate());
				pstmt.setString(7, dto.getGrade());
				pstmt.setInt(8, dto.getMileage());
				pstmt.setString(9, dto.getManager());
				// 실행하지 않고 스크립트 배치파일에 추가
				pstmt.addBatch();
			}
		
			// 스크립트 배치파일에 등록된 전체 수행
			return pstmt.executeBatch();
			
		} catch(SQLException e) {
			System.out.println("Error : 회원 다중 배치 등록 오류");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return null;
	}
	
	/**
	 * 전체회원 조회
	 * @return 전체회원
	 */
 	public ArrayList<Member> selectListMember() {
		String sql = "select * from members";  
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Member> list = new ArrayList<Member>();
		
		try {
			conn = factory.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Member dto = null;
			String memberId = null;
			String memberPw = null;
			String memberName = null;
			String mobile = null;
			String email = null;
			String entryDate = null;
			String grade = null;
			int mileage = 0;
			String manager = null;
			
			while(rs.next()) {
				memberId = rs.getString("member_id");
				memberPw = rs.getString("member_pw");
				memberName = rs.getString("member_name");
				mobile = rs.getString("mobile");
				email = rs.getString("email");
				entryDate = rs.getString("entry_date");
				grade = rs.getString("grade");
				mileage = rs.getInt("mileage");
				manager = rs.getString("manager");
				dto = new Member(memberId, memberPw, memberName, mobile, email, entryDate, grade, mileage, manager);
				list.add(dto);
			}
			
		} catch(SQLException e) {
			System.out.println("Error : 전체조회 오류");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return list;
	}
 	
 	/**
 	 * 등급별 회원조회
 	 * @param grade
 	 * @return 해당등급 전체회원, 미존재시 null
 	 */
 	public ArrayList<Member> selectListMemberByGrade(String grade) {
		String sql = "select * from members where grade=?";  
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Member> list = new ArrayList<Member>();
		
		try {
			conn = factory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, grade);
			rs = pstmt.executeQuery();
			
			Member dto = null;
			String memberId = null;
			String memberPw = null;
			String memberName = null;
			String mobile = null;
			String email = null;
			String entryDate = null;
			int mileage = 0;
			String manager = null;
			
			while(rs.next()) {
				memberId = rs.getString("member_id");
				memberPw = rs.getString("member_pw");
				memberName = rs.getString("member_name");
				mobile = rs.getString("mobile");
				email = rs.getString("email");
				entryDate = rs.getString("entry_date");
				grade = rs.getString("grade");
				mileage = rs.getInt("mileage");
				manager = rs.getString("manager");
				dto = new Member(memberId, memberPw, memberName, mobile, email, entryDate, grade, mileage, manager);
				list.add(dto);
			}
			
			return list;
			
		} catch(SQLException e) {
			System.out.println("Error : 전체조회 오류");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		
		return null;
	}
	
 	/**
 	 * 회원상세조회
 	 * @param memberId 회원 아이디
 	 * @return 회원, 오류시 null
 	 */
 	public Member selectOneMember(String memberId) {
		String sql = "select * from members where member_id=?";  
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			Member dto = null;
			String memberPw = null;
			String memberName = null;
			String mobile = null;
			String email = null;
			String entryDate = null;
			String grade = null;
			int mileage = 0;
			String manager = null;
			
			if(rs.next()) {
				memberPw = rs.getString("member_pw");
				memberName = rs.getString("member_name");
				mobile = rs.getString("mobile");
				email = rs.getString("email");
				entryDate = rs.getString("entry_date");
				grade = rs.getString("grade");
				mileage = rs.getInt("mileage");
				manager = rs.getString("manager");
				return new Member(memberId, memberPw, memberName, mobile, email, entryDate, grade, mileage, manager);
			}
			
		} catch(SQLException e) {
			System.out.println("Error : 전체조회 오류");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return null;
	}

 	/**
 	 * 회원 삭제
 	 * @param memberId 아이디
 	 * @return 삭제 행수, 오류시 0
 	 */
 	public int deleteMember(String memberId) {
		String sql = "delete from members where member_id=?";  
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			return pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("Error : 삭제 오류");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return 0;
	}

 	/**
 	 * 전체삭제 : 테스트 초기화
 	 * @return 삭제 행수, 오류시 0
 	 */
 	public int deleteAllMember() {
		String sql = "delete from members";  
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			pstmt = conn.prepareStatement(sql);
			return pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("Error : 전체삭제 오류");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return 0;
	}
 	
 	/**
 	 * 내정보 변경 (암호, 이름, 연락처, 이메일)
 	 * @param dto 변경회원
 	 * @return 변경 행수, 오류시 0
 	 */
 	public int updateMyInfo(Member dto) {
 		StringBuilder sql = new StringBuilder();
 		sql.append("update members set member_pw=?, member_name=?, mobile=?, email=? ");
		sql.append("where member_id=?");
		//String sql = "";  
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getMemberPw());
			pstmt.setString(2, dto.getMemberName());
			pstmt.setString(3, dto.getMobile());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getMemberId());
			return pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("Error : 내정보변경 오류");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return 0;
 	}

 	/**
 	 * 관리자 : 회원정보 변경
 	 * @param dto 변경회원
 	 * @return 변경행수, 오류시 0
 	 */
 	public int updateMember(Member dto) {
 		StringBuilder sql = new StringBuilder();
 		sql.append("update members set member_pw=?, member_name=?, mobile=?, email=? ");
 		sql.append(", entry_date=?, grade=?, mileage=?, manager=? ");
		sql.append("where member_id=?");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getMemberPw());
			pstmt.setString(2, dto.getMemberName());
			pstmt.setString(3, dto.getMobile());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getEntryDate());
			pstmt.setString(6, dto.getGrade());
			pstmt.setInt(7, dto.getMileage());
			pstmt.setString(8, dto.getManager());
			pstmt.setString(9, dto.getMemberId());
			return pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("Error : [관리자]회원정보변경 오류");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return 0;
 	}
 	
 	/**
 	 * 암호 변경
 	 * @param memberId 아이디
 	 * @param memberPw 암호
 	 * @param modifyMemberPw 변경암호
 	 * @return 성공시 변경행수, 실패시 0
 	 */
 	public int updatePassword(String memberId, String memberPw, String modifyMemberPw) {
 		StringBuilder sql = new StringBuilder();
 		sql.append("update members set member_pw=? ");
		sql.append("where member_id=? and member_pw=?");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, modifyMemberPw);
			pstmt.setString(2, memberId);
			pstmt.setString(3, memberPw);
			return pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("Error : 암호변경 오류");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return 0;
	}

 	/**
 	 * 암호변경 : 암호찾기에서 임시암호 발급받은 암호로 변경
 	 * @param memberId 아이디
 	 * @param modifyMemberPw 변경임시발급암호
 	 * @return 변경행수, 오류시 0
 	 */
 	public int updatePassword(String memberId, String modifyMemberPw) {
 		StringBuilder sql = new StringBuilder();
 		sql.append("update members set member_pw=? ");
		sql.append("where member_id=?");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, modifyMemberPw);
			pstmt.setString(2, memberId);
			return pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("Error : 임시발급 암호변경 오류");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return 0;
	}
 	
 	/**
 	 * 아이디 찾기
 	 * @param memberName 이름
 	 * @param mobile 연락처
 	 * @return 아이디, 오류시 null
 	 */
	public String selectMemberIdByMobile(String memberName, String mobile) {
 		StringBuilder sql = new StringBuilder();
 		sql.append("select member_id from members ");
		sql.append("where member_name=? and mobile=?");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, memberName);
			pstmt.setString(2, mobile);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("member_id");
			}
		} catch(SQLException e) {
			System.out.println("Error : 아아디찾기 오류");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		
		return null;
 	}

	/**
	 * 아이디 찾기
	 * @param memberName 이름
	 * @param email 이메일
	 * @return 아이디, 오류시 null
	 */
	public String selectMemberIdByEmail(String memberName, String email) {
 		StringBuilder sql = new StringBuilder();
 		sql.append("select member_id from members ");
		sql.append("where member_name=? and email=?");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, memberName);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("member_id");
			}
		} catch(SQLException e) {
			System.out.println("Error : 아아디찾기 오류");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		
		return null;
 	}
	
	/**
	 * 암호찾기(조회)
	 * @param memberId 아이디
	 * @param memberName 이름
	 * @param keywordType 검색타입(컬럼명 : mobile, email)
	 * @param keyword 검색어
	 * @return 존재시 true, 오류시 false (보안이슈:임시발급암호)
	 * @see updatePassword(String, String)
	 */
	public boolean selectMemberPw(String memberId, String memberName, String keywordType, String keyword) {
 		StringBuilder sql = new StringBuilder();
 		sql.append("select member_pw from members ");
		sql.append("where member_id=? and member_name=? and ");
		sql.append(keywordType);
		sql.append("=?");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberName);
			pstmt.setString(3, keyword);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				//주석처리 한 이유가 뭘까요?
				//return rs.getString("member_pw");
				return true;
			}
		} catch(SQLException e) {
			System.out.println("Error : 암호찾기 오류");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		
		return false;
	}
	
	/**
	 * 아이디 중복 조회
	 * @param memberId
	 * @return 존재시 이름, 미존재시 null
	 */
	public String selectIsExist(String memberId) {
 		StringBuilder sql = new StringBuilder();
 		sql.append("select member_name from members ");
		sql.append("where member_id=?");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("member_name");
			} 
		} catch(SQLException e) {
			System.out.println("Error : 아이디중복조회 오류");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return null;
	}
		
}

/*	
 	public int insertMember(Member dto) {
 		StringBuilder sql = new StringBuilder();
 		sql.append("update members set member_pw=? ");
		sql.append("where member_id=? and member_pw=?");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, );
			return pstmt.executeUpdate();
			rs = pstmt.executeQuery();
		} catch(SQLException e) {
			System.out.println("Error : 000 오류");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return 0;
	}
*/












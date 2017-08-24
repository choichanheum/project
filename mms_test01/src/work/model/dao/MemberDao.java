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
 * ## JDBC ���α׷��� ���� : 
 * 
 * 1. ����̹� �ε� : ������ 
 * 2. db ���� ���� : factory.getConnection() : Connection
 * 
 * 3. sql ��� ����
 * 4. sql ����
 * 5. ���ó��
 * 
 * 6. �ڿ����� : factory.close(rs, stmt, conn), factory.close(stmt, conn)
 *
 * ## JDBC Property : ȯ�漳��
 * 1. driver = ""
 * 2. url = ""
 * 3. user = "hr"
 * 4. password = "tiger"
 * 
 * ## JDBC Exception Handling
 * 1. ClassNotFoundException
 * 2. SQLException
 * 
 * ## JDBC Driver ��ġ 
 * -- Oracle : ojdbc6.jar => jdk6.0 jdbc ���� driver
 * 1. ��ǻ�ͽý���(����) ��� : jdk\jre\lib\ext> ������ ����
 * 2. ������Ʈ ���� ��� : ������ classpath �߰� ����
 * 
 * ## javac / java ����� Ŭ������ ã�ư��� �˻� ��� : classpath
 * 1. rt.jar : Java SE (ǥ�� api)
 * 2. jdk\jre\lib\ext> ������ �ִ� *.jar
 * 3. set classpath=ȯ�漳������������ ����
 * 4. classpath ȯ�溯�� �̼����ÿ��� ���� ����(working directory)
 * 
 * 	## Singleton Pattern
 * 	-- �ϳ��� Ŭ������ ���ؼ� �ϳ��� �ν��Ͻ�(��ü) ����
 * 	-- DAO Ŭ������ ���� ����
 * 	-- ��Ģ:
 * 		1. private ������
 * 		2. public static Ŭ�����̸� getInstance() { return instance; }
 * 		3. private static Ŭ�����̸� instance = new Ŭ�����̸�();
 * 	
 * 	-- Ŭ���� ���
 * 		Ŭ�����̸� ���������� = Ŭ�����̸�.getInstance();
 */
 
/**
 * ȸ�� ���̺� dao Ŭ���� : db access
 */
public class MemberDao {
	/** FactoryDao ���ڿ��� Connection, factory.close() ����ϱ� ���� ��ü */ 
	private static FactoryDao factory = FactoryDao.getInstance();
	
	/** Singleton Pattern : MemberDao ��ü */
	private static MemberDao instance = new MemberDao();
	
	/** Singleton Pattern : �⺻������ */
	private MemberDao() {}

	/**  
	 * Singleton Pattern :
	 * @return MemberDao �ν��Ͻ�
	 */
	public static MemberDao getInstance() { 
		return instance; 
	}
	
	/**
	 * �α���
	 * @param memberId ���̵�
	 * @param memberPw ��ȣ
	 * @return ȸ�� ���
	 */
	public String login(String memberId, String memberPw) {
		// sql ���� ���ǻ��� : 
		// �ǵڿ� ; �����ݷ� ǥ���ؼ��� �ȵ�, 
		// sql '���ڿ�' ��ȯ �۾�, 
		// sql injection �����̽� �߻��߱�
		String sql = "select grade from members where member_id='" + memberId + "' and member_pw='" + memberPw + "'";  
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 2. db ���� ���� 
			conn = factory.getConnection();
			//conn = DriverManager.factory.getConnection(url, user, password);
			
			// 3. sql ��� ����
			stmt = conn.createStatement();
			
			// 4. sql ����
			rs = stmt.executeQuery(sql);
			
			// 5. ���ó��
			if (rs.next()) {
				return rs.getString("grade");
			}
		} catch(SQLException e) {
			System.out.println("Error : �α��� ����");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, stmt, conn);
		}
		return null;
	}
	
	/**
	 * ȸ�����
	 * @param dto ��� ȸ��
	 * @return ��� ���, ������ 0
	 */
	public int insertMember(Member dto) {
		String sql = "insert into members values(?,?,?,?,?,?,?,?,?)";  
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("여기는 와?");
		
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
			
			return pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("Error : ȸ������ ����");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return 0;
	}

	/**
	 * [������] ȸ�� ���� ��ġ ���
	 * @param list ��� ȸ���� ���
	 * @return ���߹�ġ��� ����迭, ������ null
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
				// �������� �ʰ� ��ũ��Ʈ ��ġ���Ͽ� �߰�
				pstmt.addBatch();
			}
		
			// ��ũ��Ʈ ��ġ���Ͽ� ��ϵ� ��ü ����
			return pstmt.executeBatch();
			
		} catch(SQLException e) {
			System.out.println("Error : ȸ�� ���� ��ġ ��� ����");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return null;
	}
	
	/**
	 * ��üȸ�� ��ȸ
	 * @return ��üȸ��
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
			System.out.println("Error : ��ü��ȸ ����");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return list;
	}
 	
 	/**
 	 * ��޺� ȸ����ȸ
 	 * @param grade
 	 * @return �ش��� ��üȸ��, ������� null
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
			System.out.println("Error : ��ü��ȸ ����");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		
		return null;
	}
	
 	/**
 	 * ȸ������ȸ
 	 * @param memberId ȸ�� ���̵�
 	 * @return ȸ��, ������ null
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
			System.out.println("Error : ��ü��ȸ ����");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return null;
	}

 	/**
 	 * ȸ�� ����
 	 * @param memberId ���̵�
 	 * @return ���� ���, ������ 0
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
			System.out.println("Error : ���� ����");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return 0;
	}

 	/**
 	 * ��ü���� : �׽�Ʈ �ʱ�ȭ
 	 * @return ���� ���, ������ 0
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
			System.out.println("Error : ��ü���� ����");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return 0;
	}
 	
 	/**
 	 * ������ ���� (��ȣ, �̸�, ����ó, �̸���)
 	 * @param dto ����ȸ��
 	 * @return ���� ���, ������ 0
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
			System.out.println("Error : ���������� ����");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return 0;
 	}

 	/**
 	 * ������ : ȸ������ ����
 	 * @param dto ����ȸ��
 	 * @return �������, ������ 0
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
			System.out.println("Error : [������]ȸ���������� ����");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return 0;
 	}
 	
 	/**
 	 * ��ȣ ����
 	 * @param memberId ���̵�
 	 * @param memberPw ��ȣ
 	 * @param modifyMemberPw �����ȣ
 	 * @return ������ �������, ���н� 0
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
			System.out.println("Error : ��ȣ���� ����");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return 0;
	}

 	/**
 	 * ��ȣ���� : ��ȣã�⿡�� �ӽþ�ȣ �߱޹��� ��ȣ�� ����
 	 * @param memberId ���̵�
 	 * @param modifyMemberPw �����ӽù߱޾�ȣ
 	 * @return �������, ������ 0
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
			System.out.println("Error : �ӽù߱� ��ȣ���� ����");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return 0;
	}
 	
 	/**
 	 * ���̵� ã��
 	 * @param memberName �̸�
 	 * @param mobile ����ó
 	 * @return ���̵�, ������ null
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
			System.out.println("Error : �ƾƵ�ã�� ����");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		
		return null;
 	}

	/**
	 * ���̵� ã��
	 * @param memberName �̸�
	 * @param email �̸���
	 * @return ���̵�, ������ null
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
			System.out.println("Error : �ƾƵ�ã�� ����");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		
		return null;
 	}
	
	/**
	 * ��ȣã��(��ȸ)
	 * @param memberId ���̵�
	 * @param memberName �̸�
	 * @param keywordType �˻�Ÿ��(�÷��� : mobile, email)
	 * @param keyword �˻���
	 * @return ����� true, ������ false (�����̽�:�ӽù߱޾�ȣ)
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
				//�ּ�ó�� �� ������ �����?
				//return rs.getString("member_pw");
				return true;
			}
		} catch(SQLException e) {
			System.out.println("Error : ��ȣã�� ����");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		
		return false;
	}
	
	/**
	 * ���̵� �ߺ� ��ȸ
	 * @param memberId
	 * @return ����� �̸�, ������� null
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
			System.out.println("Error : ���̵��ߺ���ȸ ����");
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
			System.out.println("Error : 000 ����");
			e.printStackTrace(); 
		} finally {
			factory.close(rs, pstmt, conn);
		}
		return 0;
	}
*/












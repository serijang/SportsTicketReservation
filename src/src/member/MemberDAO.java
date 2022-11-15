package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconn.DBConnect;

public class MemberDAO {
	
	private DBConnect dbconn;
	
	public MemberDAO() {
		dbconn = DBConnect.getInstance();
	}
	

	//id에 해당하는 회원 정보 select
	public MemberVO selectMember(String id) {
		String sql = "select * from member where memId =?";
		Connection conn = dbconn.conn();
		MemberVO list = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				list = new MemberVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}//selectMember
	
	
	//회원 로그인/관리자 로그인 판별
	public void checkM(String id) {
		String sql = "select * from member where memId =?";
		Connection conn = dbconn.conn();
		MemberVO list = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				list = new MemberVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getInt(6), rs.getInt(7));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}//checkM
	
	
	//id에 해당하는 회원 정보 update
	public void updateMember(MemberVO vo, String id) {
		String sql = "update member set pwd=? email=? addr=? where id =?";
		Connection conn = dbconn.conn();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getPwd());
			ps.setString(2, vo.getEmail());
			ps.setString(3, vo.getAddr());
			ps.setString(4, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}//updateMember()
	
	//id에 해당하는 회원 정보 delete
	public void deleteMember(String id) {
		String sql = "delete from member where id =?";
		Connection conn = dbconn.conn();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}//deleteMember
	
	//회원 정보 insert
	public void insertMember(MemberVO vo) {
		String sql = "insert into member values(?,?,?,?,0)";
		Connection conn = dbconn.conn();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getMemId());
			ps.setString(2, vo.getPwd());
			ps.setString(3, vo.getEmail());
			ps.setString(4, vo.getAddr());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}//insertMember
	
}//MemberDAO
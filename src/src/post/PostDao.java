package post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.DBConnect;

public class PostDao {
	private DBConnect dbconn;
	
	public PostDao() {
		dbconn = DBConnect.getInstance();
	}

	// 데이터 삽
	public void insert(PostVo vo) {
		String sql = "insert into post values(post_seq.nextval, ?, ?, ?, sysdate, sysdate, ?)";
		Connection conn = dbconn.conn();  // db에 정보입력해서 연결 
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);  // sql구문을 실행시킬 수 있는 객체 
			pstmt.setString(1, "1");  // ?의 순서에 따라 인덱스 설정 -> memId가 없으면 ORA-02291 뜸. db에저장한 memId와 같은 설정값을 넣어줘야 정상작동!
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setInt(4, 0);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 전체 글 목록 조회 
	public ArrayList<PostVo> selectAll() {
		ArrayList<PostVo> list = new ArrayList<>();
		String sql = "select * from post order by post_seq asc";
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new PostVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getInt(7)));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	// 게시글 선택
	public PostVo select(int post_seq) {
		String sql = "select * from post where post_seq=?";
		Connection connection = dbconn.conn();
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1,  post_seq);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {  // rs.next()??
				return new PostVo(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	// 게시글 수정 
	public void update(PostVo vo) {
		String sql = "update post set title=?, content=?, last_modified_date = sysdate where post_seq=?";
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3,  vo.getpost_seq());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	// 게시글 삭제
	public void delete(int post_seq) {
		String sql = "delete from post where post_seq = ?";
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post_seq);
			pstmt.execute();
			System.out.println(post_seq + "번 게시글을 삭제 완료했습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}

package post;

import java.sql.Date;

public class PostVo {
	int post_seq;
	String memId;
	String title;
	String content;
	Date created_date;
	Date last_modified_date;
	int hits;
	
	// 생성
	public PostVo() {
	}
	
	public PostVo(int post_seq, String memId, String title, String content, Date created_date, Date last_modified_date, int hits) {
		super();
		this.post_seq = post_seq;
		this.memId = memId;
		this.title = title;
		this.content = content;
		this.created_date = created_date;
		this.last_modified_date = last_modified_date;
		this.hits = hits;
	}
	
	// getter, setter 
	public int getpost_seq() {
		return post_seq;
	}
	public void setpost_seq(int post_seq) {
		this.post_seq = post_seq;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getLast_modified_date() {
		return last_modified_date;
	}
	public void setLast_modified_date(Date last_modified_date) {
		this.last_modified_date = last_modified_date;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	
	@Override
	public String toString() {
		return "[게시글 번호 = " + post_seq + ", 멤버id = " + memId + ", 제목 = " + title + ", 내용 = " + content + ", 생성일자 = " 
				+ created_date + ", 수정일자 = " + last_modified_date + ", 조회수 = " + hits + "]";
	}
}



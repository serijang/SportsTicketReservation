package member;

public class MemberVO {

	private String memId;
	private String name;
	private String pwd;
	private String email;
	private String addr;
	private int blackList;
	private int master; 
	
	public MemberVO() {}
	public MemberVO(String memId, String name, String pwd, String email, String addr, int blackList, int master) {
		super();
		this.memId = memId;
		this.name = name;
		this.pwd = pwd;
		this.email = email;
		this.addr = addr;
		this.blackList = blackList;
		this.master = master;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getBlackList() {
		return blackList;
	}
	public void setBlackList(int blackList) {
		this.blackList = blackList;
	}
	public int getMaster() {
		return master;
	}
	public void setMaster(int master) {
		this.master = master;
	}
	
	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", name=" + name + ", pwd=" + pwd + ", email=" + email + ", addr=" + addr
				+ ", blackList=" + blackList + ", master=" + master + "]";
	}
}// MemberVO
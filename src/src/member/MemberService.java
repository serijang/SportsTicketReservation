package member;

import java.util.Scanner;

public class MemberService {
	
	private MemberDAO dao;
	public static String loginId = "1";
	
	public MemberService() {
		dao = new MemberDAO();
	}
	
	//로그인
	public String login(Scanner sc) {
		System.out.println("(1)로그인");
		System.out.println("============");
		System.out.print("아이디: ");
		String id = sc.next();
		System.out.print("비밀번호: ");
		String pwd = sc.next();
		
		MemberVO result = dao.selectMember(id);
		if(result == null) {
			System.out.println("없는 아이디입니다.");
		} else {
			if (pwd.equals(result.getPwd())){
				System.out.println();
				System.out.println("로그인 성공");
				System.out.println();
				loginId = id;
			}
			return loginId;
		}
		return null;
	}//login()

	
	//ID중복체크 , 없으면 TRUE
	public boolean checkId(String id) {
		return dao.selectMember(id) == null;
	}//checkId()
	
	
	//회원가입
	public void join(Scanner sc) {
		System.out.println("(2)회원가입");
		System.out.println("============");
		
		boolean flag = false;
		String id = null;
		
		while(!flag) {
			System.out.print("아이디: ");
			id = sc.next();
			flag = checkId(id);
			
			if(!flag) {
				System.out.println("중복된 아이디입니다.");
			}
		}
		
		System.out.print("이름: ");
		String name = sc.next();
		System.out.print("비밀번호: ");
		String pwd = sc.next();
		System.out.print("이메일: ");
		String email = sc.next();
		System.out.print("주소: ");
		String addr = sc.next();
		
		dao.insertMember(new MemberVO(id, name, pwd, email, addr, 0, 0));
	}//join()
	
	
	//회원탈퇴 
	public void out(Scanner sc) {
		System.out.println("회원탈퇴");
		System.out.println("============");
		System.out.print("탈퇴하시겠습니까? (1)네 (2)아니오 : ");
		int num = sc.nextInt();
		
		if(num == 1) {
			dao.deleteMember(loginId);
			loginId = null;
		} else {
			System.out.println("메인으로 돌아갑니다.");
		}
	}//out()
	
	
	//로그아웃
	public void logout(Scanner sc) {
		System.out.println("로그아웃");
		System.out.println("============");
		System.out.print("로그아웃 하시겠습니까? (1)네 (2)아니오 : ");
		int num = sc.nextInt();
		
		if(num == 1) {
			loginId = null;
		} else {
			System.out.println("메인으로 돌아갑니다.");
		}
	}//logout()
	
	
	//예매한 경기 확인
	public void checkRes() {
		System.out.println("예매한 경기 목록 확인");
		System.out.println("============");
		
		//목록확인메서드 
		
		System.out.println("============");
	}//checkRes()
	
	//예매한 경기 취소 
	public void deleteRes(Scanner sc) {
		System.out.println("예매한 경기 취소");
		System.out.println("============");
		System.out.println("경기 목록");
		
		//목록확인메서드 
		
		System.out.println("============");
		System.out.println("취소할 경기 번호: ");
		int num = sc.nextInt();
		
		//경기취소메서드
		
		System.out.println("경기가 취소되었습니다.");
	}//deleteRes
	
	//매점 결제 내역 확인 
	public void checkStore() {
		System.out.println("매점 결제 내역 확인");
		System.out.println("============");
		
		//목록확인메서드 
		
		System.out.println("============");
	}//checkStore
	
	//관리자 쪽지 확인
	
	//내가 쓴 게시글 확인
	public void checkPost() {
		System.out.println("내가 쓴 게시글 확인");
		System.out.println("============");
		
		//목록확인메서드 
		
		System.out.println("============");
	}//checkPost
	
}//MemberService

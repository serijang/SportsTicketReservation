package post;

import java.util.Scanner;

import member.MemberService;

public class Menu {
	private PostService postService;
	private MemberService memberService;

	public Menu() {
		postService = new PostService();
	}
	
	Scanner sc = new Scanner(System.in);
	
	public void run() {
		Boolean flag = true;
		while(flag) {
			int i = 0;
			System.out.println("1 : 게시글 목록 조회  2: 글 작성  3: 글 수정 4: 글 삭제 5: 종료 ");
			i = sc.nextInt();
			switch (i) {
			case 1:
				postService.getAll();
				break;
			case 2:
				postService.addPost();
				break;
			case 3:
				postService.editPost();
				break;
			case 4:
				postService.deletePost();
				break;
			case 5:
				System.out.println("프로그램을 종료합니다.");
				flag = false;
				break;
				
			}
		}
	}
	
//	public void mrun() {
//		boolean flag = true;
//		int m = 0;
//		while (flag) {
//				System.out.println("1.로그인 2.회원가입 3.종료");
//			m = sc.nextInt();
//			switch (m) {
//			case 1:
//				memberService.login();
//				break;
//			case 2:
//				memberService.join();
//				break;
//			case 3:
//				flag = false;
//				break;
//			case 4:
//				run();
//				break;
//			case 5:
//				break;
//			}
//		}
//	}
}

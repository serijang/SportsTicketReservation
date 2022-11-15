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
		while (flag) {
			int i = 0;
			System.out.println("1: 게시글 목록 조회 \n2: 글 상세 확인 \n3: 글 작성 \n4: 글 수정 \n5: 글 삭제 \n6: 종료 ");
			i = sc.nextInt();
			switch (i) {
			case 1:
				postService.getAll();
				break;
			case 2:
				postService.getPostDetail();
				break;
			case 3:
				postService.addPost();
				break;
			case 4:
				postService.editPost();
				break;
			case 5:
				postService.deletePost();
				break;
			case 6:
				System.out.println("프로그램을 종료합니다.");
				flag = false;
				break;

			}
		}
	}
}

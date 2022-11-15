package post;

import java.util.ArrayList;
import java.util.Scanner;

import member.MemberService;

public class PostService {
	private PostDao dao;
	
	public PostService() {
		dao = new PostDao();
	}

	Scanner sc = new Scanner(System.in);
	
	public void addPost() {
		System.out.println("글 작성을 시작합니다.");
		System.out.println("id?");
		String id = sc.nextLine();
		System.out.println("제목을 입력해주세요.");
		String title = sc.nextLine();
		System.out.println("내용을 입력해주세요.");
		System.out.print("작성을 멈추려면 /stop 을 입력해주세요.):");
		StringBuilder buf = new StringBuilder();
		while(true) {
			String str = sc.nextLine();
			if (str.startsWith("/stop")) {
				break;
			}
			buf.append(str + "\n");
		}
		String content = buf.toString();
		dao.insert(new PostVo(0, id, title, content, null, null, 0));
	}
	
	public void getAll() {
		System.out.println("게시글 전체 목록을 조회합니다.");
		ArrayList<PostVo> list = dao.selectAll();
		for (PostVo vo : list) {
			System.out.println(vo);
		}
	}
	
	public void editPost() {
		System.out.println("게시글을 수정합니다.");
		System.out.println("수정할 게시글의 번호를 입력해주세요 : ");
		int post_seq = sc.nextInt();
		
		PostVo vo = dao.select(post_seq);
		if (vo == null) {
			System.out.println("해당하는 게시글이 존재하지 않습니다. 메뉴로 돌아갑니다.");
		} else {
			if(MemberService.loginId.equals(vo.getMemId())) {
				System.out.println(vo);
				System.out.println("변경할 제목을 입력해주세요 : ");
				sc.nextLine();
				String title = sc.nextLine();
				System.out.println("변경할 내용을 입력해주세요 (멈추려면 /stop) : ");
				StringBuilder buf = new StringBuilder();
				while(true) {
					String content = sc.nextLine();
					if(content.startsWith("/stop")) {
						break;
					}
					buf.append(content + "\n");
				}
				String content = buf.toString();
				dao.update(new PostVo(post_seq, vo.getMemId(), title, content, null , null, 0));
			} else {
				System.out.println("다른 사용자가 작성한 게시글은 수정이 불가능 합니다.");
			}
		}	
	}
	
	public void deletePost() {
		System.out.println("게시글을 삭제합니다.");
		System.out.println("삭제할 게시글의 번호를 입력해주세요 : ");
		int post_seq = sc.nextInt();
		PostVo vo = dao.select(post_seq);
		if(vo == null) {
			System.out.println("해당하는 게시글이 존재하지 않습니다. 메뉴로 돌아갑니다.");
		} else {
			if(MemberService.loginId.equals(vo.getMemId())) {
				dao.delete(post_seq);
			} else {
				System.out.println("다른 사용자가 작성한 게시글은 삭제가 불가능 합니다.");
			}
		}
	}
	
}

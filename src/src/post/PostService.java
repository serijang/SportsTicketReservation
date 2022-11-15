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

	// 게시글 작성 
	public void addPost() {
		if (MemberService.loginId != null) {
			String id = MemberService.loginId;
			System.out.println("글 작성을 시작합니다.");
			System.out.println("제목을 입력해주세요.");
			String title = sc.nextLine();
			System.out.println("내용을 입력해주세요.");
			System.out.print("- 작성을 멈추려면 /s 을 입력해주세요 :");
			StringBuilder buf = new StringBuilder();
		while(true) {
			String str = sc.nextLine();
			if (str.startsWith("/s")) {
				break;
			}
			buf.append(str);
		}
		String content = buf.toString();
		dao.insert(new PostVo(0, id, title, content, null, null, 0));
		} else {
			System.out.println("로그인이 필요한 서비스입니다.");
		}
	}
	
	// 게시글 전체 목록 조회 
	public void getAll() {
		System.out.println("게시글 전체 목록을 조회합니다.");
		ArrayList<PostVo> list = dao.selectAll();
		for (PostVo vo : list) {
			System.out.println(vo);
		}
	}
	
	// 작성자 아이디로 게시글 전체 검색
	public void getForMemIdAll() {
		System.out.println("검색할 작성자의 아이디를 입력해주세요 : ");
		String memId = sc.next();
		ArrayList<PostVo> list = dao.selectForMemId(memId);
		if(list.isEmpty()) {
			System.out.println("해당하는 작성자의 게시글이 존재하지 않습니다. 다시 입력해주세요.");
			getForMemIdAll();
		} else {
			for(PostVo vo : list) {
				System.out.println(vo);
			}
		}
	}
	
	// 게시글 상세 확인 
	public void getPostDetail() {
		System.out.println("게시글을 상세 확인 합니다.");
		System.out.println("상세확인 할 게시글 번호를 입력해주세요 : ");
		int post_seq = sc.nextInt();
		
		PostVo vo = dao.select(post_seq);
		if(vo == null) {
			System.out.println("해당하는 게시글이 존재하지 않습니다. 다시 입력해주세요.");
			getPostDetail();
		} else {
			dao.updateHits(vo);
			System.out.println("- 제목 : " + vo.getTitle() + "\n- 내용 : " + vo.getContent() +
			"\n- 작성자 : " + vo.getMemId()+ "\n- 생성일 : " + vo.getCreated_date() + "\n- 수정일 : " + vo.getLast_modified_date() + "\n- 조회수 : " + vo.getHits() + "\n"
			);
		}
	}
	
	// 게시글 수정 
	public void editPost() {
		System.out.println("게시글을 수정합니다.");
		System.out.println("수정할 게시글의 번호를 입력해주세요 : ");
		int post_seq = sc.nextInt();
		
		PostVo vo = dao.select(post_seq);
		if (vo == null) {
			System.out.println("해당하는 게시글이 존재하지 않습니다. 다시 입력해주세요.");
			editPost();
		} else {
			if(MemberService.loginId.equals(vo.getMemId())) {
				System.out.println(vo);
				System.out.println("변경할 제목을 입력해주세요 : ");
				sc.nextLine();
				String title = sc.nextLine();
				System.out.println("변경할 내용을 입력해주세요 (멈추려면 /s) : ");
				StringBuilder buf = new StringBuilder();
				while(true) {
					String content = sc.nextLine();
					if(content.startsWith("/s")) {
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
	
	// 게시글 삭제 
	public void deletePost() {
		System.out.println("게시글을 삭제합니다.");
		System.out.println("삭제할 게시글의 번호를 입력해주세요 : ");
		int post_seq = sc.nextInt();
		PostVo vo = dao.select(post_seq);
		if(vo == null) {
			System.out.println("해당하는 게시글이 존재하지 않습니다. 다시 입력해주세요.");
			deletePost();
		} else {
			if(MemberService.loginId.equals(vo.getMemId())) {
				dao.delete(post_seq);
			} else {
				System.out.println("다른 사용자가 작성한 게시글은 삭제가 불가능 합니다.");
			}
		}
	}
	
}

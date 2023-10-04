package com.sample.app.controller.post;

import com.sample.app.dao.PostDao;
import com.sample.app.vo.Post;
import com.sample.model2.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class InsertController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//세션에서 로그인된 사용자 정보를 조회
		//로그인도니 사용자 정보가 존재하지 않으면 로그인폼을 요청하는 재요청 URL를 반환
		HttpSession session = request.getSession();
		if (session.getAttribute("loginUserId")== null) {
			return "redirect:/app/user/loginform.hta?error=deny";
		}
		String loginUserId = (String) session.getAttribute("loginUserId");
		
		//요청 파라미터 값을 조회
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//Post객체를 생성해서 게시글 제목,내용,로그인한 사용자 아이디를 대입
		Post post = new Post();
		post.setTitle(title);
		post.setUserId(loginUserId);
		post.setContent(content);
		
		//PostDao객체의 insertPost 메소드를 호출시켜 게시글 정보를 저장
		PostDao postDao = PostDao.getInstance();
		postDao.insertPost(post);
		
		//게시글 목록을 요청하는 재요청 URL을 반환
		return "redirect:list.hta";
	}
}

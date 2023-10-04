package com.sample.app.controller.post;

import com.sample.app.dao.CommentDao;
import com.sample.app.dao.PostDao;
import com.sample.app.vo.Comment;
import com.sample.app.vo.Post;
import com.sample.model2.Controller;
import com.sample.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/*
 *세션에서 사용자 정보 조회
 *사용자 정보가 없으면 로그인폼 요청하는 재요청 URL 반환
 *요청파라미터 값을 조회
 *Comment 객체를 생성해서 작성자 아이디,내용,게시글 정보 저장
 *게시글 번호로 PostDao의 getPostByNo를 실행해 게시글 정보를 조회
 *게시글 정보의 댓글 갯수를 1증가시킨다
 *PostDao의 updatePost를 실행시켜 변경된 정보를 반영
 *CommentDao 객체를 insertComment를 호출해 댓글 정보를 저장 
 *
*/

public class InsertCommentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 세션 객체에서 로그인된 사용자 정보를 조회한다.
		HttpSession session = request.getSession();
		String loginUserId = (String) session.getAttribute("loginUserId");
		
		if(loginUserId == null) {
		 return "redirect:/app/user/loginform.hta?error=deny";	
		}
		
		//요청 파리미터 값 조회
		int postNo = StringUtils.stringToInt(request.getParameter("postNo"));
		String content = request.getParameter("content");
		
		//Comment 객체를 생성해 작성자 아이디,내용,게시글 정보 저장
		Comment comment = new Comment();
		comment.setUserId(loginUserId);
		comment.setContent(content);
		comment.setPostNo(postNo);
		
		CommentDao cDao = CommentDao.getInstance();
		PostDao postDao = PostDao.getInstance();
		
		//ComeentDao의 insertComeent 를 호출해 댓글정보에 저장
		cDao.insertComment(comment);
		
		Post post =  postDao.getPostByNo(postNo);
		post.setCommentCount(post.getCommentCount() + 1);
		
		// PostDao 객체의 updatePost (Post post)를 실행해서 변경된 게시글 정보로 갱신시킨다.
		postDao.updatePost(post);
		
		
		return "redirect:detail.hta?postNo=" + postNo;
	}
}

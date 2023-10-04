package com.sample.app.controller.post;

import java.util.List;

import com.sample.app.dao.CommentDao;
import com.sample.app.dao.PostDao;
import com.sample.app.dto.CommentListDto;
import com.sample.app.dto.PostDetailDto;
import com.sample.model2.Controller;
import com.sample.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DetailController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//요청 파라미터 값을 조회
		int postNo = StringUtils.stringToInt(request.getParameter("postNo"));
		
		PostDao postDao = PostDao.getInstance();
		CommentDao commentDao = CommentDao.getInstance();
		
		//게시글 번호에 해당하는 게시글 상세정보 조회
		PostDetailDto dto = postDao.getPostDetailByNo(postNo);
		
		// 게시글 번호에 해당하는 댓글 목록 정보를 조회
		List<CommentListDto> commentListDtos = commentDao.getCommentsByPostNo(postNo);
		
		request.setAttribute("post", dto);
		request.setAttribute("comments", commentListDtos);
		
		return "post/detail.jsp";
	}
}

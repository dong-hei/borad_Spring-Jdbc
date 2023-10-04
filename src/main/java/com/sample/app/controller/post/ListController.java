package com.sample.app.controller.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sample.app.dao.PostDao;
import com.sample.app.dto.PostListDto;
import com.sample.model2.Controller;
import com.sample.util.Pagination;
import com.sample.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  
		//요청 파라미터 값을 조회
		int currentPage = StringUtils.stringToInt(request.getParameter("page"),1);
		
		//총 게시글 갯수를 조회한다.
		PostDao postDao = PostDao.getInstance();
		int totalRows =  postDao.getTotalRows();
		
		Pagination pagination = new Pagination(currentPage, totalRows);
		
		//게시글 목록 조회에 필요한 정보를 저장하는 Map객체를 생성
		Map<String, Object> param = new HashMap<>();
		param.put("begin", pagination.getBegin());
		param.put("end", pagination.getEnd());

		//PostDao의 getPosts(Map<String, Object> param)메소드를 실행시켜서 게시글 목록을 조회
		List<PostListDto> postListDtos = postDao.getPosts(param);
		
		//요청객체의 속성으로 게시글 목록정보를 저장
		request.setAttribute("posts", postListDtos);
		
		//요청객체의 속성으로 페이징처리 정보를 저장
		request.setAttribute("pagination", pagination);
		
		return "post/list.jsp";
	}
}

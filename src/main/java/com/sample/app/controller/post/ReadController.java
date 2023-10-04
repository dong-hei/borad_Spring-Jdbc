package com.sample.app.controller.post;

import com.sample.app.dao.PostDao;
import com.sample.app.vo.Post;
import com.sample.model2.Controller;
import com.sample.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReadController implements Controller {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		int postNo = StringUtils.stringToInt(request.getParameter("postNo"));
		
		PostDao postDao = PostDao.getInstance();
		
		Post post = postDao.getPostByNo(postNo);
		
		post.setReadCount(post.getReadCount() + 1);
		
		postDao.updatePost(post);
		return "redirect:detail.hta?postNo=" + postNo;
		
	}
}

package com.sample.app.controller.post;

import com.sample.app.dao.PostDao;
import com.sample.app.vo.Post;
import com.sample.model2.Controller;
import com.sample.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ModifyController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null) {
			return "redirect:/app/user/loginform.hta?error=deny";
		}
		
		int postNo = StringUtils.stringToInt(request.getParameter("postNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		PostDao postDao = PostDao.getInstance();
		Post post = postDao.getPostByNo(postNo);
		
		if(!post.getUserId().equals(loginUserId)) {
			return "redirect:detail.hta?postNo=" + postNo + "&error=post";
		}
		
		post.setTitle(title);
		post.setContent(content);
		postDao.updatePost(post);
		return null;
	}
}

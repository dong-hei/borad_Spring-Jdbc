package com.sample.app.controller.user;

import com.sample.app.dao.UserDao;
import com.sample.app.vo.User;
import com.sample.model2.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterSuccessController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("id");
		
		UserDao userDao = UserDao.getInstance();
		User user = userDao.getUserById(userId);
		
		request.setAttribute("username", user.getName());
		return "/user/register-success.jsp";
	}
}
	
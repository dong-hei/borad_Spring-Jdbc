package com.sample.app.controller.user;

import com.sample.app.dao.UserDao;
import com.sample.app.vo.User;
import com.sample.model2.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		//동일한아이디가 존재하는 지 확인
		UserDao userDao = UserDao.getInstance();
		User savedUser = userDao.getUserById(id);
		if (savedUser != null) {
		return "redirect:registerform.hta?error=fall";
		}
		savedUser = userDao.getUserByEmail(email);
		if(savedUser != null) {
			return "redirect:registerform.hta?error=fall";
		}
		//User객체 생성해 사용자 정보 저장
		User user = new User();
		user.setName(name);
		user.setId(id);
		user.setPassword(password);
		user.setEmail(email);
		
		//userDao의 insertUser를 호출해 테이블에 저장
		
		userDao.insertUser(user);
		return "redirect:registerSuccess.hta?id="+id;
	}
}

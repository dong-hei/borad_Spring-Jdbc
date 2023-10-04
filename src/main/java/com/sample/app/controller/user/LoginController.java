package com.sample.app.controller.user;

import com.sample.app.dao.UserDao;
import com.sample.app.vo.User;
import com.sample.model2.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/*
 * 요청 파라미터 id pw
 * 반환값 redirect: ../home.hta
 * 			   redirect:loginform.hta?error=fail
 * 요청처리 내용
 * 			요청 파라미터로 전달받은 id pw에 대한 인증절차를 수행
 * 			인증을 통과하지 못하면 재요청 URI(loginform.hta?error=fail)을 반환
 * 			인증을 통과하면 세션에 인증된 사용자 정보를 저장
 * 			재요청 URL을 반환
*/
import jakarta.servlet.http.HttpSession;




public class LoginController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String password =request.getParameter("password");
		
		UserDao userDao = UserDao.getInstance(); 
		User savedUser = userDao.getUserById(id);
		
		if(savedUser == null) {
			return "redirect:loginform.hta?error=fail";
		}
		if (!savedUser.getPassword().equals(password)) {
			return "redirect:loginform.hta?error=fail";
		}
		// 사용자 인증이 완료되면 사용자정보를 세션객체에 저장
		HttpSession session = request.getSession();
		session.setAttribute("username",savedUser.getName());
		session.setAttribute("loginUserId",savedUser.getId());
		
		return "redirect:../home.hta";
	}
}

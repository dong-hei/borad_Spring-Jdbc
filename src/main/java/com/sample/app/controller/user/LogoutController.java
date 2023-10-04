package com.sample.app.controller.user;

import com.sample.model2.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * request.getSession()과 request.getSession(true)
		 * HttpSession객체를 반환, 요청 객체에 세션아이디가 없으면 새로운 HttpSession 객체를 생성해 반환한다.
		 * 요청객체에 세션아이디가 있지만, 세션아이디와 일치하는 세션객체가 존재하지 않으면 새로운 HttpSession객체를 
		 * 요청객체에 세션아이디가 있고,세션아이디와 일치하는 세션객체는 
		*/
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/app/home.hta";
	}
}

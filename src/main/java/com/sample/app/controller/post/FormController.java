package com.sample.app.controller.post;

import com.sample.model2.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class FormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//세션에서 로그인된 사용자 정보를 조회
		//로그인도니 사용자 정보가 존재하지 않으면 로그인폼을 요청하는 재요청 URL를 반환
		HttpSession session = request.getSession();
		if (session.getAttribute("loginUserId")== null) {
			return "redirect:/app/user/loginform.hta?error=deny";
		}
		//세션에 로그인된 사용자 정보가 존재하면 form.jsp 리턴
		return "post/form.jsp";
	}
}
 
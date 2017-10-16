package com.ansel.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ansel.bean.User;
import com.ansel.service.IUserService;

@Controller
public class Register {
	@Resource
	private IUserService userService;

	@RequestMapping(value = "/register")
	public ModelAndView UserRegister() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", new User());
		mv.setViewName("register");
		return mv;
	}

	@RequestMapping("/verify")
	public ModelAndView processUser(HttpServletRequest request, HttpServletResponse response, @Valid User user,
			BindingResult result) throws IOException {
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		if (result.hasErrors()) {
//			表单验证有误
			mv.setViewName("register");
		} else {
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if (this.userService.checkUser(username) != null) {
//				用户名存在
				out.println("<script language='javascript'>");
				out.println("alert('用户名存在！')");
				out.println("</script>");
				out.flush();
				mv = new ModelAndView("forward:/register");
			} else {
//				插入成功
				User newUser = new User(username, email, password);
				this.userService.insertNewUser(newUser);
				mv.setViewName("register_success");
			}
		}
		return mv;
	}

}

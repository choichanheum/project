package work.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import work.model.dto.Member;
import work.model.service.MemberService;

/**
 * Servlet implementation class MemberController2
 */
@WebServlet("/addMember.do")
public class MemberController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MemberService service = new MemberService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/*
		 * 사전 검사 생략
		 */
		request.setCharacterEncoding("UTF-8");
		
		String memberId 	= request.getParameter("memberId"); 
		String memberPw		= request.getParameter("memberPw"); 
		String memberName	= request.getParameter("memberName"); 
		String mobile		= request.getParameter("mobile"); 
		String email		= request.getParameter("email"); 
		
		Member dto = new Member(memberId, memberPw, memberName, mobile, email);
		
		int rows = service.addMember(dto);
		
		RequestDispatcher dispatcher = null;
		if(rows <1) {
			
			dispatcher = request.getRequestDispatcher("index.jsp");
		} else {
			
			dispatcher = request.getRequestDispatcher("login.jsp");
		}
		
		dispatcher.forward(request, response);
		
	}

}

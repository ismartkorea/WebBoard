<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.webboard.common.CommUtil" %>
<%@ page import = "com.webboard.BoardVo" %>
<%@ page import = "com.webboard.BoardManager" %>
<%
	request.setCharacterEncoding("UTF-8");

	// 파라미터 조회.
	int pNo = CommUtil.intCheckNull((String) request.getParameter("no"));
	String pTitle = CommUtil.strCheckNull((String) request.getParameter("title"));
	String pContent = CommUtil.strCheckNull((String) request.getParameter("content"));
	String pRegName = CommUtil.strCheckNull((String) request.getParameter("regName"));
	
	BoardVo board = new BoardVo();
	board.setNo(pNo);
	board.setTitle(pTitle);
	board.setContent(pContent);
	board.setRegName(pRegName);
	board.setUpdName(pRegName);
	
	// 업데이트 저장 처리.
	BoardManager manager = BoardManager.getInstance();
	manager.updateBoard(board);
	
	// 리스트 조회.
	RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/boardList.jsp");
	rd.forward(request, response);	

%>
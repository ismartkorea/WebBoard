<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.io.Reader" %>    
<%@ page import = "com.webboard.common.CommUtil" %>
<%@ page import = "com.webboard.BoardVo" %>
<%@ page import = "com.webboard.BoardManager" %>
<%@ page import = "org.apache.log4j.Logger" %>
<%
	request.setCharacterEncoding("UTF-8");
	
	//Logger logger = Logger.getLogger( this.getClass() );

	// 파라미터 조회.
	int pNo = CommUtil.intCheckNull((String) request.getParameter("no"));	
	String pTitle = (String) request.getParameter("title");
	String pContent = (String) request.getParameter("content");
	String pRegName = (String) request.getParameter("regName");
	
	//logger.debug("content : " + pContent);
	
	BoardVo board = new BoardVo();
	board.setNo(pNo);	
	board.setTitle(pTitle);
	board.setContent(pContent);
	board.setRegName(pRegName);
	
	// 저장 처리.
	BoardManager manager = BoardManager.getInstance();
	manager.insertBoard(board);
	
	// 리스트 조회.
	RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/boardList.jsp");
	rd.forward(request, response);	

%>
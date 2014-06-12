<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.sql.Timestamp" %>
<%@ page import = "com.webboard.common.CommUtil" %> 
<%@ page import = "com.webboard.BoardManager" %> 
<%
	
	request.setCharacterEncoding("UTF-8");
	// 파라미터 취득.
	int no = CommUtil.intCheckNull((String) request.getParameter("no"));
	
	BoardManager manager = BoardManager.getInstance();
	int result = manager.deleteBoard(no);
	if(result !=0) {
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/boardList.jsp");
		rd.forward(request, response);

	} else {
%>	
<script>
alert("데이타 삭제에 실패했습니다!");
history.back(-1);
</script>
<%
	}	
%>
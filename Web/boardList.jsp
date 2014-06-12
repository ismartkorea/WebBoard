<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "org.apache.log4j.Logger" %>  
<%@ page import = "com.webboard.BoardManager" %>
<%@ page import = "com.webboard.BoardVo" %>
<%@ page import = "com.webboard.common.CommUtil" %>
<%
		// 로그.
		Logger logger = Logger.getLogger( this.getClass() );
		// 파라미터 확인
		request.setCharacterEncoding("UTF-8");
		String pageNum = request.getParameter("num");
		logger.debug(">>> pageNum = " + pageNum);
%>
<%!
		int PAGE_SIZE = 10;
		SimpleDateFormat fomatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>
<%
		// 페이지 처리.
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage -1) * PAGE_SIZE;
		int endRow = currentPage * PAGE_SIZE - 1;
		int count = 0;
		
		logger.debug("/*----- top init part -----*/");
		logger.debug("currentPage = " + currentPage);
		logger.debug("startRow = " + startRow);
		logger.debug("endRow = " + endRow);
		logger.debug("/*------------------------*/");
		
		ArrayList<BoardVo> resultList = new ArrayList<BoardVo>();
		BoardManager bm = BoardManager.getInstance();
		
		// 전체 행의 갯수 구함.
		count = bm.getListCount();
		if(count > 0) {
			resultList = bm.getBoardList(startRow, endRow);
		}		
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>게시판 리스트 화면</title>
<style type="text/css">
<!--
body {
	font-size: 12px;
	font-family: 굴림;
	text-decoration: none;
	background: #42413C;
	margin: 0;
	padding: 0;
	color: #000;
}

/* ~~ Element/tag selectors ~~ */
ul, ol, dl { /* Due to variations between browsers, it's best practices to zero padding and margin on lists. For consistency, you can either specify the amounts you want here, or on the list items (LI, DT, DD) they contain. Remember that what you do here will cascade to the .nav list unless you write a more specific selector. */
	padding: 0;
	margin: 0;
}
h1, h2, h3, h4, h5, h6, p {
	margin-top: 0;	 /* removing the top margin gets around an issue where margins can escape from their containing div. The remaining bottom margin will hold it away from any elements that follow. */
	padding-right: 15px;
	padding-left: 15px; /* adding the padding to the sides of the elements within the divs, instead of the divs themselves, gets rid of any box model math. A nested div with side padding can also be used as an alternate method. */
}
a img { /* this selector removes the default blue border displayed in some browsers around an image when it is surrounded by a link */
	border: none;
}
/* ~~ Styling for your site's links must remain in this order - including the group of selectors that create the hover effect. ~~ */
a:link {
	color: #42413C;
	text-decoration: underline; /* unless you style your links to look extremely unique, it's best to provide underlines for quick visual identification */
}
a:visited {
	color: #6E6C64;
	text-decoration: underline;
}
a:hover, a:active, a:focus { /* this group of selectors will give a keyboard navigator the same hover experience as the person using a mouse. */
	text-decoration: none;
}

/* ~~ this fixed width container surrounds the other divs ~~ */
.container {
	width: 960px;
	background: #FFF;
	margin: 0 auto; /* the auto value on the sides, coupled with the width, centers the layout */
}

/* ~~ the header is not given a width. It will extend the full width of your layout. It contains an image placeholder that should be replaced with your own linked logo ~~ */
.header {
	background: #ADB96E;
}

/* ~~ This is the layout information. ~~ 

1) Padding is only placed on the top and/or bottom of the div. The elements within this div have padding on their sides. This saves you from any "box model math". Keep in mind, if you add any side padding or border to the div itself, it will be added to the width you define to create the *total* width. You may also choose to remove the padding on the element in the div and place a second div within it with no width and the padding necessary for your design.

*/

.content {

	padding: 10px 0;
}

/* ~~ The footer ~~ */
.footer {
	padding: 10px 0;
	background: #CCC49F;
}

/* ~~ miscellaneous float/clear classes ~~ */
.fltrt {  /* this class can be used to float an element right in your page. The floated element must precede the element it should be next to on the page. */
	float: right;
	margin-left: 8px;
}
.fltlft { /* this class can be used to float an element left in your page. The floated element must precede the element it should be next to on the page. */
	float: left;
	margin-right: 8px;
}
.clearfloat { /* this class can be placed on a <br /> or empty div as the final element following the last floated div (within the #container) if the #footer is removed or taken out of the #container */
	clear:both;
	height:0;
	font-size: 1px;
	line-height: 0px;
}
-->
</style>
<script language="javascript">
<!--
//어드민 입력 화면으로.
function onInputForm() {
	location.href = "<%=request.getContextPath()%>/boardInsertForm.jsp";
}

function onShowBoard(no) {
	//alert(">> modelNo is " + modelNo);	
		
		var frm = document.frm;
		frm.no.value = no;
		
		frm.action = "<%=request.getContextPath()%>/boardUpdateForm.jsp";
		
		frm.submit();
	}
	
//전송 처리.
function onSubmit() {
	var frm = document.frm;
	
	frm.action = "<%=request.getContextPath()%>/boardList.jsp";
	
	frm.submit();
}
//-->
</script>
</head>
<body>
<form name="frm" id="frm">
<input type="hidden" name="no" id="no" value=""/>
<div class="container">
  <div class="header">
  	<a href="#"><img src="" alt="로고 넣으세요!" name="Insert_logo" width="180" height="90" id="Insert_logo" style="background: #C6D580; display:block;" /></a> 
    <!-- end .header -->
  </div>
  <div class="content">
    <h1>테스트용 게시판.</h1>
    <!-- end .content -->    
    <table width="100%" border="0" cellspacing="1" cellpadding="1">
      <tr bgcolor="#ADB96E">
    	<th scope="row">No.</th>
    	<th scope="row">제목</th>
    	<th scope="row">작성자</th>
    	<th scope="row">작성일</th>
     </tr>
<%					
					if (count ==  0) {
%>
				  <tr>
				  	<td colspan="4" height="150"><center><b>해당 데이타가 존재하지 않습니다.</b></center></td>
				  </tr>
<%
					} else {				
						
						// 리스트 조회 및 출력.
						for(int i=0; i < resultList.size(); i++) {
							BoardVo board =(BoardVo) resultList.get(i);
%>
			      <tr bgcolor="#FFFFFF" onmouseover="this.style.backgroundColor='#FFF8DE'" onmouseout="this.style.backgroundColor='#FFFFFF'" 
			      	 style="cursor:hand"	onclick="onShowBoard('<%=board.getNo() %>')">
			      	<td><%=board.getNo()%>
			      	<td><%=board.getTitle() %></td>
			      	<td><%=board.getRegName() %></td>
			      	<td><%=CommUtil.timeStampToStr(board.getRegDate(),"yyyy/MM/dd") %></td>
			      </tr>
<%
			      		}
						
					}
%>
				<tr>
					<td height="3">&nbsp;</td>
				</tr>	
				<tr>
					<td colspan="4" align="center">
<%
				if(count > 0) {
					// 하단 페이지 처리.
					int pageCount = count / PAGE_SIZE + (count % PAGE_SIZE == 0 ? 0 : 1);
					int startPage = currentPage / PAGE_SIZE * PAGE_SIZE + 1;
					int endPage = startPage + 10 - 1;
					logger.debug("/*--------------page process ------------------*/");
					logger.debug("pageCount = " + pageCount);
					logger.debug("startPage = " + startPage);
					logger.debug("endPage = " + endPage);
					logger.debug("/*--------------------------------*/");
					
					if (endPage > pageCount) endPage = pageCount;
					
					if (startPage > 10) {
%>				  		
				  		<a href="<%=request.getContextPath() %>/boardList.jsp?num=<%= startPage - 10 %>">[이전]</a>
<%
					}
					for(int i = startPage; i <= endPage; i++) {
%>
					<a href="<%=request.getContextPath() %>/boardList.jsp?num=<%=i %>">[<%=i %>]</a>

<%						
					}
					
					if(endPage < pageCount) {
%>				  		
				  	<a href="<%=request.getContextPath() %>/boardList.jsp?num=<%=startPage + 10 %>">[다음]</a>	
<%
					}
				}
%>				  		
				  	</td>
				  </tr>
				<tr>
					<td height="10">&nbsp;</td>
				</tr>				  				
    </table>
    <table width="100%" border="1" cellspacing="1" cellpadding="1">
    	<tr>
	      	<td align="center">
				&nbsp;<input type="button" name="insertFormBtn" id="insertFormBtn" value="입력화면으로"  onclick="onInputForm()"/> 			      	
	      	</td>
    	</tr>
    </table>
  </div>
  <div class="footer">
    <p>Footer</p>
    <!-- end .footer -->
  </div>
  <!-- end .container -->
</div>
</form>
</body>
</html>

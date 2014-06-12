<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.webboard.BoardManager" %>
<%@ page import = "com.webboard.BoardVo" %>
<%@ page import = "com.webboard.common.CommUtil" %>    
<%
	//
	request.setCharacterEncoding("UTF-8");
	// 파라미터 취득.
	String strNo = CommUtil.strCheckNull((String)request.getParameter("no"));
	if(strNo.equals("")) {
		strNo = "1";
	}
	BoardManager bm = BoardManager.getInstance();
	BoardVo bv = bm.selectBoard(Integer.parseInt(strNo));
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 수정 화면.</title>
<style type="text/css">
<!--
body {
	font: 100%/1.4 Verdana, Arial, Helvetica, sans-serif;
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
<link rel="stylesheet" type="text/css" href="/editor/jquery.cleditor.css" />
<script type="text/javascript" src="/editor/jquery.min.js"></script>
<script type="text/javascript" src="/editor/jquery.cleditor.min.js"></script>
<script type="text/javascript">

$(document).ready(function () {
	$("#content").cleditor(
			{
		        width:        570, // width not including margins, borders or padding
		        height:       250, // height not including margins, borders or padding
		        controls:     // controls to add to the toolbar
		          "bold italic underline strikethrough subscript superscript | font size " +
		          "style | color highlight removeformat | bullets numbering | outdent " +
		          "indent | alignleft center alignright justify | undo redo | " +
		          "rule image link unlink | cut copy paste pastetext | print source",
		        colors:       // colors in the color popup
		          "FFF FCC FC9 FF9 FFC 9F9 9FF CFF CCF FCF " +
		          "CCC F66 F96 FF6 FF3 6F9 3FF 6FF 99F F9F " +
		          "BBB F00 F90 FC6 FF0 3F3 6CC 3CF 66C C6C " +
		          "999 C00 F60 FC3 FC0 3C0 0CC 36F 63F C3C " +
		          "666 900 C60 C93 990 090 399 33F 60C 939 " +
		          "333 600 930 963 660 060 366 009 339 636 " +
		          "000 300 630 633 330 030 033 006 309 303",
		        fonts:        // font names in the font popup
		          "Arial,Arial Black,Comic Sans MS,Courier New,Narrow,Garamond," +
		          "Georgia,Impact,Sans Serif,Serif,Tahoma,Trebuchet MS,Verdana," + 
		          "굴림체,굴림,바탕체",
		        sizes:        // sizes in the font size popup
		          "1,2,3,4,5,6,7",
		        styles:       // styles in the style popup
		          [["Paragraph", "<p>"], ["Header 1", "<h1>"], ["Header 2", "<h2>"],
		            ["Header 3", "<h3>"],  ["Header 4","<h4>"],  ["Header 5","<h5>"],
		            ["Header 6","<h6>"]],
		        useCSS:       false, // use CSS to style HTML when possible (not supported in ie)
		        docType:      // Document type contained within the editor
		          '<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">',
		        docCSSFile:   // CSS file used to style the document contained within the editor
		          "",
		        bodyStyle:    // style to assign to document body contained within the editor
		          "margin:4px; font:10pt Arial,Verdana; cursor:text"
		}			
	
	);
});


// 이전 화면으로 이동.
function onPrev() {
	location.href = "<%=request.getContextPath()%>/boardList.jsp";
}
// 삭제 처리.
function onDelete() {
	var frm = document.frm;
	
	var result = confirm("삭제처리하시겠습니까?");
	if(!result) {
	 	return false;
	} else {
		frm.action ="<%=request.getContextPath()%>/boardDelete.jsp";
		frm.submit();
	}
}
//전송 처리.
function onSubmit() {
	var frm = document.frm;
	
	frm.action = "<%=request.getContextPath()%>/boardUpdate.jsp";
	
	frm.submit();
}

</script>
</head>
<body>
<form name="frm" id="frm" method="post">
<input type="hidden" id="no" name="no" value="<%=strNo%>"/>
<div class="container">
  <div class="header">
  	<a href="#"><img src="" alt="로고 넣으세요!" name="Insert_logo" width="180" height="90" id="Insert_logo" style="background: #C6D580; display:block;" /></a> 
    <!-- end .header -->
  </div>
  <div class="content">
    <h1>테스트용 게시판.</h1>
    <!-- end .content -->
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <th bgcolor="#C6D580" scope="row">제 목</th>
	    <td bgcolor="#C6D580"><input type="text" name="title" id="title" value="<%=bv.getTitle() %>" size="90" maxlength="20"/></td>
	  </tr>
	  <tr>
	    <th bgcolor="#ADB96E" scope="row">내용</th>
	    <td bgcolor="#ADB96E"><textarea id="content" name="content"  rows="10" cols="100"><%=bv.getContent() %></textarea></td>
	  </tr>
	  <tr>
	    <th bgcolor="#C6D580" scope="row">작성자명</th>
	    <td bgcolor="#C6D580"><input type="text" name="regName" id="regName" value="<%=bv.getRegName() %>" size="20" maxlength="20" /></td>
	  </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    	<tr>
    		<td>&nbsp;</td>
    	</tr>
    	<tr>
    		<td align="center">
    			<input type="button" name="prevBtn" id="prevBtn" value="리스트 화면" onclick="onPrev()"/>&nbsp;
    			<input type="button" id="delBtn" name="delBtn" value="삭제" onclick="onDelete()"/>&nbsp;
    		<input type="button" name="submitBtn" id="submitBtn" value="전송" onclick="onSubmit()"/></td>
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
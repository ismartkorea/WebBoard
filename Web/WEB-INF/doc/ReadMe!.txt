context.xml에 추가하세요.	
	
	<Resource name="jdbc/webboard"
					auth="Container"
					type="javax.sql.DataSource"
					driverClassName="core.log.jdbc.driver.MysqlDriver"
					loginTomeout="10"
					maxWait="5000"
					username="wbuser"
					password="admin123"
					testOnBorrow="true"
					url="jdbc:mysql://localhost:3306/webboard">
	</Resource>	
	
* 웹에디터
Getting Started With CLEditor
http://premiumsoftware.net/CLEditor/GettingStarted#usage
참조하세요.

* 필요파일
jquery.cleditor.min.js - jQuery Plugin (minified) 
jquery.cleditor.js - jQuery Plugin (source) 
jquery.cleditor.css - Style Sheet 
images/buttons.gif - Toolbar Button Image Strip 
images/toolbar.gif - Toolbar Background Image 



<%@page session="false"%>
<html>
<body>
	<h1>Title : ${title}</h1>	
	<h1>Message : ${message}</h1>
	<h2><a href="<c:url value="/logout" />" > Logout</a></h2>
</body>
</html>
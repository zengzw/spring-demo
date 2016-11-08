<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
	
	<form:form commandName="userMulti" action="/validate-group" method="post" >
	
		name:<form:input path="name" />
		<form:errors path="name"/><br>
		id:<form:input path="id" />
		<form:errors path="id"/><br></br>
		age:<form:input path="age" />
		<form:errors path="age"/><br></br>
		<input type="submit" value="提交">
	</form:form>

	
</body>
</html>
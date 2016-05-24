<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>account list</title>
</head>
<body>
	<table class="table table-striped" id="responsiveTable">
        <thead>
        <tr>
            <th>用户id</th>
            <th>用户名字</th>
        </tr>
        </thead>
        <tbody>
			<c:forEach var="account" items="${page.accounts}">
			<tr>
                <td>${account.id}</td>
                <td>${account.username}</td>
            </tr>
			</c:forEach>
        </tbody>
    </table>
</body>
</html>
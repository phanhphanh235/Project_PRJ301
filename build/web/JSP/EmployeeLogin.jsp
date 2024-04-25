<%-- 
    Document   : EmployeeLogin
    Created on : Apr 22, 2024, 1:43:06 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="EmployeeController" method="post">
            <p>username <input type="text" name="userName"><!-- comment -->
            <p> password<input type="password" name="password"> 
                <input type="hidden" name="service" value="login">
            <p><input type="submit" name="submit" value="login">
                <input type="reset">
        </form>
    </body>
</html>

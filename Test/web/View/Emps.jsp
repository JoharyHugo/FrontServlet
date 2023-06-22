<%-- 
    Document   : Emps
    Created on : 4 avr. 2023, 01:38:37
    Author     : johary
    
--%>
<% String a=(String)request.getAttribute("Employer"); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Emp</title>
    </head>
    <body>
        <h1>Hello Emp!<% out.print(a);%> </h1>
    </body>
</html>

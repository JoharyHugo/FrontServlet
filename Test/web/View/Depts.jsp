<%-- 
    Document   : Depts
    Created on : 4 avr. 2023, 01:37:46
    Author     : johary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String dep=(String)request.getAttribute("Departement"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dept</title>
    </head>
    <body>
        <h1>Hello <% out.println(dep);%>  !</h1>
    </body>
</html>

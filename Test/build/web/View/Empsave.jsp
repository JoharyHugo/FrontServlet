<%-- 
    Document   : Empsave
    Created on : 6 juin 2023, 13:39:17
    Author     : johary
--%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="etu2021.Framework.model.Emp"%>
<% Emp a=(Emp)request.getAttribute("Emp");
 
 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><% out.print(a.getnom());%></h1>
        <h1><% out.print(a.getid());%></h1>
        <% for(Byte b:a.getfichier().getbit()){ %>
        <h1> <% out.print(b.toString()); %> </h1>
        <% } %>
        <h1><% out.print(a.getfichier().getnom()); %></h1>
    </body>
</html>

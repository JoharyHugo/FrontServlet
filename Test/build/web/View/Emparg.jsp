<%-- 
    Document   : Emparg
    Created on : 18 juin 2023, 23:46:46
    Author     : johary
--%>

<%@page import="etu2021.Framework.model.Emp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Emp employer=(Emp)request.getAttribute("arguEmp");  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employer Argument</title>
    </head>
    <body>
        <h1>Hello <% out.print(employer.getnom());%>!</h1>
         <h1> <% out.print(employer.getid());%>!</h1>
          <% for(Byte b:employer.getfichier().getbit()){ %>
        <h1> <% out.print(b.toString()); %> </h1>
        <% } %>
        <h1><% out.print(employer.getfichier().getnom()); %></h1>
    </body>
</html>

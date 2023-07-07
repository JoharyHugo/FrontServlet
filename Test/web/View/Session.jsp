<%-- 
    Document   : Session
    Created on : 6 juil. 2023, 21:21:34
    Author     : johary
--%>

<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <%
            // Récupérer la HashMap de la session
            HashMap<String, Object> sessions = (HashMap<String, Object>) request.getAttribute("session");
            
            // Vérifier si la HashMap est vide
            if (session != null && !sessions.isEmpty()) {
                // Parcourir les entrées de la HashMap
                for (String key : sessions.keySet()) {
                    Object value = sessions.get(key);
        %>
                    <p>Clé : <%= key %>, Valeur : <%= value %></p>
        <%
                }
            } else {
        %>
                <p>La session est vide.</p>
        <%
            }
        %>
    </body>
</html>


<%-- 
    Document   : login
    Created on : 30 juin 2023, 12:26:27
    Author     : johary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <style>
        form {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  max-width: 800px;
  margin: auto;
  
}
label {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 5px;
  display: block;
  flex-basis: 100%;
}
input[type="text"], input[type="email"], input[type="date"],  input[type="number"],input[type="file"],input[type="password"],select {
  font-size: 16px;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ccc;
  margin-bottom: 20px;
  flex-basis: 48%;
}
select {
  flex-basis: 100%;
}
input[type="submit"] {
  background-color: #007bff;
  color: #fff;
  padding: 10px 20px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  font-size: 16px;
  transition: background-color 0.3s ease;
  margin-top: 20px;
}
input[type="submit"]:hover {
  background-color: #0062cc;
}
    </style>
    <body>
        <h1>Login</h1>
        <form action="Connect" method="post" >
        <label for="objet">Name:</label>
        <input type="text" name="Name" id="objet">
        <br><br>
        <label for="Siege">Mdp:</label>
        <input type="password" name="Mdp" id="Siege">
        <br><br>
       
         <input type="submit" values="Valider">  
        </form>
        <a href="Emp_all">Test</a>
        <br>
        <a href="Sessions">Test Session</a>
    </body>
</html>

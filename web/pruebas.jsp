<%-- 
    Document   : pruebas
    Created on : 19 abr. 2021, 18:59:17
    Author     : 52777
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Datos de la Persona relacionada al Usuario</h1><br/>
        <%
           String nom = (String)session.getAttribute("name");
           String imagen = (String)session.getAttribute("imagen");
        %>
         <h2>username: <%out.println(nom);%> </h2>
        <%
          if(imagen!=null){
        %>
         <img src="/SistemaMedico/images/<%=imagen%>" alt=""/> 
        <%
          }
          else{
        %>
        
        <img src="/SistemaMedico/images/defaultProfile.jpg" alt=""/>
        <%}%>
    </body>
</html>
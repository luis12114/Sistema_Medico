<%-- 
    Document   : index.js
    Created on : 25 abr. 2021, 13:19:05
    Author     : 52777
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String nom = (String) session.getAttribute("name");
    String imagen = (String) session.getAttribute("imagen");
%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard</title>
        <link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
        <link href="/SistemaMedico//css/DashboardStylel.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <input type="checkbox" id="menu-toggle">
        <div class="sidebar">

            <div class="brand">
                <span class="lab la-affiliatetheme"></span>
                <h2>Medical</h2>
            </div>
            <div class="sidemenu">
                <div class="side-user">
                    <div class="side-img"
                      style="
                       <%if(imagen!=null){%>
                         background-image:url(/SistemaMedico/images/<%=imagen%>
                       <%}else{%>
                         background-image:url(/SistemaMedico/images/defaultProfile.jpg
                       <%}%>
                      "
                    >
                    </div>
                    <div class="user">
                        <small><%out.println(nom);%></small>
                        <p>Software developer</p>
                    </div>
                </div>
                <ul>

                    <li>
                        <a href="#"  class="active">
                            <span class="las la-home"></span>
                            <span>Dashboard</span>
                        </a>
                    </li>
                </ul>

            </div>
        </div>
        <div class="main-content">
            <header>
                <label for="menu-toggle" class="menu-toggler">
                    <span class="las la-bars"></span>
                </label>
                <div class="search">
                    <span class="las la-search"></span>
                    <input type="search" placeholder="Enter keyword">
                </div>
                <div class="header-icons">
                    <span class="las la-bell"></span>
                    <span class="las la-bookmark"></span>
                </div>
            </header>
        </div>
        <label class="close-mobile-menu" for="menu-toggle"></label>
    </body>

</html>
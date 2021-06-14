<%-- 
    Document   : edit
    Created on : 5 may. 2021, 20:18:52
    Author     : 52777
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
    /**
     * Datos del usuario logeado*
     */
    String nom1 = (String) session.getAttribute("name1");
    String imagen1 = (String) session.getAttribute("imagen1");

    /**
     * Datos del usuario que se desea editar*
     */
    String nom = (String) session.getAttribute("name");
    String email = (String) session.getAttribute("email");
    String password = (String) session.getAttribute("pass");
    String imagen = (String) session.getAttribute("imagen");
    
%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit</title>
        <link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
        <link rel="stylesheet" href="/SistemaMedico//css/dashboardStyle.css">
    </head>

    <body>
        <input type="checkbox" id="menu-toggle">
        <div class="sidebar">

            <div class="brand">
                <span class="las la-stethoscope"></span>
                <h2>Medical</h2>
            </div>
            <div class="sidemenu">
                <div class="side-user">
                    <div class="side-img"
                         style="
                         <%if (imagen != null) {%>
                         background-image:url(/SistemaMedico/images/<%=imagen1%>
                         <%} else {%>
                         background-image:url(/SistemaMedico/images/defaultProfile.jpg
                         <%}%>
                         "
                         >
                    </div>
                    <div class="user">
                        <small><%out.println(nom1);%></small>
                        <p>Panel Administrador</p>
                    </div>
                </div>
                <ul>

                    <li>
                        <form method="post" action="/SistemaMedico/controlAdmin" class="opc-cont">
                            <button type="submit" class="opc">
                                <span class="las la-home"></span> <samp class="text">Dashboard</samp>
                            </button>
                            <input type="hidden" name="action" value="dashboard">
                            <input type="text" name="nameUser" value="<%out.println(nom1);%>" style="display:none">
                        </form>
                    </li>

                    <li>
                        <form method="post" action="/SistemaMedico/UserController" class="opc-cont">
                            <button type="submit" class="opc">
                                <span class="las la-user"></span> <samp class="text">Users</samp>
                            </button>
                            <input type="hidden" name="action" value="allUsers">
                            <input type="text" name="usuario" value="<%out.println(nom1);%>" style="display:none">
                        </form>
                    </li>

                    <li>
                        <form method="post" action="/SistemaMedico/RolesController" class="opc-cont">
                            <button type="submit" class="opc">
                                <span class="las la-user-tag"></span> <samp class="text">Roles</samp>
                            </button>
                            <input type="hidden" name="action" value="allRoles">
                            <input type="text" name="usuario" value="<%out.println(nom);%>" style="display:none">
                        </form>
                    </li>

                    <li>
                        <form method="post" action="/SistemaMedico/MedicosController" class="opc-cont">
                            <button type="submit" class="opc">
                                <span class="las la-stethoscope"></span> <samp class="text">Medicos</samp>
                            </button>
                            <input type="hidden" name="action" value="allMedicos">
                            <input type="text" name="usuario" value="<%out.println(nom);%>" style="display:none">
                        </form>
                    </li>

                    <li>
                        <form method="post" action="/SistemaMedico/CitasController" class="opc-cont">
                            <button type="submit" class="opc">
                                <span class="las la-calendar-day"></span> <samp class="text">Citas
                                </samp>
                            </button>
                            <input type="hidden" name="action" value="allCitas">
                            <input type="text" name="usuario" value="<%out.println(email);%>" style="display:none">
                        </form>
                    </li>
                </ul>
            </div>
        </div>
        <div class="main-content">
            <header>
                <label for="menu-toggle" class="menu-toggler">
                    <span class="las la-bars"></span>
                </label>
                <div class="search" style="display: none;">
                    <span class="las la-search"></span>
                    <input type="search" placeholder="Enter keyword">
                </div>
                <div class="header-icons ">
                    <span class="las la-bell "></span>
                    <span class="las la-bookmark "></span>
                </div>
            </header>
            <main class="container-edit ">
                <div class="contactInfo ">
                    <div class="img">
                        <%if (imagen != null) {%>
                        <img src="/SistemaMedico/images/<%=imagen%>" alt="">
                        <%} else {%>
                        <img src="/SistemaMedico/images/defaultProfile.jpg" alt="">
                        <%}%>
                    </div>
                </div>
                <div class="contactForm">
                    <h2>Informacion</h2>

                    <form action="" method="post" action="/SistemaMedico/UserController" class="formBox" enctype="multipart/form-data">

                        <div class="inputBox w50">
                            <input type="text" name="usuario" required value="<%out.println(nom);%>">
                            <span>User Name</span>
                        </div>

                        <div class="inputBox w50">
                            <input type="password" name="password" required value="<%out.println(password);%>">
                            <span>Password</span>
                        </div>

                        <div class="inputBox w50">
                            <input  <input type="email"  name="email" required value="<%out.println(email);%>">
                            <span>Email</span>
                        </div>
                            
                        <div class="inputBox w50">
                            <input type="file" name="file">
                        </div>
                            
                        <div class="inputBox w100">
                            <%
                                String msg = (String) session.getAttribute("mensaje");
                                if (!(msg == null)) {
                            %>
                            <p style="color:white; padding-top:10px; padding-bottom: 10px;background-color: red; font-size:14px; margin-bottom:0px; text-align: center;"><i class="las la-times" style="color:white;margin-right:3px; font-size:14px;"></i><%out.println(msg);%></p>
                            <%

                                    session.setAttribute("mensaje", null);
                                }
                            %>
                        </div>
                        
                        <!--<div class="inputBox w50">
                            <input type="file" name="file">
                        </div>
                        
                       
                        <div class="inputBox w100">
                            <input type="file" name="file">
                            <span>Cambiar foto</span>
                        </div>

                        <div class="inputBox w50">
                            <input type="text" required>
                            <span>Mobil Number</span>
                        </div>

                        <div class="inputBox w100">
                            <textarea required></textarea>
                            <span>Write you message here...</span>
                        </div>-->


                        <div class="botones">
                            <div class="inputBox w100  buton">
                                <input type="hidden" name="action" value="updateUser">
                                <input type="text" name="usuario1" value="<%out.println(nom);%>" style="display:none">
                                <input type="text" name="loginUser" value="<%out.println(nom1);%>" style="display:none">
                                <input type="text" name="emailLogin" value="<%out.println(email);%>" style="display:none">
                                <input  <input type="text"  name="imagen" value="<%out.println(imagen);%>" style="display:none">
                                <button type="submit" value="Send ">Guardar</button>
                            </div>
                        </div>

                    </form>
                    <div class="botom">
                        <form method="post" action="/SistemaMedico/UserController" class="formBox">
                            <input type="hidden" name="action" value="allUsers">
                            <input type="text" name="usuario" value="<%out.println(nom);%>" style="display:none">
                            <div class="botones">
                                <div class="inputBox w100  buton">
                                    <button type="submit" class="top">Regresar</button>
                                </div> 
                            </div>
                        </form>
                    </div>
                </div>
            </main>
        </div>
        <label class="close-mobile-menu" for="menu-toggle"></label>
    </body>

</html>
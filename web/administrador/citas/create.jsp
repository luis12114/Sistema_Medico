<%-- 
    Document   : Create
    Created on : 11 may. 2021, 22:44:53
    Author     : 52777
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%
    String nom = (String) session.getAttribute("name");
    String imagen = (String) session.getAttribute("imagen");
    String email = (String) session.getAttribute("email");
    int rol = (int) session.getAttribute("rol");
%>


<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create</title>
        <link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
        <link href="/SistemaMedico//css/dashboardStyle.css" rel="stylesheet" type="text/css"/>
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
                         background-image:url(/SistemaMedico/images/<%=imagen%>
                         <%} else {%>
                         background-image:url(/SistemaMedico/images/defaultProfile.jpg
                         <%}%>
                         "
                         >
                    </div>
                    <div class="user">
                        <small><%out.println(nom);%></small>
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
                            <input type="text" name="nameUser" value="<%out.println(nom);%>" style="display:none">
                        </form>
                    </li>

                    <li>
                        <form method="post" action="/SistemaMedico/DatosController" class="opc-cont">
                            <button type="submit" class="opc">
                                <span class="las la-users-cog"></span> <samp class="text">Mis Datos</samp>
                            </button>
                            <input type="hidden" name="action" value="datos">
                            <input type="text" name="usuario-login" value="<%out.println(nom);%>" style="display:none">
                            <input type="text" name="usuario" value="<%out.println(nom);%>" style="display:none">
                        </form>
                    </li> 
                    <%if (rol == 1) {%>
                    <li>
                        <form method="post" action="/SistemaMedico/UserController" class="opc-cont">
                            <button type="submit" class="opc">
                                <span class="las la-user"></span> <samp class="text">Users</samp>
                            </button>

                            <input type="hidden" name="action" value="allUsers">
                            <input type="text" name="usuario" value="<%out.println(nom);%>" style="display:none">
                        </form>
                    </li>
                    <%}%>

                    <%if (rol == 1) {%>
                    <li>
                        <form method="post" action="/SistemaMedico/RolesControlle" class="opc-cont">
                            <button type="submit" class="opc">
                                <span class="las la-user-tag"></span> <samp class="text">Roles</samp>
                            </button>
                            <input type="hidden" name="action" value="allRoles">
                            <input type="text" name="usuario" value="<%out.println(nom);%>" style="display:none">
                        </form>
                    </li>
                    <%}%>

                    <%if (rol == 1) {%>
                    <li>
                        <form method="post" action="/SistemaMedico/MedicosController" class="opc-cont">
                            <button type="submit" class="opc">
                                <span class="las la-stethoscope"></span> <samp class="text">Medicos</samp>
                            </button>
                            <input type="hidden" name="action" value="allMedicos">
                            <input type="text" name="usuario" value="<%out.println(nom);%>" style="display:none">
                        </form>
                    </li>
                    <%}%>

                    <%if (rol == 1 || rol == 2 || rol == 3) {%>
                    <li>
                        <form method="post" action="/SistemaMedico/CitasController" class="opc-cont">
                            <button type="submit" class="opc">
                                <span class="las la-calendar-day"></span> <samp class="text">Citas
                                </samp>
                            </button>
                            <input type="hidden" name="action" value="allCitas">
                            <input type="text" name="usuario" value="<%out.println(nom);%>" style="display:none">
                        </form>
                    </li>
                    <%}%>
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

                    </div>
                </div>
                <div class="contactForm">
                    <h2>Citas</h2>

                    <form method="post" action="/SistemaMedico/CitasController" class="formBox">
                        <div class="inputBox w50 ">
                            <input type="text" name="name" value="<%out.println(nom);%>" readonly required>
                            <!--<span>Nombre</span>-->
                        </div>

                        <div class="inputBox w50 ">
                            <select name="doctor" required>
                                <option value="" selected>Selecciona un medico</option>
                                <c:forEach items="${medico}" var="med">
                                    <option value="${med.nombre}">${med.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="inputBox w50 ">
                            <input type="date" name="fecha" id="fecha" required>
                            <span>Fecha</span>
                        </div>

                        <div class="inputBox w50 ">
                            <select name="hora" required>
                                <option value="" selected>Selecciona una hora</option>
                                <option value="9:00">9:00</option>
                                <option value="10:00">10:00</option>
                                <option value="11:00">11:00</option>
                                <option value="12:00">12:00</option>
                                <option value="13:00">13:00</option>
                                <option value="14:00">14:00</option>
                                <option value="15:00">15:00</option>
                            </select>
                        </div>

                        <div class="inputBox w50 ">
                            <input type="text" name="tel" required>
                            <span>Tel</span>
                        </div>


                        <div class="inputBox w50 ">
                            <input type="email" name="email" value="<%out.println(email);%>" readonly required>
                            <!--<span>Correo</span>-->
                        </div>

                        <div class="inputBox w50 ">
                            <input type="text" name="genero" required>
                            <span>Genero</span>
                        </div>

                        <div class="inputBox w50 ">
                            <input type="text" name="motivo" required>
                            <span>Motivo</span>
                        </div>

                        <div class="inputBox w100">
                            <input type="text" name="sintomas"required>
                            <span>Sintomas</span>
                        </div>

                        <div class="inputBox w100">
                            <%
                                String msg = (String) session.getAttribute("mensaje");
                                if (!(msg == null)) {
                            %>
                            <p style="color:white; padding-top:5px; padding-bottom:5x;background-color: red; font-size:14px; margin-bottom:-20px; text-align: center;"><i class="las la-times" style="color:white;margin-right:3px; font-size:14px;"></i><%out.println(msg);%></p>
                                <%

                                        session.setAttribute("mensaje", null);
                                    }
                                %>
                        </div>

                        <input type="text" name="usuario1" value="<%out.println(nom);%>" style="display:none">    
                        <input type="hidden" name="action" value="addCita">

                        <div class="">
                            <div class="inputBox w100  buton">
                                <button type="submit " value="Send ">Guardar</button>
                            </div
                        </div>
                    </form>
                    <div class="botom">
                        <form method="post" action="/SistemaMedico/CitasController" class="formBox">
                            <input type="hidden" name="action" value="allCitas">
                            <input type="text" name="usuario" value="<%out.println(nom);%>" style="display:none">
                            <div class="inputBox w100  buton">
                                <button type="submit" class="top">Regresar</button>
                            </div> 
                        </form>
                    </div>
                </div>
            </main>
        </div>
        <label class="close-mobile-menu " for="menu-toggle "></label>
        <script>
            window.addEventListener('load', function () {

                document.getElementById('fecha').type = 'text';

                document.getElementById('fecha').addEventListener('blur', function () {

                    document.getElementById('fecha').type = 'text';

                });

                document.getElementById('fecha').addEventListener('focus', function () {

                    document.getElementById('fecha').type = 'date';

                });

            });

        </script>
    </body>

</html>



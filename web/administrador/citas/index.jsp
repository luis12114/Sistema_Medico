<%-- 
    Document   : index
    Created on : 25 abr. 2021, 17:41:00
    Author     : 52777
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
    String nom = (String) session.getAttribute("name");
    String imagen = (String) session.getAttribute("imagen");
    int rol = (int) session.getAttribute("rol");
%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Citas</title>
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
                        <form method="post" action="/SistemaMedico/RolesController" class="opc-cont">
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
                        <form method="post" action="/SistemaMedico/CitasController" class="opc-cont active">
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
                <div class="search">
                    <span class="las la-search"></span>
                    <input type="search" placeholder="Enter keyword">
                </div>
                <div class="header-icons">
                    <span class="las la-bell"></span>
                    <span class="las la-bookmark"></span>
                </div>
            </header>
            <main class="card__container ">
                <%if (rol == 1 || rol == 2) {%>
                <div class="card__glass">
                    <form method="post" action="/SistemaMedico/CitasController" class="add">
                        <button class="btn-add"><span class="las la-plus-circle"></span></button>
                        <input type="hidden" name="action" value="formAddCita">
                        <input type="text" name="usuario-login" value="<%out.println(nom);%>" style="display:none">
                    </form>
                </div>
                <%}%>


                <c:forEach items="${citas}" var="citas">
                    <div class="card__glass">

                        <div class="card__data">
                            <h3 class="card__title"> ${citas.id}. ${citas.name} </h3>
                            <span class="card__profession">Doctor:${citas.doctor}</span><br>
                            <span class="card__profession">Correo:${citas.correo}</span><br>
                            <span class="card__profession">Genero:${citas.genero}</span><br>
                            <span class="card__profession">Motivo:${citas.motivo}</span><br>
                            <span class="card__profession">Sintomas:${citas.sintomas}</span><br>
                            <span class="card__profession">Tel: ${citas.tel}</span><br>
                            <span class="card__profession">Horario:${citas.fecha}, ${citas.hora}</span><br>
                            <span class="card__profession">Estatus:${citas.estatus}</span><br>
                            <!--<span class="card__profession">Web Developer</span><br>
                            <span class="card__profession">Web Developer</span>-->
                        </div>


                        <%if (rol == 1) {%>
                        <form method="post" action="/SistemaMedico/CitasController" class="card__button">
                            <span class="las la-edit"></span><input type="submit" value="Edit" class="opc">
                            <input type="hidden" name="action" value="editCita">
                            <input type="text" name="usuario-login" value="<%out.println(nom);%>" style="display:none">
                            <input type="text" name="cita" value="${citas.id}" style="display:none">
                        </form>

                        <form method="post" action="/SistemaMedico/CitasController" class="card__button formulario">
                            <span class="las la-trash"></span><input type="submit"  value="Delit" class="opc">
                            <input type="hidden" name="action" value="delitCita">
                            <input type="text" name="usuario-login" value="<%out.println(nom);%>" style="display:none">
                            <input type="text" name="cita" value="${citas.id}" style="display:none">
                        </form>

                        <form method="post" action="/SistemaMedico/CitasController" class="card__button formulario">
                            <span class="las la-ban" > </span><input type="submit"  value="Cancelar" class="opc" style="margin-left:1px;">
                            <input type="hidden" name="action" value="cancelarCita">
                            <input type="text" name="usuario-login" value="<%out.println(nom);%>" style="display:none">
                            <input type="text" name="cita" value="${citas.id}" style="display:none">
                        </form>
                        <%}%>

                        <%if (rol == 2) {%>
                        <form method="post" action="/SistemaMedico/CitasController" class="card__button">
                            <span class="las la-edit"></span><input type="submit" value="Edit" class="opc">
                            <input type="hidden" name="action" value="editCita">
                            <input type="text" name="usuario-login" value="<%out.println(nom);%>" style="display:none">
                            <input type="text" name="cita" value="${citas.id}" style="display:none">
                        </form>
                        <form method="post" action="/SistemaMedico/CitasController" class="card__button formulario">
                            <span class="las la-ban" > </span><input type="submit"  value="Cancelar" class="opc" style="margin-left:1px;">
                            <input type="hidden" name="action" value="cancelarCita">
                            <input type="text" name="usuario-login" value="<%out.println(nom);%>" style="display:none">
                            <input type="text" name="cita" value="${citas.id}" style="display:none">
                        </form>
                        <%}%>
                        <%if (rol == 3) {%>
                        <form method="post" action="/SistemaMedico/CitasController" class="card__button formulario">
                            <span class="las la-check-circle" > </span><input type="submit"  value="Realizada" class="opc" style="margin-left:1px;">
                            <input type="hidden" name="action" value="cancelarCita">
                            <input type="text" name="usuario-login" value="<%out.println(nom);%>" style="display:none">
                            <input type="text" name="cita" value="${citas.id}" style="display:none">
                        </form>
                        <%}%>

                    </div>
                </c:forEach>
            </main>
        </div>
        <label class="close-mobile-menu" for="menu-toggle"></label
    </body>
</html>
<%-- 
    Document   : edit
    Created on : 5 may. 2021, 20:18:52
    Author     : 52777
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
    /**Datos del usuario logeado**/
    String nom = (String) session.getAttribute("name");
    String imagen = (String) session.getAttribute("imagen");
    
    /**Datos del usuario que se desea editar**/
    int id= (int) session.getAttribute("id");
    String area= (String) session.getAttribute("area");
    String nombre= (String) session.getAttribute("nombre");
    String apell_p= (String) session.getAttribute("apell_p");
    String apell_m= (String) session.getAttribute("apell_m");
    String direccion= (String) session.getAttribute("direccion");
    String email= (String) session.getAttribute("email");
    String tel= (String) session.getAttribute("tel");
    String hora_inic= (String) session.getAttribute("hora_inic");
    String hora_fin= (String) session.getAttribute("hora_fin");
    
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
                        <form method="post" action="/SistemaMedico/UserController" class="opc-cont">
                            <button type="submit" class="opc">
                                <span class="las la-user"></span> <samp class="text">Users</samp>
                            </button>
                            <input type="hidden" name="action" value="allUsers">
                            <input type="text" name="usuario" value="<%out.println(nom);%>" style="display:none">
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
                        <form method="post" action="/SistemaMedico/MedicosController" class="opc-cont active">
                            <button type="submit" class="opc">
                                <span class="las la-stethoscope"></span> <samp class="text">Medicos</samp>
                            </button>
                            <input type="hidden" name="action" value="allMedicos">
                            <input type="text" name="usuario" value="<%out.println(nom);%>" style="display:none">
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
                    <h2>Medico</h2>
                    <form method="post" action="/SistemaMedico/MedicosController" class="formBox">
                        <div class="inputBox w50 ">
                            <input type="text" name="area" required value="<%out.println(area);%>">
                            <span>Area</span>
                        </div>

                        <div class="inputBox w50 ">
                            <input type="text" name="nombre" required value="<%out.println(nombre);%>">
                            <span>Nombre</span>
                        </div>

                        <div class="inputBox w50 ">
                            <input type="text" name="apm" required value="<%out.println(apell_m);%>">
                            <span>Apellido Materno</span>
                        </div>

                        <div class="inputBox w50 ">
                            <input type="text" name="atp" required value="<%out.println(apell_p);%>">
                            <span>Apellido Paterno</span>
                        </div>

                        <div class="inputBox w50 ">
                            <input type="email" name="email" required value="<%out.println(email);%>">
                            <span>Correo</span>
                        </div>

                        <div class="inputBox w50 ">
                            <input type="text" name="tel" required value="<%out.println(tel);%>">
                            <span>Tel</span>
                        </div>

                        <div class="inputBox w50 ">
                            <input type="text" name="hrinic" required value="<%out.println(hora_inic);%>">
                            <span>Hora de incio</span>
                        </div>

                        <div class="inputBox w50 ">
                            <input type="text" name="hrfin" required value="<%out.println(hora_fin);%>">
                            <span>Hora de termino</span>
                        </div>

                        <div class="inputBox w100">
                            <input type="text" name="direcc" required value="<%out.println(direccion);%>">
                            <span>Dirrección</span>
                        </div>

                        <input type="hidden" name="action" value="updateMedico">
                        <input type="text" name="usuario1" value="<%out.println(nom);%>" style="display:none">
                        <input type="text" name="id_serch" value="<%out.println(id);%>" style="display:none">
                        <div class="">
                            <div class="inputBox w100  buton">
                                <button type="submit " value="Send ">Guardar</button>
                            </div
                        </div>
                    </form>
                    <div class="botom">
                        <form method="post" action="/SistemaMedico/MedicosController" class="formBox">
                            <input type="hidden" name="action" value="allMedicos">
                            <input type="text" name="usuario" value="<%out.println(nom);%>" style="display:none">
                            <div class="inputBox w100  buton">
                                <button type="submit" class="top">Regresar</button>
                            </div> 
                        </form>
                    </div>
                </div>
            </main>
        </div>
        <label class="close-mobile-menu" for="menu-toggle"></label>
    </body>

</html>
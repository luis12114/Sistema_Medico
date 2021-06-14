<%-- 
    Document   : Create
    Created on : 11 may. 2021, 22:44:53
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
                        
                    </div>
                </div>
                <div class="contactForm">
                    <h2>Registro</h2>

                    <form method="post" action="/SistemaMedico/RolesController" class="formBox" enctype="multipart/form-data">
                        
                        <div class="inputBox w50">
                            <input type="text" name="rol" required value="">
                            <span>Rol</span>
                        </div>
                            
                        <div class="inputBox w50">
                            <input type="text" name="desc" required value="">
                            <span>Descripción</span>
                        </div>
                            
                        <div class="inputBox w100">
                            <input type="text" name="url" required value="">
                            <span>Url</span>
                        </div>
                        
                        
                        <input type="hidden" name="action" value="addRole">

                        <div class="">
                            <div class="inputBox w100  buton">
                                <button type="submit " value="Send ">Guardar</button>
                            </div
                        </div>
                    </form>
                   <div class="botom">
                    <form method="post" action="/SistemaMedico/RolesController" class="formBox">
                        <input type="hidden" name="action" value="allRoles">
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
        <label class="close-mobile-menu " for="menu-toggle "></label>
    </body>

</html>


